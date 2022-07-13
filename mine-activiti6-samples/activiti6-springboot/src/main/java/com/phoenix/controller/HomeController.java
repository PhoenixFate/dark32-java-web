package com.phoenix.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenix
 * @version 1.0.0
 * @date 2022/7/13 14:48
 */
@RestController
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        return "hello home";
    }


}
