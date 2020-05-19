package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.annotation.Cache_Find;
import com.jt.enu.KEY_ENUM;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat/")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("queryItemName")
	public String queryItemName(Integer itemCatId) {
		return itemCatService.findNameById(itemCatId);
	}
	
	@RequestMapping("list")
	public List<EasyUITree> list(@RequestParam(value = "id",defaultValue = "0")Long parentId) {
		return itemCatService.findList(parentId);
		//return itemCatService.findByCache(parentId);
	}
}
