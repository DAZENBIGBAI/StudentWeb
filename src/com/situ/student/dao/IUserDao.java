package com.situ.student.dao;

import java.util.List;

import com.situ.student.entity.User;

public interface IUserDao {
	public User findUser(String name,String password);
}
