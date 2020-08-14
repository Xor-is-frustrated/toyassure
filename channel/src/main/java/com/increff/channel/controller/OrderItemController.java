package com.increff.channel.controller;

import com.increff.channel.dto.OrderItemDto;
import com.increff.channel.service.ApiException;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.OrderItemData;
import com.increff.commons.data.PartyData;
import com.increff.commons.form.OrderItemFormChannel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {

    @Autowired
    private OrderItemDto dto;

    @ApiOperation(value = "Adds an orderItem through channel")
    @RequestMapping(path = "/channel", method = RequestMethod.POST)
    public void addByChannel(@RequestBody OrderItemFormChannel form) throws ApiException {
        dto.addByChannel(form);
    }

    @ApiOperation(value = "Gets an orderItem by id")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OrderItemData get(@PathVariable Long id) throws ApiException {
        return dto.get(id);
    }

    @ApiOperation(value = "Gets orderItems list by orderid")
    @RequestMapping(path = "/order/{id}", method = RequestMethod.GET)
    public List<OrderItemData> getByOrder(@PathVariable Long id) throws ApiException {
        return dto.getByOrder(id);
    }

    @ApiOperation(value = "Gets orderItems list by orderid")
    @RequestMapping(path = "/channels", method = RequestMethod.GET)
    public List<ChannelData> getChannels() throws ApiException {
        return dto.getChannels();
    }

    @ApiOperation(value = "Gets orderItems list by orderid")
    @RequestMapping(path = "/clients", method = RequestMethod.GET)
    public List<PartyData> getClients() throws ApiException {
        return dto.getClients();
    }

    @ApiOperation(value = "Gets orderItems list by orderid")
    @RequestMapping(path = "/customers", method = RequestMethod.GET)
    public List<PartyData> getCustomers() throws ApiException {
        return dto.getCustomers();
    }


    @ApiOperation(value = "Updates an item")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody OrderItemFormChannel form) throws ApiException {
        dto.update(id, form);
    }

    @ApiOperation(value = "Deletes an item")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) throws ApiException {
        dto.delete(id);
    }

    @ApiOperation(value = "Fulfills all order items of an order id")
    @RequestMapping(path = "/fulfill/{id}", method = RequestMethod.GET)
    public void fulfill(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
            throws ApiException, JAXBException, IOException {
        dto.fulfillAndGenerateInvoice(id, response);

    }

}
