package com.xf.demo03.service.impl;

import com.xf.demo03.model.User;
import com.xf.demo03.service.SendJunkMailService;
import com.xf.demo03.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author xfgg
 */
@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {
    @Autowired
    JavaMailSender mailSender;
    @Resource
    private UserService userService;
    @Value("${spring.mail.username}")
    private String from;
    public static final Logger LOGGER = LogManager.getLogger(SendJunkMailServiceImpl.class);
    @Override
    public boolean sendJunkMail(List<User> user) {
        try {
            if (user==null||user.size()<=0) {
                return Boolean.FALSE;
            }
            for (User userlist:user
                 ) {
                MimeMessage mimeMessage=this.mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                /**
                 * 邮件发送发
                 */
                messageHelper.setFrom(from);
                /**
                 * 邮件主题
                 */
                messageHelper.setSubject("你是傻子");
                /**
                 * 邮件接收方
                 */
                messageHelper.setTo("test@163.com");
                /**
                 * 邮件内容
                 */
                messageHelper.setText(userlist.getUsername()+"你知道吗，你是傻子");
                /**
                 * 发送邮件
                 */
                this.mailSender.send(mimeMessage);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
