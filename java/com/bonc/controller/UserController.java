package com.bonc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bonc.entity.Student;
import com.bonc.service.StudentService;

@Controller
public class UserController {
	
	StudentService ss=new StudentService();
	
	@RequestMapping("/get")
	public ModelAndView home() {
		Map< String, String> map = new HashMap<>();
		map.put("hello", "word");
		return new ModelAndView("get",map);
		
	}
	//查询所有
	@RequestMapping("/all")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("student");  
		List<Student> stuList=ss.searchAll();
		mav.addObject("hello", stuList);
//		for (int i = 0; i < stuList.size(); i++) {
//			System.out.println(stuList.get(i));
//			
//		}
		return mav;
	}
	@RequestMapping("/doAll")
	public String add(Student stu) {
		boolean b=ss.addStudent(stu);
		if(b==true){
			return "student";
		}else{
			return "redirect:/all";
		}
	}
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
