package com.increff.assure.dto;

import com.increff.assure.client.ClientChannel;
import com.increff.assure.pdf.PDFFromFOP;
import com.increff.assure.pojo.InventoryPojo;
import com.increff.assure.pojo.OrderItemPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.*;
import com.increff.assure.util.ConvertorUtil;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.data.OrderItemData;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.form.OrderInvoice;
import com.increff.commons.form.OrderItemFormCSV;
import com.increff.commons.form.OrderItemFormChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class OrderItemDto {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientChannel clientChannel;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BinSkuService binSkuService;

    public OrderItemData addByAssure(OrderItemFormCSV form) throws ApiException {
        OrderPojo order = orderService.get(form.getOrderId());
        ProductPojo product = productService.getByClientIdAndClientSkuId(form.getClientSkuId(), order.getClient().getId());
        OrderItemPojo pojo = ConvertorUtil.convert(form, order, product);
        OrderItemPojo orderItem = orderItemService.add(pojo);
        return ConvertorUtil.convert(orderItem);
    }

    public OrderItemData addByChannel(OrderItemFormChannel form) throws ApiException {

        OrderPojo order = orderService.get(form.getOrderId());

        ChannelListingData cl = clientChannel
                .getByChannelSkuIdAndChannel(form.getChannelSkuId(), order.getChannelId());
        if (cl == null) {
            throw new ApiException("Client sku Id and Client pair does not exist.");
        }
        Long globalSkuId = cl.getGlobalSkuId();

        ProductPojo product = productService.get(globalSkuId);

        OrderItemPojo pojo = ConvertorUtil.convert(form, order, product);

        OrderItemPojo orderItem = orderItemService.add(pojo);

        return ConvertorUtil.convert(orderItem);
    }

    public OrderItemData get(Long id) throws ApiException {
        OrderItemPojo pojo = orderItemService.get(id);
        return ConvertorUtil.convert(pojo);
    }

    public List<OrderItemData> getAll() {
        List<OrderItemPojo> list = orderItemService.getAll();
        return ConvertorUtil.convertOrderItems(list);
    }

    public List<OrderItemData> getByOrder(Long id) throws ApiException {
        OrderPojo order = orderService.get(id);
        List<OrderItemPojo> list = orderItemService.getByOrder(order.getId());
        return ConvertorUtil.convertOrderItems(list);
    }

    public OrderItemData update(Long id, OrderItemFormChannel form) throws ApiException {
        OrderItemPojo pojo = orderItemService.get(id);
        OrderItemPojo list = orderItemService.update(id, form.getOrderedQuantity(), pojo.getAllocatedQuantity(),
                pojo.getFulfilledQuantity(), form.getSellingPrice());
        return ConvertorUtil.convert(list);
    }

    @Transactional(rollbackFor = ApiException.class)
    public List<OrderItemData> allocate(Long id) throws ApiException {

        OrderPojo order = orderService.get(id);
        List<OrderItemPojo> itemList = orderItemService.getByOrder(order.getId());

        boolean allItemsAllocated = true;
        // For every item in order, reducing the availableQuantity of the inventory and
        // increasing the value to allocatedQuantity. Reducing the binskus.
        for (OrderItemPojo item : itemList) {
            InventoryPojo inventory = inventoryService.getByGlobalSkuId(item.getProduct().getGlobalSkuId());
            Long allocateQuantity = Math.min(inventory.getAvailableQuantity(),
                    item.getOrderedQuantity() - item.getAllocatedQuantity());
            Long availableQuantity = inventory.getAvailableQuantity() - allocateQuantity;
            if (item.getOrderedQuantity() - item.getAllocatedQuantity() != allocateQuantity) {
                allItemsAllocated = false;
            }
            inventoryService.updateQuantities(inventory.getProduct().getGlobalSkuId(), availableQuantity,
                    allocateQuantity + inventory.getAllocatedQuantity(), inventory.getFulfilledQuantity());

            orderItemService.update(item.getId(), item.getOrderedQuantity(),
                    item.getAllocatedQuantity() + allocateQuantity, item.getFulfilledQuantity(),
                    item.getSellingPrice());

            binSkuService.reduceBinSkuByAllocatedQuantity(item.getProduct().getGlobalSkuId(), allocateQuantity);

        }

        if (allItemsAllocated == true) {
            orderService.updateStatus(order, OrderStatus.ALLOCATED);
        }

        return getByOrder(id);
    }

    @Transactional(rollbackFor = ApiException.class)
    public void generateInvoice(Long id, HttpServletResponse response)
            throws ApiException, JAXBException, IOException {
        OrderPojo order = orderService.get(id);
        if (order.getStatus() != OrderStatus.FULFILLED) {
            throw new ApiException("Order items are not Fulfilled.");
        }

        List<OrderItemPojo> itemList = orderItemService.getByOrder(order.getId());
        double sellingprice = 0;
        for (OrderItemPojo item : itemList) {
            sellingprice += item.getSellingPrice() * item.getOrderedQuantity();
        }

        List<OrderItemData> orderItemData = ConvertorUtil.convertOrderItems(itemList);
        OrderInvoice invoice = new OrderInvoice();
        invoice.setItems(orderItemData);
        sellingprice = Math.round(sellingprice * 100.0) / 100.0;
        invoice.setSellingprice(sellingprice);
        invoice.setId(order.getId());
        invoice.setClient(order.getClient().getName());
        invoice.setCustomer(order.getCustomer().getName());
        invoice.setOrderId(order.getChannelOrderId());
        invoice.setOrderDate(order.getCreatedAt().toString().substring(0, 10));
        // converting java object to XML
        PDFFromFOP.javaToXML(invoice);

        // converting XML to PDF
        PDFFromFOP.generatePDF(id);

        // writing back the PDF to HttpResponse
        PDFFromFOP.generateResponse(response);

    }

    public void delete(Long id) throws ApiException {
        orderItemService.delete(id);
    }

    public void fulfill(Long id) throws ApiException {
        OrderPojo order = orderService.get(id);
        if (order.getStatus() == OrderStatus.FULFILLED) {
            return;
        }
        if (order.getStatus() != OrderStatus.ALLOCATED) {
            throw new ApiException("Order items are not allocated.");
        }
        List<OrderItemPojo> itemList = orderItemService.getByOrder(order.getId());
        for (OrderItemPojo item : itemList) {

            InventoryPojo inventory = inventoryService.getByGlobalSkuId(item.getProduct().getGlobalSkuId());
            Long allocateQuantity = inventory.getAllocatedQuantity() - item.getOrderedQuantity();
            Long fulfilledQuantity = inventory.getFulfilledQuantity() + item.getOrderedQuantity();

            inventoryService.updateQuantities(inventory.getProduct().getGlobalSkuId(), inventory.getAvailableQuantity(), allocateQuantity,
                    fulfilledQuantity);

            orderItemService.update(item.getId(), item.getOrderedQuantity(), Long.valueOf(0),
                    item.getOrderedQuantity(), item.getSellingPrice());


        }
        orderService.updateStatus(order, OrderStatus.FULFILLED);
    }
}
