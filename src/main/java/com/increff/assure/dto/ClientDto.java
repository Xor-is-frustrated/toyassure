package com.increff.assure.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.increff.assure.model.ClientData;
import com.increff.assure.model.ClientForm;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ClientService;
import com.increff.assure.util.ConvertorUtil;

@Service
public class ClientDto {

	@Autowired
	private ClientService clientService;

	public ClientData add(ClientForm form) throws ApiException {
		ClientPojo pojo = ConvertorUtil.convert(form);
		ClientPojo client = clientService.add(pojo);
		return ConvertorUtil.convert(client);
	}

	public ClientData get(Long id) throws ApiException {
		ClientPojo pojo = clientService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<ClientData> getAll() {
		List<ClientPojo> list = clientService.getAll();
		return ConvertorUtil.convertClients(list);
	}
	
	public ClientData update(Long id, ClientForm form) throws ApiException {
		ClientPojo pojo = ConvertorUtil.convert(form);
		ClientPojo client = clientService.update(id,pojo);
		return ConvertorUtil.convert(client);
	}

}
