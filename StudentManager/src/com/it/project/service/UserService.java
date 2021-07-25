package com.it.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.it.project.entity.User;


@Service
@Repository

public interface UserService {
	public User findByUserName(String username);
	public int add(User user);
	public int edit(User user);
	public int delete(String ids);
	public List<User> findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int editpassword(User user);
	public User findByUserId(Long id);
}
