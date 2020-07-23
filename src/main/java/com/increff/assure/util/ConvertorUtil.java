package com.increff.assure.util;

import java.util.ArrayList;
import java.util.List;

import com.increff.assure.model.ClientData;
import com.increff.assure.model.ClientForm;
import com.increff.assure.pojo.ClientPojo;

public class ConvertorUtil {

	public static ClientPojo convert(ClientForm form) {
		ClientPojo client=new ClientPojo();
		client.setName(form.getName());
		client.setType(form.getType());
		return client;
	}

	public static ClientData convert(ClientPojo pojo) {
		ClientData client = new ClientData();
		client.setId(pojo.getId());
		client.setName(pojo.getName());
		client.setType(pojo.getType());
		return client;
	}

	public static List<ClientData> convertClients(List<ClientPojo> pojos) {
		List<ClientData>list = new ArrayList<ClientData>();
		for(ClientPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}

	
	
}
