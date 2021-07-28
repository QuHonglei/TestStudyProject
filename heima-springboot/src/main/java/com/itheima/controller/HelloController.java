package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * 3.处理器，要用注解标识@RestController
 */
@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    //注入配置类
    @Autowired
    private DataSource dataSource;
    @Value("${itcast.url}")
    private String itcastUrl;
    @Value("${itheima.url}")
    private String itheimaUrl;
    //定义一个方法，返回一个字符串
    @GetMapping("hello}")
    public String hello(){
        System.out.println("itcastUrl = "+itcastUrl);
        System.out.println("itheimaUrl = "+itheimaUrl);
        System.out.println("DataSource = "+dataSource);
        return "Hello,Springboot!";
    }
    @GetMapping("/user/{id}")
    public User queryById(@PathVariable Long id){
        return userService.queryById(id);
    }
}
