package com.phoenix.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

public class ShiroService {

    //一般shiro的注解添加在controller层
    // @RequiresRoles({"admin"})
    public void testShiroAnnotation() {
        System.out.println("service test annotation");
        Session session = SecurityUtils.getSubject().getSession();
        Object key = session.getAttribute("key");
        System.out.println(key);
    }

}
