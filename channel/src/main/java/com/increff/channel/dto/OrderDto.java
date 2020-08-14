package com.increff.channel.dto;

import com.increff.channel.client.ClientAssure;
import com.increff.channel.service.ApiException;
import com.increff.commons.data.OrderData;
import com.increff.commons.form.OrderForm;
import com.increff.commons.form.OrderSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderDto {

    @Autowired
    private ClientAssure clientAssure;

    public OrderData add(OrderForm form) throws ApiException {
        OrderData order= clientAssure.getOrderByOrderId(form.getChannelOrderId());
        if(order!=null){
            throw new ApiException("Channel Order Id already exists.");
        }
        return clientAssure.addOrder(form);

    }

    public OrderData get(Long id) {
        return clientAssure.getOrder(id);
    }

    public List<OrderData> getAll() {
        return clientAssure.getAllChannelOrders();
    }

    public void delete(Long id) {
        clientAssure.deleteOrder(id);
    }

    public List<OrderData> getOrderBySearch(OrderSearchForm form) {
       return clientAssure.getOrderBySearch(form);

    }
}
