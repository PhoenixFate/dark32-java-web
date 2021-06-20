package com.phoenix.activeMQ;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class ActiveMQSpringTest {

    @Test
    public void test01(){
        //初始化spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activeMQ.xml");
        //获得jmsTemplate对象
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        //获得Destination对象
        ActiveMQQueue  activeMQQueue= applicationContext.getBean(ActiveMQQueue.class);
        //发送消息
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("spring send queue message");
            }
        });
    }


}
