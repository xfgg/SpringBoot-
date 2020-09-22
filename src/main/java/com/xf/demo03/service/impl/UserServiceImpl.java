package com.xf.demo03.service.impl;

import com.xf.demo03.dao.UserDao;
import com.xf.demo03.model.User;
import com.xf.demo03.repository.UserRepository;
import com.xf.demo03.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author xfgg
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RedisTemplate<String, User> redisTemplate;
    private static final String ALL_USER = "ALL_USER_LIST";
    Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public User findById(String id) {
        /**
         * 查询redis缓存中的所有数据
         */
        List<User> userList=redisTemplate.opsForList().range(ALL_USER,0,-1);
        if (userList !=null && userList.size()>0){
            for (User user:userList
                 ) {
                if (user.getId().equals(id)){
                    return user;
                }
            }
        }
        /**
         * 查询数据库中的数据
         */
        User user=userRepository.findById(id).get();
        if (user.getId().equals(id)){
            /**
             * 将数据插入到redis缓存中
             */
            redisTemplate.opsForList().leftPush(ALL_USER,user);
        }
        logger.info("找到用户id："+id);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByUserName(String username) {
        List<User> users = (List<User>) findByUserName(username);
        if(users==null && users.size()<=0){
            return null;
        }
        return users.get(0);
    }

    @Resource
    private UserDao userDao;
    @Override
    public User findByNameAndPassword(String username, String password) {
        return userDao.findByNameAndPassword(username,password);
    }
}
