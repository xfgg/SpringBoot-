package com.xf.demo03.service;

import com.xf.demo03.model.Mood;

import java.util.concurrent.Future;

/**
 * @author xfgg
 */
public interface MoodService {
    /**
     * 保存Mood对象
     * @param mood
     * @return
     */
    Mood save(Mood mood);

    /**
     * 使用Active消息缓存中间价
     * @param mood
     * @return
     */
    String asynSave(Mood mood);

    /**
     * 没有返回值的异步调用
     */
    void asyncTask();

    /**
     *有返回值的异步调用
     * @param s
     * @return
     */
    Future<String> asyncTask(String s);

    /**
     * 异步调用，无返回值，事务测试
     * @param Flag
     */
    void asyncTaskForTransaction(Boolean Flag);
}
