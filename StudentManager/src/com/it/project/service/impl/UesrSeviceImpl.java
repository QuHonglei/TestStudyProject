package com.it.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.project.dao.UserDao;
import com.it.project.entity.User;
import com.it.project.service.UserService;
@Service
public class UesrSeviceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public User findByUserName(String username) {
		// TODO 自动生成的方法存根
		return userDao.findByUserName(username);
	}
	@Override
	public int add(User user) {
		// TODO 自动生成的方法存根
		return userDao.add(user);
	}
	@Override
	public List<User> findList(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return userDao.findList(queryMap);
	}
	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO 自动生成的方法存根
		return userDao.getTotal(queryMap);
	}
	@Override
	public int edit(User user) {
		// TODO 自动生成的方法存根
		return userDao.edit(user);
	}
	@Override
	public int delete(String ids) {
		// TODO 自动生成的方法存根
		return userDao.delete(ids);
	}
	@Override
	public int editpassword(User user) {
		// TODO 自动生成的方法存根
		return userDao.editpassword(user);
	}
	@Override
	public User findByUserId(Long id) {
		// TODO 自动生成的方法存根
		return userDao.findByUserId(id);
	}




}
