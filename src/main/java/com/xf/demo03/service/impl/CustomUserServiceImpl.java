package com.xf.demo03.service.impl;

import com.xf.demo03.model.User;
import com.xf.demo03.model.UserRoleRel;
import com.xf.demo03.service.RoleService;
import com.xf.demo03.service.UserRoleRelService;
import com.xf.demo03.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xfgg
 */
public class CustomUserServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;
    @Resource
    private UserRoleRelService userRoleRelService;
    @Resource
    private RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user==null){
            return null;
        }
        //获取用户所有关联对象
        List<UserRoleRel> roleRelList = userRoleRelService.findByUserId(user.getId());
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        if (roleRelList!=null && roleRelList.size()>0){
            for (UserRoleRel rel: roleRelList
                 ) {
                //获取用户关联角色名称
                String roleName = roleService.find(rel.getRoleId()).getName();
                authorityList.add(new SimpleGrantedAuthority(roleName));
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword()
        ,authorityList);
    }
}
