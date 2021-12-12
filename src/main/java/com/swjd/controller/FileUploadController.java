package com.swjd.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
//文件上传
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	static final Logger logger = (Logger) LoggerFactory.getLogger("FileUploadController");

	@RequestMapping("/fileu")
	public String fileU() {
		return "fileUpload";
	}
@RequestMapping("/fileUpload")
public String Upload(@RequestParam("name") String name,@RequestParam("uploadfile") List<MultipartFile> uploadfile,HttpServletRequest request) {
		//判断上传的文件是否存在
	if(!uploadfile.isEmpty()&&uploadfile.size()>0) {
		//遍历上传的文件
		for(MultipartFile file:uploadfile) {
			//获取上传文件的名称
			String fileName=file.getOriginalFilename();
			logger.info(fileName);

			//___________________________
//			System.out.println(fileName);
			//设置文件上传的路径
			String dirPath="E:\\FileCenter\\test\\";
			//______________________________
			System.out.println(dirPath);
			logger.info(dirPath);
		File filePath =	new File(dirPath);
		//如果文件上传路径不存在则创建路径
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		//生成保存的新文件名=上传者名称+UUID+原文件名
		String newFileName=name+"_"+UUID.randomUUID()+"_"+fileName;
		try {
			file.transferTo(new File(dirPath + newFileName));
			//____________________________________________
			System.out.println(dirPath+newFileName);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		}
		return "success";
	}
	return "error";
}
}
