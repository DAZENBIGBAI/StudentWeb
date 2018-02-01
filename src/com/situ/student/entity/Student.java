package com.situ.student.entity;

public class Student {
	private int id;
	private String name;
	private int age;
	private String gender;
	private String adress;
	private String banji_id;
	public Student() {
		super();
	}
	
	public String getBanji_id() {
		return banji_id;
	}

	public void setBanji_id(String banji_id) {
		this.banji_id = banji_id;
	}

	public Student(String name, int age, String gender, String adress, String banji_id) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.adress = adress;
		this.banji_id = banji_id;
	}

	public Student(int id, String name, int age, String gender, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.adress = adress;
	}

	public Student(String name, int age, String gender, String adress) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.adress = adress;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Student [id="+id+", name=" + name + ", age=" + age + ", gender=" + gender + ", adress=" + adress
				+ "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
}
