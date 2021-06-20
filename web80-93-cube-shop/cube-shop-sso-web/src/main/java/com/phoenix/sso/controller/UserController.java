package com.phoenix.sso.controller;

import com.phoenix.common.utils.CommonResult;
import com.phoenix.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("phone/{phone}")
    @ResponseBody
    public CommonResult checkPhone(@PathVariable String phone){
        Boolean b = userService.checkPhone(phone);
        if(b){
            return CommonResult.ok();
        }else {
            return CommonResult.error("该用户名已经被使用");
        }
    }

    @RequestMapping("email/{email}")
    @ResponseBody
    public CommonResult checkEmail(@PathVariable String email){
        Boolean b = userService.checkEmail(email);
        if(b){
            return CommonResult.ok();
        }else {
            return CommonResult.error("该用户名已经被使用");
        }
    }

    @RequestMapping("username/{username}")
    @ResponseBody
    public CommonResult checkUsername(@PathVariable String username){
        Boolean b = userService.checkUsername(username);
        if(b){
            return CommonResult.ok();
        }else {
            return CommonResult.error("该用户名已经被使用");
        }
    }

}
