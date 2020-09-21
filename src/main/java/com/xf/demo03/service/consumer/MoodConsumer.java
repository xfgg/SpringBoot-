package com.xf.demo03.service.consumer;


import com.xf.demo03.model.Mood;
import com.xf.demo03.service.MoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xfgg
 */
@Component
public class MoodConsumer {
    /**
     * JmsListener配置消费者监听的队列moodqueue
     * @param text 接收到的信息
     */
    @JmsListener(destination = "mood.queue")
    public void receiveQueue(String text){
        System.out.println("用户发表说说["+text+"]成功");
    }
    @Resource
    private MoodService moodService;
    @JmsListener(destination = "mood.queue.asyn.save")
    public void receiveQueue(Mood mood){
        moodService.save(mood);
    }
}
