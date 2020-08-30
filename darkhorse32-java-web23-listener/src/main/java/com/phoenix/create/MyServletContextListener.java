package com.phoenix.create;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MyServletContextListener implements ServletContextListener {
    //本监听器的作用：创建或加载一些初始化对象：加载数据库驱动、连接池的初始化
    //做一些准备工作：加载一些初始化的配置文件：spring的配置文件
    //开启任务调度：定时器
    @Override
    //监听context域对象的创建
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获得被监听的对象
        ServletContext servletContext = servletContextEvent.getServletContext();
        //通用方法，获得被监听的对象
        Object source = servletContextEvent.getSource();
        System.out.println("context创建");

        //开启一个计息的任务调度
        Timer timer=new Timer();
        //tast:任务
        //firsttime第一次执行时间
        //间隔执行时间
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("银行计息了。。。。");
            }
        },new Date(),5000000);
    }



    @Override
    //监听context域对象的销毁
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("context销毁");
    }
}
