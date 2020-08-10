package com.increff.channel.dto;

import com.increff.channel.assure.Order;
import com.increff.channel.service.ApiException;
import com.increff.commons.data.OrderData;
import com.increff.commons.form.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderDto {

    @Autowired
    private Order order;

    public OrderData add(OrderForm form) throws ApiException {
        return order.addOrder(form);

    }

    public OrderData get(Long id) {
        return order.getOrder(id);
    }

    public List<OrderData> getAll() {
        return order.getAllChannelOrders();
    }

    public void delete(Long id) {
        order.deleteOrder(id);
    }
}
