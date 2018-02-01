package com.situ.student.dao;

import java.util.List;

import com.situ.student.entity.Student;

public interface IStudentDao {
	/**
	 * 添加学生到数据库
	 * @param student
	 * @return
	 */
	public abstract boolean add(Student s);
	/**
	 * 根据id删除指定的学生
	 * @param id
	 * @return
	 */
	public abstract boolean delete(int id);
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public abstract int update(Student s);
	/**
	 * 根据id查找指定的学生
	 * @param id
	 */
	public abstract Student findById(int id);
	/**
	 * 返回所有学生信息
	 * @return
	 */
	public abstract List<Student> findAll();
	/**
	 * 删除重名
	 */
	public abstract boolean checkName (String name);
	//分页显示
	public abstract List<Student> getPageList(Integer index,int p);
	//批量删除
	public abstract boolean deleteAll(String[] strId);
    //获得总人数
	public abstract int studentCount();
	
	
}











