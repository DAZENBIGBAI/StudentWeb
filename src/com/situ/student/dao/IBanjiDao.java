package com.situ.student.dao;

import java.util.List;

import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;

public interface IBanjiDao {
	//查找所有班级
	public  abstract List findAllBanji();
	//统计班级人数
	public  List<Integer> countStudent();
	
	//添加班级
	public abstract int addBanji(Banji b);
	//根据id删除
	public abstract int deleteBanji(int id);
	//根据id查找班级
	public abstract Banji findBanjiById(int id);
	//修改班级信息
	public abstract int updateBanji(Banji banjiName);
}
