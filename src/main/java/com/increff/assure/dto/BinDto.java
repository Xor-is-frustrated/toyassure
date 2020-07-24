package com.increff.assure.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.increff.assure.model.BinData;
import com.increff.assure.pojo.BinPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.BinService;
import com.increff.assure.util.ConvertorUtil;

@Service
public class BinDto {
	@Autowired
	private BinService binService; 

	public BinData add() throws ApiException {
		BinPojo bin = binService.add();
		return ConvertorUtil.convert(bin);         
	}

	public BinData get(Long id) throws ApiException {
		BinPojo pojo = binService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<BinData> getAll() {
		List<BinPojo> list = binService.getAll();
		return ConvertorUtil.convertBins(list);
	}
	
}
