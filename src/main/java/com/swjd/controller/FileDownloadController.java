package com.swjd.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileDownloadController {
        @RequestMapping("/fileD")
        public String fileU() {
            return "download";
        }
        @RequestMapping("/download")
    public ResponseEntity<byte[]> fileDownload(String filename,HttpServletRequest request)throws Exception{
            //指定文件下载的路径
            String path = "D:/新建文件夹/upload/";
            //创建该文件对象 File.separator代表文件间隔符("/"或“\”,解决不同平台兼容性问题)
            File file = new File(path + File.separator + filename);
            //设置响应头
            HttpHeaders headers = new HttpHeaders();
            //通知浏览器以下载的方式打开文件
            headers.setContentDispositionFormData("attachment",filename);
            //定义以流的形式下载返回文件数据
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //使用Sting MVC框架的ResponseEntity对象封装返回下载数据
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers,
                    HttpStatus.OK);
        }
}
