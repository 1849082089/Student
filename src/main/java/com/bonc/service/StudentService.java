package com.bonc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.dao.StudentDao;
import com.bonc.entity.Student;
@Service
public class StudentService {
	@Autowired
	StudentDao sd;

	public List<Student> getList(){
		return sd.getList();
	}
	
	public int addStudent(Student stu){
		return sd.create(stu);
	}
	
	public int deleteById(int stuId) {
		return sd.deleteById(stuId);
	}
	
	public int updateById(Student stu) {
		return sd.updateById(stu);
	}
	
	public Student selectById(Integer id) {
		return sd.selectById(id);
	}
	
	public int getTotalPages(int pageSize,List<Student> list) {
		return sd.getTotalPages(pageSize,list);
	}
	
	public List getPages(int currentPage,int pageSize){
		return sd.getPages(currentPage,pageSize);
	}
	
	public List selectPages( String item,String str,int currentPage,int pageSize){
		return sd.selectPages(item, str, currentPage, pageSize);
	}
	
	public List<Student> getStuList(String item,String str){
		return sd.getStuList(item, str);
	}

}
