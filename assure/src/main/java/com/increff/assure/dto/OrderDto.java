package com.increff.assure.dto;

import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.OrderItemPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.service.*;
import com.increff.assure.util.ConvertorUtil;
import com.increff.commons.data.OrderData;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.form.OrderForm;
import com.increff.commons.form.OrderSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDto {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PartyService partyService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private OrderItemService orderItemService;

    public OrderData add(OrderForm form) throws ApiException {
        PartyPojo client = partyService.getByName(form.getClientName());
        PartyPojo customer = partyService.getByName(form.getCustomerName());
        ChannelPojo channel = channelService.getByName(form.getChannelName());
        OrderPojo pojo = ConvertorUtil.convert(form, channel, client, customer);
        OrderPojo order = orderService.add(pojo);
        return ConvertorUtil.convert(order, form.getChannelName());
    }

    public OrderData get(Long id) throws ApiException {
        OrderPojo pojo = orderService.get(id);
        ChannelPojo channel = channelService.get(pojo.getChannelId());
        return ConvertorUtil.convert(pojo, channel.getName());
    }

    public List<OrderData> getAll() throws ApiException {
        List<OrderPojo> list = orderService.getAll();
        List<String> channelNames = new ArrayList<String>();
        for (OrderPojo pojo : list) {
            channelNames.add(channelService.get(pojo.getChannelId()).getName());
        }
        return ConvertorUtil.convertOrders(list, channelNames);
    }

    public List<OrderData> getOrderBySearch(OrderSearchForm form) throws ApiException {
        String channelName = form.getChannelName();
        String orderStatus = form.getOrderStatus();

        List<String> channelNames = new ArrayList<String>();
        if (channelName == "" && orderStatus == "") {
            return getAll();
        } else if (channelName == "") {
            OrderStatus status;
            if (orderStatus.equals("CREATED")) {
                status = OrderStatus.CREATED;
            } else if (orderStatus.equals("ALLOCATED")) {
                status = OrderStatus.ALLOCATED;
            } else {
                status = OrderStatus.FULFILLED;
            }

            List<OrderPojo> list = orderService.getByOrderStatus(status);
            for (OrderPojo pojo : list) {
                channelNames.add(channelService.get(pojo.getChannelId()).getName());
            }
            return ConvertorUtil.convertOrders(list, channelNames);

        } else if (orderStatus == "") {
            Long channelId = channelService.getByName(channelName).getId();
            List<OrderPojo> list = orderService.getByChannelId(channelId);
            for (OrderPojo pojo : list) {
                channelNames.add(channelService.get(pojo.getChannelId()).getName());
            }
            return ConvertorUtil.convertOrders(list, channelNames);

        } else {

            OrderStatus status;
            if (orderStatus.equals("CREATED")) {
                status = OrderStatus.CREATED;
            } else if (orderStatus.equals("ALLOCATED")) {
                status = OrderStatus.ALLOCATED;
            } else {
                status = OrderStatus.FULFILLED;
            }

            Long channelId = channelService.getByName(channelName).getId();
            List<OrderPojo> list = orderService.getBySearch(channelId, status);
            for (OrderPojo pojo : list) {
                channelNames.add(channelService.get(pojo.getChannelId()).getName());
            }
            return ConvertorUtil.convertOrders(list, channelNames);

        }

    }

    public List<OrderData> getOrderBySearchExternal(OrderSearchForm form) throws ApiException {
        String channelName = form.getChannelName();
        String orderStatus = form.getOrderStatus();

        List<String> channelNames = new ArrayList<String>();
        if (channelName == "" && orderStatus == "") {
            return getExternalOrders();
        } else if (channelName == "") {
            OrderStatus status;
            if (orderStatus.equals("CREATED")) {
                status = OrderStatus.CREATED;
            } else if (orderStatus.equals("ALLOCATED")) {
                status = OrderStatus.ALLOCATED;
            } else {
                status = OrderStatus.FULFILLED;
            }

            List<OrderPojo> list = orderService.getByOrderStatusExternal(status);
            for (OrderPojo pojo : list) {
                channelNames.add(channelService.get(pojo.getChannelId()).getName());
            }
            return ConvertorUtil.convertOrders(list, channelNames);

        } else if (orderStatus == "") {
            Long channelId = channelService.getByName(channelName).getId();
            List<OrderPojo> list = orderService.getByChannelId(channelId);
            for (OrderPojo pojo : list) {
                channelNames.add(channelService.get(pojo.getChannelId()).getName());
            }
            return ConvertorUtil.convertOrders(list, channelNames);

        } else {

            OrderStatus status;
            if (orderStatus.equals("CREATED")) {
                status = OrderStatus.CREATED;
            } else if (orderStatus.equals("ALLOCATED")) {
                status = OrderStatus.ALLOCATED;
            } else {
                status = OrderStatus.FULFILLED;
            }

            Long channelId = channelService.getByName(channelName).getId();
            List<OrderPojo> list = orderService.getBySearch(channelId, status);
            for (OrderPojo pojo : list) {
                channelNames.add(channelService.get(pojo.getChannelId()).getName());
            }
            return ConvertorUtil.convertOrders(list, channelNames);

        }

    }


    public List<OrderData> getExternalOrders() throws ApiException {
        ChannelPojo channel=channelService.getByName("INTERNAL");
        List<OrderPojo> orders = orderService.getExternalOrders(channel.getId());
        List<String> channelNames = new ArrayList<String>();
        for (OrderPojo pojo : orders) {
            channelNames.add(channelService.get(pojo.getChannelId()).getName());
        }
        return ConvertorUtil.convertOrders(orders, channelNames);

    }

    public void delete(Long id) throws ApiException {
        OrderPojo order = orderService.get(id);
        List<OrderItemPojo> list = orderItemService.getByOrder(order.getId());
        for (OrderItemPojo item : list) {
            orderItemService.delete(item.getId());
        }
        orderService.delete(order.getId());

    }

    public OrderData getByOrderId(String channelOrderId) throws ApiException {
        OrderPojo pojo = orderService.getByChannelOrderId(channelOrderId);
        ChannelPojo channel = channelService.get(pojo.getChannelId());
        return ConvertorUtil.convert(pojo, channel.getName());
    }
}
