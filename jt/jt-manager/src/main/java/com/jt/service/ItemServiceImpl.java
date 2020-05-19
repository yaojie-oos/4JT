package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIDate;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIDate findPageObject(Integer page, Integer rows) {
		int total = itemMapper.selectCount(null);
		int start = (page-1)*rows;
		List<Item> list = itemMapper.findPageObject(start,rows);
		return new EasyUIDate(total,list); 
		
	}
	
	@Transactional//添加事务控制
	@Override
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1)
		.setCreated(new Date())
		.setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setItemId(item.getId())
		.setCreated(item.getCreated())
		.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}
	
	@Transactional
	@Override
	public void updateItem(Item item,ItemDesc itemDesc) {
		itemDesc.setItemId(item.getId())
		.setUpdated(item.getCreated())
		.setCreated(item.getCreated());
		itemDescMapper.updateById(itemDesc);
		item.setCreated(new Date());
		itemMapper.updateById(item);
		
	}

	@Override
	public void deleteItem(Long[] ids) {
		itemMapper.deleteBatchIds(Arrays.asList(ids));
		//itemMapper.deleteItem(ids);
		itemDescMapper.deleteBatchIds(Arrays.asList(ids));
	}

	@Override
	public void updateStatus(Long[] ids, int status) {
		Item entity = new Item();
		entity.setStatus(status);
		entity.setUpdated(new Date());
		QueryWrapper<Item> queryWrapper = new QueryWrapper<Item>();
		queryWrapper.in("id", Arrays.asList(ids));
		itemMapper.update(entity, queryWrapper);
		
	}

	@Override
	public ItemDesc queryDesc(Long itemId) {
		ItemDesc sysDesc = itemDescMapper.selectById(itemId);
		return sysDesc;
	}

	@Override
	public Item findItemById(long id) {
		return itemMapper.selectById(id);
	}

	



	
	
	
	
	
	
	
	
}
