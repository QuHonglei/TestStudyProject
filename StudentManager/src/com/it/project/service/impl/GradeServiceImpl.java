package com.it.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.project.dao.GradeDao;
import com.it.project.entity.Grade;
import com.it.project.service.GradeService;
@Service
public class GradeServiceImpl implements GradeService {
	
	@Autowired
	private GradeDao gradeDao;

	@Override
	public Grade findByGradeName(String name) {
		// TODO 自动生成的方法存根
		return gradeDao.findByGradeName(name);
	}

	@Override
	public List<Grade> findList(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return gradeDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return gradeDao.getTotal(queryMap);
	}

	@Override
	public int add(Grade grade) {
		// TODO 自动生成的方法存根
		return gradeDao.add(grade);
	}

	@Override
	public int edit(Grade grade) {
		// TODO 自动生成的方法存根
		return gradeDao.edit(grade);
	}

	@Override
	public int delete(String ids) {
		// TODO 自动生成的方法存根
		return gradeDao.delete(ids);
	}

	@Override
	public List<Grade> findAll() {
		// TODO 自动生成的方法存根
		return gradeDao.findAll();
	}

}
