package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.annotation.Cache_Find;
import com.jt.enu.KEY_ENUM;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUIDate;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/item/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("query")
	public EasyUIDate findPageObject(Integer page,Integer rows) {
		return itemService.findPageObject(page,rows);
	}
	
	@RequestMapping("save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		try {
			itemService.saveItem(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
		try {
			itemService.updateItem(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("delete")
	public SysResult deleteItem(Long[] ids) {
		try {
			itemService.deleteItem(ids);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("reshelf")
	public SysResult reshelfItem(Long[] ids) {
		try {
			int status = 1;
			itemService.updateStatus(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("instock")
	public SysResult instockItem(Long[] ids) {
		try {
			int status = 2;
			itemService.updateStatus(ids, status);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("query/item/desc/{itemId}")
	public SysResult queryDesc(@PathVariable Long itemId) {
		try {
			ItemDesc data = itemService.queryDesc(itemId);
			return SysResult.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
}
