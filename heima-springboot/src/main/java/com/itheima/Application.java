package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 2.springboot启动引导类(工程入口)，要加注解 @SpringBootApplication
 *
 */
@SpringBootApplication
//扫描mybatis所有业务mapper接口
//@MapperScan("com.itheima.mapper")
@MapperScan("com.itheima.mapper")
public class Application {
    public static void main(String[] args) {
        //告诉spingboot我们的启动类是Application
        SpringApplication.run(Application.class,args);
    }
}
