package com.bonc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bonc.entity.Student;
import com.bonc.service.StudentService;

@Controller
public class UserController {

	@Autowired
	StudentService ss;

	@RequestMapping("/get")
	public ModelAndView home() {
		Map<String, String> map = new HashMap<>();
		map.put("hello", "word");
		return new ModelAndView("get", map);
	}

	// 查询所有
	@RequestMapping("/all")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("student");
		List<Student> stuList = ss.getList();
		mav.addObject("hello", stuList);
		return mav;
	}

	// 添加或修改学生信息
	@RequestMapping("/doAdd")
	public String add(Student stu) {
		if(stu.getStuId()>0){
			//修改
			int b = ss.updateById(stu);
			if (b > 0) {
				return "redirect:/remove";
			} else {
				return "student";
			}
		}else{
			//添加
			int b = ss.addStudent(stu);
			if (b > 0) {
				return "redirect:/remove";
			} else {
				return "student";
			}
		}
	}

	// 删除学生信息
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(int id) {
		int resultNum = ss.deleteById(id);
		if (resultNum != 0) {
			return true;
		}
		return false;
	}
	
	//修改时显示学生信息
	@RequestMapping("/selectById")
	@ResponseBody
	public Student selectById(int id) {
		System.out.println(id);
		Student stu = ss.selectById(id);
		System.out.println(stu);
		return stu;
	}
	//总记录分页查询
	@RequestMapping("/msg")
	public String selectByPages(Model model,HttpSession session,String p) {
		String item=(String) session.getAttribute("item");
		String str=(String) session.getAttribute("str");
		if(item==null && str==null){
		
		//当前页
		int pageNum=1;
		//每页行数
		int pageSize=4;
		//所有信息
		List<Student> list = ss.getList();
		//总页数
		int pageCount=ss.getTotalPages(pageSize, list);
	     if(p!=null){
	    	 pageNum=Integer.parseInt(p);
	     }
	     int prePage=pageNum>1 ? pageNum-1 : 1;
	     int nxtPage=pageNum<pageCount ? pageNum+1 : pageNum;
	     //显示内容
	     List<Student> stuList=ss.getPages(pageNum,pageSize);
		model.addAttribute("hello", stuList);
		model.addAttribute("prePage", prePage);
		model.addAttribute("nxtPage", nxtPage);
		return "student";
		}else{
			//当前页
			int pageNum=1;
			//每页行数
			int pageSize=4;
			//所有信息
			List<Student> list = ss.getStuList(item, str);
			System.out.println(list);
			//总页数
			int pageCount=ss.getTotalPages(pageSize, list);
		     if(p!=null){
		    	 pageNum=Integer.parseInt(p);
		     }
		     int prePage=pageNum>1 ? pageNum-1 : 1;
		     int nxtPage=pageNum<pageCount ? pageNum+1 : pageNum;
		     //显示内容
		     List<Student> stuList=ss.selectPages(item,str,pageNum,pageSize);
			model.addAttribute("hello", stuList);
			model.addAttribute("prePage", prePage);
			model.addAttribute("nxtPage", nxtPage);
			return "student";
		}
	}
	
	@RequestMapping("/key")
	public String select(String item,String str,HttpSession session) {
		session.setAttribute("item", item);
		session.setAttribute("str", str);
		return "redirect:/msg";
	}
	@RequestMapping("/remove")
	public String remove(HttpSession session) {
		session.removeAttribute("item");
		session.removeAttribute("str");
		return "redirect:/msg";
	}
	
}
