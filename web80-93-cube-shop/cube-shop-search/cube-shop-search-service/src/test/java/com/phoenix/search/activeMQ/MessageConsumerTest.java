package com.phoenix.search.activeMQ;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;

public class MessageConsumerTest {

    @Test
    public void testQueueConsumer() throws IOException {
        //初始化spring 容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activeMQ.xml");

        //等待
        System.in.read();
    }


}
