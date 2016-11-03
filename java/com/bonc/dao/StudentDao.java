package com.bonc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bonc.entity.Student;



public class StudentDao extends BaseDao {
	Connection conn;
	PreparedStatement stat=null;
	ResultSet rs;
	String sql;
	
	//查询
		public List<Student> searchAll(){
				List<Student> userList = new ArrayList<Student>();
				String sql="select * from student";
				try {
					conn = getConn();
					stat = conn.prepareStatement(sql);
					rs = stat.executeQuery();
					while (rs.next()) {
						Student stu=new Student();
						stu.setStuId(rs.getInt("stuId"));
						stu.setStuName(rs.getString("stuName"));
						stu.setClassId(rs.getInt("classId"));
						userList.add(stu);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeAll(conn,stat,rs);
				}
				return userList;
			}
	
	//条件查询
	public List<Student> search(String name){
			List<Student> userList = new ArrayList<Student>();
			String sql="select * from student where stuName=?";
			try {
				conn = getConn();
				stat = conn.prepareStatement(sql);
				stat.setString(1, name);
				rs = stat.executeQuery();
				while (rs.next()) {
					Student stu=new Student();
					stu.setStuId(rs.getInt("stuId"));
					stu.setStuName(rs.getString(rs.getString("stuName")));
					stu.setClassId(rs.getInt("classId"));
					userList.add(stu);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeAll(conn,stat,rs);
			}
			return userList;
		}
	//添加
	public boolean addStudent(Student stu){
		int ret=0;
		ret=excuteUpdate("insert into student(stuId,stuName,classId) values(?,?,?)",
				new Object[]{stu.getStuId(),stu.getStuName(),stu.getClassId()});
		return true;
	}

}
