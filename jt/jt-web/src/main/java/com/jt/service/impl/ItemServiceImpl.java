package com.jt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.util.ObjectMapperUtils;
import com.jt.utils.HttpClientService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private HttpClientService httpClientService;
	

	@Override
	public Item findItemById(long id) {
		String url = "http://manage.jt.com/web/item/findItemById";
		Map<String,String> params = new HashMap<>();
		params.put("id", id+"");
		String json = httpClientService.doGet(url,params);
		Item item = ObjectMapperUtils.toObject(json, Item.class);
		return item;
	}

}
