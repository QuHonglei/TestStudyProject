package com.itheima.service;

import com.itheima.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
//加载Spring环境
@RunWith(SpringRunner.class)
//必须要加测试注解
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void queryById() {
        User user = userService.queryById(8L);
        System.out.println("user = "+user);
    }

    @Test
    public void savaUser() {
        User user = new User();
        user.setUserName("test2");
        user.setName("test2");
        user.setAge(21);
        user.setPassword("123456");
        user.setSex(1);
        user.setCreated(new Date());
        userService.savaUser(user);
    }
}