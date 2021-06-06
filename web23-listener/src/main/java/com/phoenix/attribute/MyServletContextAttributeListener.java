package com.phoenix.attribute;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        //获得放入域的属性
        System.out.println("在ServletContext域中添加属性：");
        System.out.println(servletContextAttributeEvent.getName());
        System.out.println(servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        //获得放入域的属性
        System.out.println("从ServletContext域中删除属性");
        System.out.println(servletContextAttributeEvent.getName());
        System.out.println(servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        //获得放入域的属性
        System.out.println("从ServletContext域中替换属性");
        System.out.println(servletContextAttributeEvent.getName());//获取修改前的name
        System.out.println(servletContextAttributeEvent.getValue());//获得修改前的value
    }
}
