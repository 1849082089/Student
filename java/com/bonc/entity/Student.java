package com.bonc.entity;

public class Student {
	
	private int stuId;
	private String stuName;
	private int classId;
	
	public Student() {
		super();
	}
	public Student(int stuId, String stuName, int classId) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.classId = classId;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	
	

}
