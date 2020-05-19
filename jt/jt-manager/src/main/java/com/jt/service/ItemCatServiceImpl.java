package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.annotation.Cache_Find;
import com.jt.enu.KEY_ENUM;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;


@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private ItemCatMapper itemCatMapper;
	

	@Override
	public String findNameById(Integer itemCatId) {
		ItemCat selectById = itemCatMapper.selectById(itemCatId);
		return selectById.getName();
	}

	@Override
	@Cache_Find(key = "ITEM-CAT",keyType = KEY_ENUM.AUTO,seconds = 0)
	public List<EasyUITree> findList(Long parentId) {
		List<EasyUITree> treeList = new ArrayList<>();
		List<ItemCat> catList = getCatList(parentId);
		for(ItemCat cat : catList) {
			EasyUITree uiTree = new EasyUITree();
			uiTree.setId(cat.getId());
			uiTree.setText(cat.getName());
			String state = cat.getIsParent()?"closed":"open";
			uiTree.setState(state);
			treeList.add(uiTree);
		}
		return treeList;
	}

	private List<ItemCat> getCatList(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}

	


	
}
