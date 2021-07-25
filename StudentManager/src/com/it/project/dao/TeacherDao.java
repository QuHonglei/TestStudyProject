package com.it.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.it.project.entity.Teacher;
@Service
@Repository
public interface TeacherDao {
	public Teacher findByUserName(String username);//根据用户名称获取用户信息
	public Teacher findByUserId(Long id);//根据用户id获取用户信息
	public int add(Teacher teacher);//增加学生信息
	public int edit(Teacher teacher);//编辑学生信息
	public int editpassword(Teacher teacher);//修改密码功能里面，修改当前学生密码
	public int delete(String ids);//删除学生信息
	public List<Teacher> findList(Map<String,Object> queryMap);//分页获取学生列表
	public List<Teacher> findAll();//获取所有学生
	public int getTotal(Map<String,Object> queryMap);
}
