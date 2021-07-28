package com.itheima.service;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 94886
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户查询id
     * @param id 用户id
     * @return 用户
     */
    public User queryById(Long id){
        //根据id查询
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 创建事务
     * 如果食物中出错，则会回滚，
     * @param user
     */
    @Transactional
    public void savaUser(User user){
        System.out.println("新增用户");
        //选择性新增，如果属性为空，则该属性不会出现在insert语句上
        userMapper.insertSelective(user);
        //回滚
//        int i = 1/0;
    }
}
