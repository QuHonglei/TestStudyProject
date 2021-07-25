package com.it.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.it.project.entity.Grade;

@Service
public interface GradeService {
	public Grade findByGradeName(String string);
	public List<Grade> findAll();
	public List<Grade> findList(Map<String,Object >queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Grade grade);
	public int edit(Grade grade);
	public int delete(String ids);
}
