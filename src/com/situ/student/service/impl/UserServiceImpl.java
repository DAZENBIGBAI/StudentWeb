package com.situ.student.service.impl;

import com.situ.student.dao.IUserDao;
import com.situ.student.dao.impl.UserDaoImpl;
import com.situ.student.entity.User;
import com.situ.student.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao dao = new UserDaoImpl();
	//获取用户
	public User findUser(String name ,String password){
		User user = dao.findUser(name, password);
		return user;
	}
	
}
