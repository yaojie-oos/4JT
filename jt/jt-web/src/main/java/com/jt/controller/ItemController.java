package com.jt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemDescService;
import com.jt.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	

	/**
	 * 根据商品id查询后台服务器
	 * 1.在前台service中实现httpClient调用
	 * 2.后台根据id查询数据库返回json串
	 * 3.前台将json转化为item对象
	 * 4.将item对象保存到request域中
	 * 5.返回页面的逻辑名称
	 */
	@RequestMapping("/items/{id}")
	public String findItemById(@PathVariable long id,Model model) {
		Item item = itemService.findItemById(id);
		model.addAttribute("item", item);
		ItemDesc itemDesc = itemDescService.findItemDescById(id);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}
