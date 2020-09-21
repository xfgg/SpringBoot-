package com.xf.demo03.service;

import com.xf.demo03.model.User;

import java.util.List;

/**
 * @author xfgg
 */
public interface SendJunkMailService {
    boolean sendJunkMail(List<User> user);
}
