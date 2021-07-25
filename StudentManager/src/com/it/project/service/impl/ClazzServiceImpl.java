package com.it.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.it.project.dao.ClazzDao;
import com.it.project.entity.Clazz;
@Service
@Repository
public class ClazzServiceImpl implements com.it.project.service.ClazzService {
	@Autowired
	private ClazzDao clazzDao;
	@Override
	public Clazz findByClazzName(String string) {
		// TODO 自动生成的方法存根
		return clazzDao.findByClazzName(string);
	}

	@Override
	public List<Clazz> findAll() {
		// TODO 自动生成的方法存根
		return clazzDao.findAll();
	}

	@Override
	public List<Clazz> findList(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return clazzDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return clazzDao.getTotal(queryMap);
	}

	@Override
	public int add(Clazz clazz) {
		// TODO 自动生成的方法存根
		return clazzDao.add(clazz);
	}

	@Override
	public int edit(Clazz clazz) {
		// TODO 自动生成的方法存根
		return clazzDao.edit(clazz);
	}

	@Override
	public int delete(String ids) {
		// TODO 自动生成的方法存根
		return clazzDao.delete(ids);
	}

	@Override
	public Long getById(Long id) {
		// TODO 自动生成的方法存根
		return clazzDao.getById(id);
	}



}
