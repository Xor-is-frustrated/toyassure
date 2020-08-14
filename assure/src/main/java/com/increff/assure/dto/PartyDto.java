package com.increff.assure.dto;

import java.util.List;

import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.util.ConvertorUtil;
import com.increff.commons.data.PartyData;
import com.increff.commons.form.PartyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.increff.assure.service.PartyService;

@Service
public class PartyDto {

	@Autowired
	private PartyService partyService;

	public PartyData add(PartyForm form) throws ApiException {
		PartyPojo pojo = ConvertorUtil.convert(form);
		PartyPojo client = partyService.add(pojo);
		return ConvertorUtil.convert(client);
	}

	public PartyData get(Long id) throws ApiException {
		PartyPojo pojo = partyService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<PartyData> getAll() {
		List<PartyPojo> list = partyService.getAll();
		return ConvertorUtil.convertClients(list);
	}

	public List<PartyData> getAllClients() {
		List<PartyPojo> list = partyService.getAllClients();
		return ConvertorUtil.convertClients(list);
	}

	public List<PartyData> getAllCustomers() {
		List<PartyPojo> list = partyService.getAllCustomers();
		return ConvertorUtil.convertClients(list);
	}
	
	public PartyData update(Long id, PartyForm form) throws ApiException {
		PartyPojo pojo = ConvertorUtil.convert(form);
		PartyPojo client = partyService.update(id,pojo.getName());
		return ConvertorUtil.convert(client);
	}

    public PartyData getByName(String name) throws ApiException{
		PartyPojo pojo = partyService.getByName(name);
		return ConvertorUtil.convert(pojo);
    }
}
