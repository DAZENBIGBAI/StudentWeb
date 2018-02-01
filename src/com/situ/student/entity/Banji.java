package com.situ.student.entity;

import java.util.List;

public class Banji {
	private Integer id;
	private String name;
	private Integer count;
	
	public Banji() {
		super();
	}
	

	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public Banji(Integer id, String name, Integer count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}


	public Banji(String name) {
		super();
		this.name = name;
	}


	
	public Banji(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Banji [id=" + id + ", name=" + name + ", count=" + count + "]";
	}

	


	


	

	
	
	
}
