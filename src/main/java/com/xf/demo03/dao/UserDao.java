package com.xf.demo03.dao;

import com.xf.demo03.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xfgg
 */
@Mapper
public interface UserDao {
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByNameAndPassword(@Param("username") String username,
                               @Param("password") String password);
}
