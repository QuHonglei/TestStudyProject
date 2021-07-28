package com.itheima.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Date;

//标识是一个配置类
//@Configuration
//@EnableConfigurationProperties(JdbcProperties.class)
//@PropertySource("classpath:application.yml")
public class JdbcConfig {
    //在yml文件中配置连接池的时候，要将JdbcConfig中的Druid的配置给删除或注释,配置类注解也删掉
    /*
    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource(){
        return new DruidDataSource();
    }
     */


//    @Value("${jdbc.url}")
//    String url;
//    @Value("${jdbc.driverClassName}")
//    String driverClassName;
//    @Value("${jdbc.username}")
//    String username;
//    @Value("${jdbc.password}")
//    String password;

//    @Bean
//    public DataSource dataSource(){
//        //创建数据源对象
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setUrl(url);
//        return dataSource;
//    @Bean
//    public DataSource dataSource(JdbcProperties jdbc){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(jdbc.getDriverClassName());
//        dataSource.setUsername(jdbc.getUsername());
//        dataSource.setPassword(jdbc.getPassword());
//        dataSource.setUrl(jdbc.getUrl());
//        return dataSource;
//    }


}
