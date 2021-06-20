package com.phoenix.sso.controller;

import com.phoenix.common.utils.CommonResult;
import com.phoenix.common.utils.CookieUtils;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Value("${USER_TOKEN}")
    private String USER_TOKEN;

    @Value("${PORTAL_ADDRESS}")
    private String PORTAL_ADDRESS;

    /**
     * 展示登录页面
     * @return
     */
    @RequestMapping("login/page")
    public String page(Model model,String redirect){
        model.addAttribute("portalAddress",PORTAL_ADDRESS);
        model.addAttribute("redirect",redirect);
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = loginService.login(username, password);
        if(token!=null){
            //把token写入cookie
            CookieUtils.setCookie(request,response,USER_TOKEN,token);

            return CommonResult.ok(token);
        }else {
            return CommonResult.build(400,"用户名或者密码错误");
        }
    }


}
