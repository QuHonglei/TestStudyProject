package com.itheima.mapper;

import com.itheima.pojo.User;
import tk.mybatis.mapper.common.Mapper;

/*
如果没有配置Mapper接口扫描包的话，就要使用 @Mapper 注解为每一个Mapper接口进行标识，才能够被识别。
或者不加注解，而是在启动类中添加接口扫描注解 @MapperScan("com.itheima.mapper")  推荐此种方法。
 */
//@Mapper
public interface UserMapper extends Mapper<User> {
}
