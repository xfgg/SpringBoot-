package com.xf.demo03.service;

import com.xf.demo03.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xfgg
 */
public interface UserService {
    /**
     * 根据用户ID查找
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 分页操作
     */
    Page<User> findAll(Pageable pageable);

    /**
     * mybatis接口
     * @param username
     * @param password
     * @return
     */
    User findByNameAndPassword(String username,String password);
}
