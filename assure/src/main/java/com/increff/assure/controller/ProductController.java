package com.increff.assure.controller;

import java.util.List;

import javax.validation.Valid;

import com.increff.assure.dto.ProductDto;
import com.increff.assure.service.ApiException;
import com.increff.commons.data.ProductData;
import com.increff.commons.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@ApiOperation(value = "Gets a product by Client name and client sku id")
	@RequestMapping(path = "/clientskuandname", method = RequestMethod.POST)
	public ProductData get(@RequestBody ProductForm form) throws ApiException {

		return productDto.getByClientNameAndClientSkuId(form);
	}

	@ApiOperation(value = "Gets list of all products")
	@RequestMapping(method = RequestMethod.GET)
	public List<ProductData> getAll() {
		return productDto.getAll();

	}


	@ApiOperation(value = "Gets list of all products")
	@RequestMapping(path="/client/{name}",method = RequestMethod.GET)
	public List<ProductData> getByClientName(@PathVariable String name) {
		return productDto.getByClientName(name);

	}
	
	@ApiOperation(value = "Updates a product")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable int id, @RequestBody ProductForm form) throws ApiException {
		productDto.update(Long.valueOf(id),form);

	}

}
