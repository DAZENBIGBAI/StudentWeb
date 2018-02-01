package com.situ.student.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.situ.student.dao.IUserDao;
import com.situ.student.entity.User;
import com.situ.student.util.C3P0Util;

public class UserDaoImpl implements IUserDao {

	@Override
	public User findUser(String name, String password) {
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "SELECT * FROM USER where username=? and password=?";
		Object[] params = {name,password};
		User user = new User();
		try {
			user = queryRunner.query(sql, new BeanHandler<User>(User.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}


