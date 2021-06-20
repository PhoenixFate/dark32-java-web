package com.phoenix.sso.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.phoenix.common.utils.CommonResult;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class TokenController {

    @Autowired
    private TokenService tokenService;

//    @RequestMapping(value = "token/{token}",produces = "application/json;charset=utf-8")
    @RequestMapping(value = "token/{token}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String  getToken(@PathVariable String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = tokenService.getUserByToken(token);
        String callback = request.getParameter("callback");
        System.out.println("callback: "+callback);
        String result="";
        if(user!=null){
            result=JSON.json(CommonResult.ok(user));
        }else {
            result=JSON.json(CommonResult.build(400,"token已过期"));
        }
        //判断是否为jsonp请求
        if(StringUtils.isBlank(callback)){
            return result;
        }else {
            return  callback+"("+result+")";
        }
    }

    @RequestMapping(value = "token/new/{token}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object  getTokenNew(@PathVariable String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = tokenService.getUserByToken(token);
        String callback = request.getParameter("callback");
        String result="";
        if(user!=null){
            result=JSON.json(CommonResult.ok(user));
        }else {
            result=JSON.json(CommonResult.build(400,"token已过期"));
        }
        //判断是否为jsonp请求
        if(StringUtils.isBlank(callback)){
            return result;
        }else {
            //spring 4.2之后支持
            // 就是把返回值拼装成jsonp支持的形式：  callback+"("+result+")";
            MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return  mappingJacksonValue;
        }
    }

}
