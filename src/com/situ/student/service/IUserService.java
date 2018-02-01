package com.situ.student.service;

import com.situ.student.entity.User;

public interface IUserService {
	//获取用户
	public User findUser(String name ,String password);
}
