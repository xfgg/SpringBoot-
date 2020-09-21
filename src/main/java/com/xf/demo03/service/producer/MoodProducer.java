package com.xf.demo03.service.producer;

import com.xf.demo03.model.Mood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author xfgg
 */
@Service
public class MoodProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     *JmsMessagingTemplate是发送消息的工具类，封装了JmsTemplate
     * @param destination 发送到的队列
     * @param message 待发送的消息
     */
    public void sendMessage(Destination destination,final String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
    public void sendMessage(Destination destination, Mood mood){
        jmsMessagingTemplate.convertAndSend(destination,mood);
    }
}
