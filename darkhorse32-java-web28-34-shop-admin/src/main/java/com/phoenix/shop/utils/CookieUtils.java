package com.phoenix.shop.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

    public static Cookie getCookie(Cookie[] allCookie, String cookieName){
        if(cookieName==null){
            return null;
        }
        if(allCookie!=null){
            for(Cookie cookie:allCookie){
                if(cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }


}
