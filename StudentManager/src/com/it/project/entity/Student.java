package com.it.project.entity;
import org.springframework.stereotype.Component;
@Component
public class Student {
	private Long id;
	private String name;//姓名
	private Long clazzId;//班级id
	private String username;//学号
	private String password;
	private String sex;
	private String photo;//头像  
	private Long year;//入学年份
	private Long endyear;//毕业年份
	private String province;//省份
	
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getClazzId() {
		return clazzId;
	}
	public void setClazzId(Long clazzId) {
		this.clazzId = clazzId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Long getEndyear() {
		return endyear;
	}
	public void setEndyear(Long endyear) {
		this.endyear = endyear;
	}
	
}
