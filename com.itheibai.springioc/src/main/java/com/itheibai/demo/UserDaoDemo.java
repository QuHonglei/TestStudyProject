package com.itheibai.demo;

import com.itheibai.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试
 */
public class UserDaoDemo {
    public static void main(String[] args) {
        //获取spring配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //使用getBean()方法获得bean对象，传入值为配置文件中bean的id，返回的对象要强制转换
        UserDao userDao = (UserDao) app.getBean("userDao");
        //获取到bean对象后，可以调用对象的方法
        userDao.save();
    }
}
