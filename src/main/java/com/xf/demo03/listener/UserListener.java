package com.xf.demo03.listener;

import com.xf.demo03.model.User;
import com.xf.demo03.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class UserListener implements ServletContextListener {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;
    private static final String ALL_USER ="ALL_USER_LIST";
    Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /**
         * 查询数据库中所有的用户
         */
        List<User> userList=userService.findAll();
        /**
         * 查询缓存中的用户数据
         */
        redisTemplate.delete(ALL_USER);
        /**
         * 将数据存放到redis缓存
         */
        redisTemplate.opsForList().leftPushAll(ALL_USER,userList);
        /**
         * 真实项目中需要注释掉，查询所有的用户数据
         */
        List<User> queryUserList = redisTemplate.opsForList().range(ALL_USER,0,-1);
        //System.out.println("缓存中的用户数有："+queryUserList.size()+"人");
        //System.out.println("ServletContext上下文初始化");
        logger.info("缓存中的用户数有："+queryUserList.size()+"人");
        logger.info("ServletContext上下文初始化");
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent){
        //System.out.println("ServletContext上下文摧毁");
        logger.info("ServletContext上下文摧毁");
    }
}
