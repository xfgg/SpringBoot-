package com.xf.demo03;

import com.xf.demo03.model.Mood;
import com.xf.demo03.model.User;
import com.xf.demo03.service.MoodService;
import com.xf.demo03.service.UserService;
import com.xf.demo03.service.producer.MoodProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.persistence.Id;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Demo03ApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    void mySqlTest() {
        String sql = "select id,username,password from user";
        List<User> userList = (List<User>) jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        System.out.println("查询成功");
        for (User user : userList) {
            System.out.println("id:" + user.getId() + "username:" + user.getUsername()
                    + "password:" + user.getPassword());
        }
    }

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedis() {
        /**
         * 增key：name，value：ay
         */
        redisTemplate.opsForValue().set("name", "ay");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println("name");
        /**
         * 删除
         */
        redisTemplate.delete("name");
        /**
         * 更新
         */
        redisTemplate.opsForValue().set("name", "a1");
        /**
         * 查询
         */
        name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name1");
    }

    @Resource
    private UserService userService;

    @Test
    void testRepository() {
        /**
         * 查询所有数据
         */
        List<User> userList = userService.findAll();
        /**
         * 根据id查询用户
         */
        User user = userService.findById("1");
        /**
         * 添加用户
         */
        User user1 = new User();
        user1.setId("2");
        user1.setUsername("xuefang");
        user1.setPassword("123456");
        userService.save(user1);
        /**
         * 分页查询数据
         */
        Pageable pageable1 = PageRequest.of(1, 4);
        Page<User> users = userService.findAll(pageable1);
    }

    /**
     * redis缓存测试
     */
    @Test
    public void testFindById() {
        Long redisUserSize = 0L;
        /**
         * 查询id=1的数据，该数据存在与redis缓存中
         */
        User user = userService.findById("1");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前redis缓存中的用户数量为：" + redisUserSize);
        System.out.println("--->>>id:" + user.getId() + "name:" + user.getUsername());
        /**
         * 查询id=4的数据，不存在于Redis缓存中，存在于数据库中，所以会把数据库查询的数据更新到缓存中
         */
        User user1 = userService.findById("2");
        System.out.println("--->>>id:" + user1.getId() + "name:" + user1.getUsername());
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前redis缓存中的用户数量为：" + redisUserSize);
    }

    /**
     * 测试log4j
     */
    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void testLog4() {
        userService.findById("1");
        logger.info("查询成功");
    }
    /**
     * 测试mybatis方法
     */
    @Test
    public void testMyBatis(){
        User user=userService.findByNameAndPassword("xuefeng","123456");
        logger.info(user.getId()+user.getUsername());
    }

    /**
     * mood test
     */
    @Resource
    private MoodService moodService;
    @Test
    public void testmood(){
        Mood mood = new Mood();
        mood.setId("1");
        mood.setUserId("1");
        mood.setPraiseNum(0);
        mood.setContent("这是我的第一条说说");
        mood.setPublishTime(new Date());
        moodService.save(mood);
    }

    /**
     * 测试activeMQ
     */
    @Resource
    private MoodProducer moodProducer;
    @Test
    public void testActiveMQ(){
        Destination destination = new ActiveMQQueue("mood.queue");
        moodProducer.sendMessage(destination,"hello,ActiveMQ");

    }
    /**
     * ActiveMQ异步
     */
    @Test
    public void testActiveMQAsyn(){
        Mood mood = new Mood();
        mood.setId("2");
        mood.setUserId("2");
        mood.setPublishTime(new Date());
        mood.setContent("这是我的第二条说说");
        mood.setPraiseNum(0);
        String msg = moodService.asynSave(mood);
        System.out.println("异步发表说说："+msg);
    }
}
