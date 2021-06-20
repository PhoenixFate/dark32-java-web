package com.phoenix.common.utils;

public class Test {

    public static void main(String[] args) {
        String[] domains = "sso.cube-shop.phoenix-jd.com".split("\\.");
        int len = domains.length;
        String domainName="";
        if (len > 3) {
            // www.xxx.com.cn
            domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
        } else if (len > 1) {
            // xxx.com or xxx.cn
            domainName = "." + domains[len - 2] + "." + domains[len - 1];
        } else {
            //domainName = serverName;
        }
        System.out.println(domainName);
    }

}
