package com.itheibai.demo;

import com.itheibai.service.Impl.UserServiceImpl;
import com.itheibai.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    public static void main(String[] args) {
//        //在没有将service配置到容器里之前，要通过new的方式获取
//        UserService userService = new UserServiceImpl();
//        userService.save();
        //将service配置到容器里后
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) app.getBean("userService");
        userService.save();
    }
}
