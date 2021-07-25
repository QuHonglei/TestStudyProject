package com.it.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.project.dao.StudentDao;
import com.it.project.entity.Student;
import com.it.project.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	public StudentDao studentDao;

	@Override
	public Student findByUserName(String username) {
		// TODO 自动生成的方法存根
		return studentDao.findByUserName(username);
	}

	@Override
	public int add(Student student) {
		// TODO 自动生成的方法存根
		return studentDao.add(student);
	}

	@Override
	public int edit(Student student) {
//		//自动生成毕业年份
//		Long start_year = student.getYear();//获取入学年份
//		Long year = clazzDao.getById(student.getClazzId());//获取年制
//		Long endyear = start_year + year;
//		student.setEndyear(endyear);
		// TODO 自动生成的方法存根
		return studentDao.edit(student);
	}

	@Override
	public int delete(String ids) {
		// TODO 自动生成的方法存根
		return studentDao.delete(ids);
	}

	@Override
	public List<Student> findList(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return studentDao.findList(queryMap);
	}

	@Override
	public List<Student> findAll() {
		// TODO 自动生成的方法存根
		return studentDao.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return studentDao.getTotal(queryMap);
	}

	@Override
	public List<Student> countYearStudent() {
		// TODO 自动生成的方法存根
		return studentDao.countYearStudent();
	}

	@Override
	public List<Student> countProvinceStudent() {
		// TODO 自动生成的方法存根
		return studentDao.countProvinceStudent();
	}

	@Override
	public int editpassword(Student student) {
		// TODO 自动生成的方法存根
		return studentDao.editpassword(student);
	}

	@Override
	public Student findByUserId(Long id) {
		// TODO 自动生成的方法存根
		return studentDao.findByUserId(id);
	}


}
