package com.it.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.it.project.entity.Clazz;


@Service
public interface ClazzService {
	public Clazz findByClazzName(String string);
	public List<Clazz> findAll();
	public List<Clazz> findList(Map<String,Object >queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Clazz clazz);
	public int edit(Clazz clazz);
	public int delete(String ids);
	public Long getById(Long id);
}
