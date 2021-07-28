package com.itheima.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性读取类，要加注解标识
 */
//@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {
    private String url;
    private String driverClassName;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JdbcProperties() {
    }

    public JdbcProperties(String url, String driverClassName, String username, String password) {
        this.url = url;
        this.driverClassName = driverClassName;
        this.username = username;
        this.password = password;
    }
}
