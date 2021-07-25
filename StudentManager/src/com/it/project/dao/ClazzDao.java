package com.it.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.it.project.entity.Clazz;

@Repository
@Service
public interface ClazzDao {
	public Clazz findByClazzName(String string);
	public List<Clazz> findAll();//获取所有班级
	public List<Clazz> findList(Map<String,Object >queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Clazz clazz);//添加专业
	public int edit(Clazz clazz);//修改专业
	public int delete(String ids);//删除专业
	public Long getById(Long id);//根据id获取到专业年制
}
