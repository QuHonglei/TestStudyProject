package com.itheibai.dao.impl;

import com.itheibai.dao.UserDao;
import com.itheibai.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserDaoImpl implements UserDao {

    //properties集合类型注入
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    //map集合类型注入
    private Map<String, User> userMap;

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    //list集合类型注入
    private List<String> strList;

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    //普通数据类型注入
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //    //无参构造，默认调用无参构造
//    public UserDaoImpl() {
//        System.out.println("UserDaoImpl对象被创建...");
//    }
//
//    public void init(){
//        System.out.println("初始化方法...");
//    }
//
//    public void destory(){
//        System.out.println("销毁方法...");
//    }

    public void save() {
        System.out.println(name+"===="+age);
        System.out.println(strList);
        System.out.println(userMap);
        System.out.println(properties);
        System.out.printf("save running....");
    }
}
