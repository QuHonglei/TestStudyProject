package com.itheibai.service.Impl;

import com.itheibai.dao.UserDao;
import com.itheibai.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceImpl implements UserService {
    //通过有参构造方法注入

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
    }


/**
 *通过光剑UserService，在其内部调用。或是有参构造方法。
 public void save() {
 ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
 UserDao userDao = (UserDao) app.getBean("userDao");
 userDao.save();
 }
 */

//    //通过set方法注入
//    private UserDao userDao;
//    public void setUserDao(UserDao userDao){
//        this.userDao=userDao;
//    }
    //通过注入方法调用
    public void save(){
        userDao.save();
    }


}
