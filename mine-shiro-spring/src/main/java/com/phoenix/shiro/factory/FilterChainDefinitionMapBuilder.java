package com.phoenix.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    /**
     * /login.jsp = anon
     * /shiro/login = anon
     * /shiro/logout = logout-->
     * /user.jsp = roles[user]
     * /admin.jsp = roles[admin]-->
     * /** = authc
     *
     * @return map
     */
    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //访问数据库，进行初始化
        map.put("/login.jsp", "anon");
        map.put("/shiro/login", "anon");
        map.put("/shiro/logout", "logout");
        map.put("/user.jsp", "authc,roles[user]"); //必须通过认证，"记住我"不能访问
        map.put("/admin.jsp", "authc,roles[admin]"); //必须通过认证，"记住我"不能访问
        map.put("/list.jsp", "user"); //记住我 和 认证都可以方法
        map.put("/**", "authc");
        return map;
    }


}
