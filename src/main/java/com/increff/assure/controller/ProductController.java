package com.increff.assure.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.ProductDto;
import com.increff.assure.model.ProductData;
import com.increff.assure.model.ProductForm;
import com.increff.assure.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductDto productDto;

	@ApiOperation(value = "Adds a product")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void add(@RequestBody @Valid ProductForm form) throws ApiException {
		productDto.add(form);
	}

	@ApiOperation(value = "Gets a product by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ProductData get(@PathVariable int id) throws ApiException {

		return productDto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets list of all products")
	@RequestMapping(method = RequestMethod.GET)
	public List<ProductData> getAll() {
		return productDto.getAll();

	}

}
