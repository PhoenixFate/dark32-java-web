package com.phoenix.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SolrMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        //取消息内容
        TextMessage textMessage=(TextMessage) message;
        try {
            String text = textMessage.getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
