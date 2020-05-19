package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIDate;

public interface ItemService {

	EasyUIDate findPageObject(Integer page, Integer rows);

	void saveItem(Item item, ItemDesc itemDesc);

	void updateItem(Item item, ItemDesc itemDesc);

	void deleteItem(Long[] ids);

	void updateStatus(Long[] ids, int status);

	ItemDesc queryDesc(Long itemId);

	Item findItemById(long id);
	
}
