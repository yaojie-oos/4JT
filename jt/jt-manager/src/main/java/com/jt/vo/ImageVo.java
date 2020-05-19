package com.jt.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class ImageVo implements Serializable{
	private static final long serialVersionUID = 2976811462153027673L;
	private Integer error;
	private String url;
	private Integer width;
	private Integer height;
}
 