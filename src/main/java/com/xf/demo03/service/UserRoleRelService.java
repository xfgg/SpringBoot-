package com.xf.demo03.service;

import com.xf.demo03.model.UserRoleRel;

import java.util.List;

/**
 * @author xfgg
 */
public interface UserRoleRelService {
    /**
     * 根据id查询用户关联角色实体
     * @param userId
     * @return
     */
    List<UserRoleRel> findByUserId(String userId);
}
