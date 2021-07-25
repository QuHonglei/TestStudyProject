package com.it.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.project.dao.TeacherDao;
import com.it.project.entity.Teacher;
import com.it.project.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	public TeacherDao teacherDao;
	
	@Override
	public Teacher findByUserName(String username) {
		// TODO 自动生成的方法存根
		return teacherDao.findByUserName(username);
	}

	@Override
	public Teacher findByUserId(Long id) {
		// TODO 自动生成的方法存根
		return teacherDao.findByUserId(id);
	}

	@Override
	public int add(Teacher teacher) {
		// TODO 自动生成的方法存根
		return teacherDao.add(teacher);
	}

	@Override
	public int edit(Teacher teacher) {
		// TODO 自动生成的方法存根
		return teacherDao.edit(teacher);
	}

	@Override
	public int editpassword(Teacher teacher) {
		// TODO 自动生成的方法存根
		return teacherDao.editpassword(teacher);
	}

	@Override
	public int delete(String ids) {
		// TODO 自动生成的方法存根
		return teacherDao.delete(ids);
	}

	@Override
	public List<Teacher> findList(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return teacherDao.findList(queryMap);
	}

	@Override
	public List<Teacher> findAll() {
		// TODO 自动生成的方法存根
		return teacherDao.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return teacherDao.getTotal(queryMap);
	}
}
