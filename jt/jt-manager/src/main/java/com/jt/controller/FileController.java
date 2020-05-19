package com.jt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.ImageVo;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	//当用户上传时重定向到上传页面
	
	/**
	 * 思路：
	 * 1.获取文件信息包含文件名称
	 * 2.指定文件上传的路径
	 * 3.实现文件上传
	 * @param fileImage
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/file")
	public String file(MultipartFile fileImage ) throws IllegalStateException, IOException {
		
		//获取文件名称
		String fileName = fileImage.getOriginalFilename();
		
		//定义文件夹路径
		File fileDir = new File("F:/4京淘/jt/jt-manager/src/main/webapp/image");
		if(!fileDir.exists()) {
			//创建文件夹
			fileDir.mkdirs();
		}
		
		//实现文件上传
		fileImage.transferTo(new File("F:/4京淘/jt/jt-manager/src/main/webapp/image/"+fileName));
		return "redirect:/file.jsp";
	}
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	//实现文件上传
	public ImageVo fileUpload(MultipartFile uploadFile) {
		return fileService.uploadFile(uploadFile);
	}
	
}
