package com.xf.demo03.service.impl;

import com.xf.demo03.model.Mood;
import com.xf.demo03.repository.MoodRepository;
import com.xf.demo03.service.MoodService;
import com.xf.demo03.service.producer.MoodProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * @author xfgg
 */
@Service
public class MoodServiceImpl implements MoodService {
    @Autowired
    private MoodRepository moodRepository;
    @Override
    public Mood save(Mood mood) {
        return moodRepository.save(mood);
    }

    /**
     * 队列
     */
    private static Destination destination = new ActiveMQQueue("mood.queue.asyn.save");
    @Resource
    private MoodProducer moodProducer;
    @Override
    public String asynSave(Mood mood) {
        /**
         * 调用生产者发送信息
         */
        moodProducer.sendMessage(destination,mood);
        return "success";
    }

    @Async
    @Override
    public void asyncTask() {
        long startTime = System.currentTimeMillis();
        try {
            /**
             * 模拟耗时
             */
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+":void asyncTask(),耗时:"+(endTime-startTime));
    }

    @Async("asyncTaskExecutor")
    @Override
    public Future<String> asyncTask(String s) {
        long startTime = System.currentTimeMillis();
        try {
            /**
             * 模拟耗时
             */
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+":Future<String> asyncTask(String s),耗时："
                +(endTime-startTime));
        return AsyncResult.forValue(s);
    }

    @Async("asyncTaskExecutor")
    @Override
    public void asyncTaskForTransaction(Boolean Flag) {
        Mood mood = new Mood();
        mood.setId("3");
        mood.setUserId("3");
        mood.setPraiseNum(0);
        mood.setContent("我的第三条说说");
        mood.setPublishTime(new Date());
        if(Flag){
            throw new RuntimeException("模拟异常");
        }
    }
}
