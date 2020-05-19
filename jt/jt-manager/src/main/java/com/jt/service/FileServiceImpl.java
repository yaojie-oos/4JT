package com.jt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.ImageVo;

@Service
@PropertySource("classpath:properties/Image.properties")
//@ConfigurationProperties(prefix = "image")
public class FileServiceImpl implements FileService {
	//定义本地磁盘路径
	@Value("${image.localDirPath}")
	private String localDirPath;
	//定义虚拟路径名称
	@Value("${image.urlPath}")
	private String urlPath;
	/**
	 * 1.获取图片的名称
	 * 2.校验是否为图片的类型
	 * 3.校验是否为恶意程序，如：木马.exe.jpg
	 * 4.准备文件夹，分文件保存 按照时间存储:yyyy/MM/dd
	 * 5.防止文件重名，UUID 32位16进制数+毫秒数
	 * @throws IOException 
	 */
	@Override
	public ImageVo uploadFile(MultipartFile uploadFile) {
		ImageVo imageVo = new ImageVo();
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase();
		//使用正则表达式判断
		if(!fileName.matches(".+\\.(jpg|png|gif)")) {
			imageVo.setError(1);
			return imageVo;
		}
		
		try {
			//判断是否为恶意程序 图片模板
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if(width==0||height==0) {
				imageVo.setError(1);
				return imageVo;
			}
			//时间转化为字符串
			String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			
			//准备文件夹
			String localDir = localDirPath+dateDir;
			
			File dirFile = new File(localDir);
			if(!dirFile.exists()) {
				//如果文件夹不存在则创建文件夹
				dirFile.mkdirs();
			}
			
			//使用UUID定义文件名称
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			
			//拼接新的文件名称
			String realLocalPath = localDir+"/"+uuid+fileType;
			
			
			//完成文件上传
			uploadFile.transferTo(new File(realLocalPath));
			
			//拼接url路径
			String realUrlPath = urlPath+dateDir+"/"+uuid+fileType;
			//将文件信息回传给页面
			imageVo.setError(0)
			.setHeight(height)
			.setWidth(width)
			.setUrl(realUrlPath);
			
		} catch (Exception e) {
			e.printStackTrace();
			imageVo.setError(1);
			return imageVo;
		}
		

		return imageVo;
	}

}
