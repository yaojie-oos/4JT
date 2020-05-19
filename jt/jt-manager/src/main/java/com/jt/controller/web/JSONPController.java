package com.jt.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtils;

@RestController
public class JSONPController {
	//@RequestMapping("/web/testJSONP")
	public String testJSONP(String callback) {
		ItemCat item = new ItemCat();
		item.setId(1L);
		String js = ObjectMapperUtils.toJson(item);
		return callback+"("+js+")";
		
	}
	
	@RequestMapping("/web/testJSONP")
	public JSONPObject JSONP(String callback) {
		ItemCat item = new ItemCat();
		item.setId(1L);
		JSONPObject jsonpObject = new JSONPObject(callback, item);
		return jsonpObject;
	}
}
