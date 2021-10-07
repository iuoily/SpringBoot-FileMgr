package com.swjd.controller;

import java.io.File;
import java.net.URLEncoder;
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
            String path = "C:\\Users\\Administrator\\Desktop\\";
            //创建该文件对象 File.separator代表文件间隔符("/"或“\”,解决不同平台兼容性问题)
            File file = new File(path + File.separator + filename);
            //对文件名编码，防止中文乱码
            filename = this.getFilename(request,filename);
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
    /**
     * 根据浏览器的不同进行编码设置，返回编码后的文件名
     */
    public String getFilename(HttpServletRequest request,String filename)throws Exception{
        //IE不同版本User-Agent中出现的关键词
        String[] IEBrowserKeyWords={"MSID","Trident","Edge"};
        //获取请求头信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTDF-8显示
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
            return new String(filename.getBytes("UTF-8"),"ISO-8859-1");
    }
}
