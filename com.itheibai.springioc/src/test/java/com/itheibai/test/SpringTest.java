package com.itheibai.test;

import com.itheibai.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void test1(){
        //测试scope
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) app.getBean("userDao");
        System.out.println(userDao1);
        //设置容器为手动关闭，可在测试中打印出销毁方法
        app.close();
        //当scope属性为单例时，容器中只有一个对象


    }
}
