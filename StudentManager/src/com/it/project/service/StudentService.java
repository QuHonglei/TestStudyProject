package com.it.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.it.project.entity.Student;
@Service
@Repository
public interface StudentService {
	public Student findByUserName(String username);
	public Student findByUserId(Long id);
	public int add(Student student);
	public int edit(Student student);
	public int editpassword(Student student);
	public int delete(String ids);
	public List<Student> findList(Map<String,Object> queryMap);
	public List<Student> findAll();
	public int getTotal(Map<String,Object> queryMap);
	public List<Student> countYearStudent();
	public List<Student> countProvinceStudent();
	
}
