package com.xf.demo03.security;

import com.xf.demo03.service.impl.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author xfgg
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public CustomUserServiceImpl customUserService(){
        return new CustomUserServiceImpl();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        /**
         * 路由策略和访问权限的简单配置
         */
        http
                .formLogin()    //启用默认登录页面
                //登录失败返回error界面
                .failureForwardUrl("/error")
                //登录成功跳转URL
                .defaultSuccessUrl("/User")
                .permitAll();   //登录页面全部权限可访问
        super.configure(http);
    }
    /**
     * 配置内存用户
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                //可添加内存中的用户，并可给用户指定角色权限
                .inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
               // .withUser("薛峰").password("123456").roles("ADMIN")
                //.and()
                //.withUser("学风").password("123456").roles("USER");
    }
}
