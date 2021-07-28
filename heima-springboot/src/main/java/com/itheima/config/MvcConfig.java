package com.itheima.config;

import com.itheima.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义mvc的部分内容，要实现 WebMvcConfigurer 接口，并添加 @Configuration 注解
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * 将拦截器注册到spring容器中（ioc）
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    /**
     * 重写addInterceptors方法，往拦截器链添加自定义拦截方法
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //通过registry方法，添加myInterceptor拦截器，并设置拦截路径 /*
        registry.addInterceptor(myInterceptor()).addPathPatterns("/*");
    }
}
