package com.jt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.ItemDesc;
import com.jt.service.ItemDescService;
import com.jt.util.ObjectMapperUtils;
import com.jt.utils.HttpClientService;
@Service
public class ItemDescServiceImpl implements ItemDescService {
	@Autowired
	private HttpClientService httpClientService;

	@Override
	public ItemDesc findItemDescById(long id) {
		String url = "http://manage.jt.com/web/item/findItemDescById";
		Map<String,String> params = new HashMap<>();
		params.put("id", id+"");
		String json = httpClientService.doGet(url,params);
		ItemDesc itemDesc = ObjectMapperUtils.toObject(json, ItemDesc.class);
		return itemDesc;
	}

}
