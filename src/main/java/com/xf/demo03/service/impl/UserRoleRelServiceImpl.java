package com.xf.demo03.service.impl;

import com.xf.demo03.model.UserRoleRel;
import com.xf.demo03.repository.UserRoleRelRepository;
import com.xf.demo03.service.UserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xfgg
 */
@Service
public class UserRoleRelServiceImpl implements UserRoleRelService {
    @Resource
    UserRoleRelRepository userRoleRelRepository;
    @Override
    public List<UserRoleRel> findByUserId(String userId) {
        return userRoleRelRepository.findByUserId(userId);
    }
}
