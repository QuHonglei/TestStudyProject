package com.itheima.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Data ：自动提供getter和setter、hashCode、equals、toString等方法
 * @Getter：自动提供getter方法
 * @Setter：自动提供setter方法
 * @Slf4j：自动在bean中提供log变量，其实用的是slf4j的日志功能。
 */
@Data
//当从数据库查询数据的时候，结果通过jpa接口映射到实体类
@Table(name = "tb_user")
public class User {
    @Id
    //主键回填，数据库中主键自增后，会回填到新增的对象中
    @KeySql(useGeneratedKeys = true)
    private Long id;
//    @Column
    //user_name(数据库中)-->userName（实体类中），如果名字相同，或是符合驼峰命名规则，则会自动映射，不用添加注解
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String note;
    private Date created;
    private Date updated;

}
