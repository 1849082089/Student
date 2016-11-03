package com.bonc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.dao.StudentDao;
import com.bonc.entity.Student;
@Service
public class StudentService {
	StudentDao sd=new StudentDao();
	public List<Student> searchAll(){
		return sd.searchAll();
	}
	public boolean addStudent(Student stu){
		return sd.addStudent(stu);
	}

}
