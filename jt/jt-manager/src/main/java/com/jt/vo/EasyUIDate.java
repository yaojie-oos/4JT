package com.jt.vo;

import java.io.Serializable;
import java.util.List;

import com.jt.pojo.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@Accessors
@NoArgsConstructor
@AllArgsConstructor
//该类展现表格数据
public class EasyUIDate implements Serializable{
	private static final long serialVersionUID = -3894727935419208210L;
	private Integer total;
	private List<Item> rows;
}
