package com.phoenix.sso.controller;

import com.phoenix.common.utils.CommonResult;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 注册功能controller
 */
@Controller
@RequestMapping("user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Value("${PORTAL_ADDRESS}")
    private String PORTAL_ADDRESS;

    /**
     * 展示注册页面
     * @return
     */
    @RequestMapping("/register/page")
    public String page(Model model){
        model.addAttribute("portalAddress",PORTAL_ADDRESS);
        return "register";
    }

    /**
     * 展示注册页面
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("user/register");
        System.out.println(user);
        Boolean register = registerService.register(user);
        if(register){
            return CommonResult.ok();
        }else {
            return CommonResult.error("服务器出错");
        }
    }


}
