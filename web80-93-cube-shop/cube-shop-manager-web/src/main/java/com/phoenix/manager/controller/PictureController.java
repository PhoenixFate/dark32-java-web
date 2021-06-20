package com.phoenix.manager.controller;

import com.phoenix.common.utils.FastDFSClient;
import com.phoenix.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("picture")
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    /**
     * 返回值如果是String ，则返回的contentType是 text/plain
     * 返回值如果是其他对象，如Map等等，则返回的contextType是 application/json
     * @param multipartFile
     * @return
     */
    // 设置返回的contentType        text/plain;charset=utf-8
    @RequestMapping(value = "upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String upload(@RequestParam(name = "uploadFile") MultipartFile multipartFile){
        Map<String,Object> result=new HashMap<>();
        try {
            // 把图片上传到图片服务器
            FastDFSClient fastDFSClient=new FastDFSClient("conf/client2.conf");
            // 取文件扩展名
            String originalFilename = multipartFile.getOriginalFilename();
            String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            // 得到一个图片的地址和文件名
            String url = fastDFSClient.uploadFile(multipartFile.getBytes(), extName);
            // 补充为完整的url
            url=IMAGE_SERVER_URL+url;
            System.out.println("url: "+url);
            // 封装到map中返回
            result.put("error",0);
            result.put("url",url);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error",1);
            result.put("message","上传图片失败");
        }
        return JsonUtils.objectToJson(result);
    }

}
