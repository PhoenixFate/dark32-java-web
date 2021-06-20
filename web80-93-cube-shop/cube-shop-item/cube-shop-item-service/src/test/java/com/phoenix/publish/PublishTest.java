package com.phoenix.publish;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class PublishTest {


    /**
     * item-service打包成war只是便于部署，根tomcat没有关系
     * item-service 本质只需要加载Spring容器就可以了
     *
     */
    //@Test
    public void test01(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//        while (true){
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        // 等同于上面while true
        System.out.println("服务已经启动");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务已经关闭 ");
    }


}
