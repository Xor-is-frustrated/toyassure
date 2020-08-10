package com.increff.channel.dto;

import com.increff.channel.assure.Order;
import com.increff.channel.assure.OrderItem;
import com.increff.channel.pdf.PDFFromFOP;
import com.increff.channel.service.ApiException;
import com.increff.commons.data.OrderData;
import com.increff.commons.data.OrderItemData;
import com.increff.commons.form.OrderInvoice;
import com.increff.commons.form.OrderItemFormChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class OrderItemDto {

    @Autowired
    private OrderItem orderItem;

    @Autowired
    private Order order;


    public void addByChannel(OrderItemFormChannel form) throws ApiException {
        orderItem.addOrderItem(form);
    }

    public OrderItemData get(Long id) {
        return orderItem.getOrderItem(id);
    }

    public List<OrderItemData> getByOrder(Long id) {
        return orderItem.getitemsByOrderId(id);
    }

    public void update(Long id, OrderItemFormChannel form) {
       orderItem.updateOrderItem(id,form);
    }

    public void delete(Long id) {
        orderItem.deleteOrderItem(id);
    }

    public void fulfillAndGenerateInvoice(Long id, HttpServletResponse response) throws JAXBException, IOException {

        List<OrderItemData> orderItemData=orderItem.getitemsByOrderId(id);
        OrderData orderData= order.getOrder(id);
        double sellingPrice=0;
        for(OrderItemData item:orderItemData){
            sellingPrice+=item.getSellingPrice()*item.getOrderedQuantity();
        }
        sellingPrice= Math.round(sellingPrice * 100.0) / 100.0;
        OrderInvoice invoice = new OrderInvoice();
        invoice.setChannelName(orderData.getChannelName());
        invoice.setItems(orderItemData);
        invoice.setSellingprice(sellingPrice);
        invoice.setId(orderData.getId());

        invoice.setOrderId(orderData.getChannelOrderId());
//        invoice.setOrderDate(orderData.getCreatedDate().toString());
        // converting java object to XML
        PDFFromFOP.javaToXML(invoice);

        // converting XML to PDF
        PDFFromFOP.generatePDF(id);

        // writing back the PDF to HttpResponse
        PDFFromFOP.generateResponse(response);


    }
}
