package com.increff.channel.controller;

import com.increff.channel.dto.OrderDto;
import com.increff.channel.service.ApiException;
import com.increff.commons.data.OrderData;
import com.increff.commons.form.OrderForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderDto dto;

    @ApiOperation(value = "Adds a order")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public OrderData add(@RequestBody OrderForm form) throws ApiException {
        return dto.add(form);
    }

    @ApiOperation(value = "Deletes a order")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) throws ApiException {
        dto.delete(id);
    }

    @ApiOperation(value = "Gets a order by id")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OrderData get(@PathVariable int id) throws ApiException {

        return dto.get(Long.valueOf(id));
    }

    @ApiOperation(value = "Gets list of all orders")
    @RequestMapping(path = "/external",method = RequestMethod.GET)
    public List<OrderData> getAll() {
        return dto.getAll();

    }

}
