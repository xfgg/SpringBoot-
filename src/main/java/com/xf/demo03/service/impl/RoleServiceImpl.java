package com.xf.demo03.service.impl;

import com.xf.demo03.model.Role;
import com.xf.demo03.repository.RoleRepository;
import com.xf.demo03.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xfgg
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleRepository roleRepository;
    @Override
    public Role find(String id) {
        return roleRepository.findById(id).get();
    }
}
