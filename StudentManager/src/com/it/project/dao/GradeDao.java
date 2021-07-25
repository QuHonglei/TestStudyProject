package com.it.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.it.project.entity.Grade;

@Service
@Repository
public interface GradeDao {
	public Grade findByGradeName(String name);
	public List<Grade> findAll();
	public List<Grade> findList(Map<String,Object >queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Grade grade);
	public int edit(Grade grade);
	public int delete(String ids);
	

}
