package com.phoenix.activeMQ;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import javax.jms.*;
import java.io.IOException;

public class ActiveMQTest {

    private String ActiveMQAliyunUrl="tcp://47.99.113.229:61616";

    private String ActiveMQDellUrl="tcp://114.67.89.253:19027";

    /**
     * queue 点到点
     */
    @Test
    public void testQueueProducer() throws JMSException {
        //1. 创建连接工厂对象，需要指定服务到ip及端口
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(ActiveMQDellUrl);
        //2。使用工厂对象创建connection对象
        Connection connection = connectionFactory.createConnection();
        //3。开启连接 调用connection到start方法
        connection.start();
        //4。 创建一个session对象
        //第一个参数：是否开启事务；一般不开启事务，如果开启事务，第二个参数无意义
        //第二个参数：应答模式：（自动应答，手动应答）一般自动应答
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5。使用session对象创建Destination对象，两种形式queue，topic
        Queue queue = session.createQueue("test-queue");
        //6。使用session创建producer对象
        MessageProducer producer = session.createProducer(queue);
        //7。创建一个消息message对象（TextMessage）
//        TextMessage textMessage=new ActiveMQTextMessage();
//        textMessage.setText("hello");
        TextMessage textMessage = session.createTextMessage("hello");
        //8。发送消息
        producer.send(textMessage);
        //9。关闭所有资源
        producer.close();
        session.close();
        connection.close();
    }


    @Test
    public void testQueueConsumer() throws JMSException, IOException {
        //1.创建ConncetinFactory对象
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(ActiveMQDellUrl);
        //2。创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3。开启连接
        connection.start();
        //4。使用连接对象connection创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5。创建一个Destination对象
        Queue queue = session.createQueue("spring-queue");
        //6。使用session对象创建一个消费者consumer对象
        MessageConsumer messageConsumer = session.createConsumer(queue);
        //7。接受消息
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                //消息到达时，回调该方法
                //8。打印结果
                TextMessage textMessage=(TextMessage) message;
                try {
                    System.out.println("接受到的消息： "+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //等待接受消息
        System.in.read();
        //9。关闭各种资源
        messageConsumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicProducer() throws JMSException {
        //1. 创建连接工厂对象，需要指定服务到ip及端口
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://47.99.113.229:61616");
        //2。使用工厂对象创建connection对象
        Connection connection = connectionFactory.createConnection();
        //3。开启连接 调用connection到start方法
        connection.start();
        //4。 创建一个session对象
        //第一个参数：是否开启事务；一般不开启事务，如果开启事务，第二个参数无意义
        //第二个参数：应答模式：（自动应答，手动应答）一般自动应答
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5。使用session对象创建Destination对象，两种形式queue，topic
        Topic topic = session.createTopic("test-topic");
        //6。使用session创建producer对象
        MessageProducer producer = session.createProducer(topic);
        //7。创建一个消息message对象（TextMessage）
//        TextMessage textMessage=new ActiveMQTextMessage();
//        textMessage.setText("hello");
        TextMessage textMessage = session.createTextMessage("hello! I'm a topic message");
        //8。发送消息
        producer.send(textMessage);
        //9。关闭所有资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer() throws JMSException, IOException {
        //1.创建ConncetinFactory对象
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://47.99.113.229:61616");
        //2。创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3。开启连接
        connection.start();
        //4。使用连接对象connection创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5。创建一个Destination对象
        Topic topic = session.createTopic("test-topic");
        //6。使用session对象创建一个消费者consumer对象
        MessageConsumer messageConsumer = session.createConsumer(topic);
        //7。接受消息
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                //消息到达时，回调该方法
                //8。打印结果
                TextMessage textMessage=(TextMessage) message;
                try {
                    System.out.println("接受到的消息： "+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("topic consumer 2 starts");
        //等待接受消息
        System.in.read();
        //9。关闭各种资源
        messageConsumer.close();
        session.close();
        connection.close();
    }


    @Test
    public void testReference(){
        Object o1=new Object();
        Object o2=new Object();
        System.out.println(o1);
        System.out.println(o2);
        o2=o1;
        System.out.println(o2);
    }

}
