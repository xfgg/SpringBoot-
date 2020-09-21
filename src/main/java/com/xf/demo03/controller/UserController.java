package com.xf.demo03.controller;

import com.xf.demo03.model.User;
import com.xf.demo03.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xfgg
 */
@Controller
@RequestMapping("/User")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/list")
    public String test(Model model){
        List<User> user=userService.findAll();
        model.addAttribute("user",user);
        return "userList";
    }
}
