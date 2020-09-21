package com.xf.demo03.quartz;

import com.xf.demo03.model.User;
import com.xf.demo03.service.SendJunkMailService;
import com.xf.demo03.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author xfgg
 */
@Component
@Configuration
@EnableScheduling
public class SendMailQuartz {
    /**
     * 日志对象
     */
    private static final Logger LOGGER = LogManager.getLogger(SendMailQuartz.class);
    @Resource
    private SendJunkMailService sendJunkMailService;
    @Resource
    private UserService userService;
    /**
     * 每五秒执行一次
     */
    @Scheduled(cron="*/5 * * * * *")
    public void reportCurrentByCron(){
        List<User> userList=userService.findAll();
        if (userList==null||userList.size()<=0){
            return;
        }
        /**
         * 发送邮件
         */
        sendJunkMailService.sendJunkMail(userList);
        LOGGER.info("定时器运行了！");
        System.out.println("定时器运行了！");
    }
}
