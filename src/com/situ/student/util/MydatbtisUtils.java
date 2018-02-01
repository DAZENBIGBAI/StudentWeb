package com.situ.student.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MydatbtisUtils {
	   static SqlSessionFactory sqlSessionFactory  = null;
	   static InputStream inputStream;
	    static {
	        //加载核心的配置文件
	        String resource = "mybatis.xml";
	        //inputStream =Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
          try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
           //创建SqlSessionFactory
           sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    }


	    public static SqlSession getSqlSession() {
	        //创建SqlSession
	        SqlSession sqlSession = sqlSessionFactory.openSession();
	        return sqlSession;
	    }

}
