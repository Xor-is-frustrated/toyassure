package com.increff.channel.dto;

import com.increff.channel.client.ClientAssure;
import com.increff.channel.dao.ChannelListingDao;
import com.increff.channel.pdf.PDFFromFOP;
import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.service.ApiException;
import com.increff.channel.service.ChannelListingService;
import com.increff.commons.data.*;
import com.increff.commons.form.OrderInvoice;
import com.increff.commons.form.OrderItemFormChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrderItemDto {

    @Autowired
    private ClientAssure clientAssure;

    @Autowired
    private ChannelListingDao channelListingDao;


    public void addByChannel(OrderItemFormChannel form) throws ApiException {

        OrderItemData data=clientAssure.addOrderItem(form);
        if(data==null){
            OrderData order= clientAssure.getOrder(form.getOrderId());
            ChannelListingPojo p=channelListingDao.selectByChannelSkuIdAndChannelId(form.getChannelSkuId(),
                    order.getChannelId());
            if(p==null){
                throw new ApiException("ChannelSkuId and Channel Name pair does not exist");

            }
            else{
                throw new ApiException("ChannelSkuId and Channel Name pair already exists. Update the item.");
            }


        }
    }

    public OrderItemData get(Long id) {
        return clientAssure.getOrderItem(id);
    }

    public List<OrderItemData> getByOrder(Long id) {
        return clientAssure.getItemsByOrderId(id);
    }

    public void update(Long id, OrderItemFormChannel form) {
       clientAssure.updateOrderItem(id,form);
    }

    public void delete(Long id) {
        clientAssure.deleteOrderItem(id);
    }

    public void fulfillAndGenerateInvoice(Long id, HttpServletResponse response) throws JAXBException, IOException {

        List<OrderItemData> orderItemData= clientAssure.getItemsByOrderId(id);
        OrderData orderData= clientAssure.getOrder(id);
        double sellingPrice=0;
        for(OrderItemData item:orderItemData){
            sellingPrice+=item.getSellingPrice()*item.getOrderedQuantity();
        }
        sellingPrice= Math.round(sellingPrice * 100.0) / 100.0;
        OrderInvoice invoice = new OrderInvoice();
        String channelName= orderData.getChannelName();
        channelName=channelName.substring(0,1).toUpperCase()+channelName.substring(1).toLowerCase()+" Invoice";
        invoice.setChannelName(channelName);
        invoice.setItems(orderItemData);
        invoice.setSellingprice(sellingPrice);
        invoice.setId(orderData.getId());
        invoice.setCustomer(orderData.getCustomerName());
        invoice.setClient(orderData.getClientName());

        invoice.setOrderId(orderData.getChannelOrderId());
        invoice.setOrderDate(ZonedDateTime.now().toString().substring(0,10));
        // converting java object to XML
        PDFFromFOP.javaToXML(invoice);

        // converting XML to PDF
        PDFFromFOP.generatePDF(id);

        // writing back the PDF to HttpResponse
        PDFFromFOP.generateResponse(response);


    }

    public List<ChannelData> getChannels() {
        return clientAssure.getAllChannels();

    }

    public List<PartyData> getClients() {
        return clientAssure.getOnlyClients();

    }
    public List<PartyData> getCustomers() {
        return clientAssure.getOnlyCusotmers();

    }

}
