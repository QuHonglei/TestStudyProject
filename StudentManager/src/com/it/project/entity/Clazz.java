package com.it.project.entity;

import org.springframework.stereotype.Component;

/**
 * 班级实体
 * @author quhonglei
 *
 */
@Component
public class Clazz {
	private Long id;
	private Long gradeId;//年级id
	private String name;
	private Long year;//年限
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	
}
