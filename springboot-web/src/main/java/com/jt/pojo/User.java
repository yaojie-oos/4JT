package com.jt.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)//链式加载
@NoArgsConstructor//无参构造
@AllArgsConstructor//有参构造
@TableName("user")//对象与数据表完成映射
public class User implements Serializable{
	private static final long serialVersionUID = -6161009932524706762L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
	
	
}
