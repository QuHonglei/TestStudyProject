package com.it.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.it.project.entity.Student;
@Service
@Repository
public interface StudentDao {
	public Student findByUserName(String username);//根据用户名称获取用户信息
	public Student findByUserId(Long id);//根据用户id获取用户信息
	public int add(Student student);//增加学生信息
	public int edit(Student student);//编辑学生信息
	public int editpassword(Student student);//修改密码功能里面，修改当前学生密码
	public int delete(String ids);//删除学生信息
	public List<Student> findList(Map<String,Object> queryMap);//分页查询获取学生列表
	public List<Student> findAll();//获取所有学生
	public int getTotal(Map<String,Object> queryMap);//获取底部分页
	public List<Student> countYearStudent();//按照入学年份进行分组
	public List<Student> countProvinceStudent();//按照省份进行分组
}
