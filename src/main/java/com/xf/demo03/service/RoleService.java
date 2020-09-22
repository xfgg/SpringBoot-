package com.xf.demo03.service;

import com.xf.demo03.model.Role;

/**
 * @author xfgg
 */
public interface RoleService {
    /**
     * 查找Role实体根据id
     * @param id
     * @return
     */
    Role find(String id);
}
