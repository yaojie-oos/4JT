package com.jt.service;

import java.util.List;

import com.jt.vo.EasyUITree;

public interface ItemCatService {

	String findNameById(Integer itemCatId);

	List<EasyUITree> findList(Long parentId);

	

	


}
