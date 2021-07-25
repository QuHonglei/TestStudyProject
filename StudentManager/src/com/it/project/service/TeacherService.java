package com.it.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.it.project.entity.Teacher;
@Service
@Repository
public interface TeacherService {
	public Teacher findByUserName(String username);
	public Teacher findByUserId(Long id);
	public int add(Teacher teacher);
	public int edit(Teacher teacher);
	public int editpassword(Teacher teacher);
	public int delete(String ids);
	public List<Teacher> findList(Map<String,Object> queryMap);
	public List<Teacher> findAll();
	public int getTotal(Map<String,Object> queryMap);
	
}
