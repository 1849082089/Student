package com.bonc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bonc.entity.Student;


@Repository
public class StudentDao {
	
	private final Log logger = LogFactory.getFactory().getInstance(StudentDao.class);
		@Autowired
	    private JdbcTemplate jdbcTemplate;
	    //查询所有学生信息 
	    public List<Student> getList()
	    {
	        String sql = "select * from student";
	        
	        logger.info(sql);
	        
	        logger.info(jdbcTemplate);
	        
	        return (List<Student>)jdbcTemplate.query(sql, new RowMapper<Student>()
	        {
	            public Student mapRow(ResultSet rs, int rowNum)
	                throws SQLException
	            {
	            	Student stu = new Student();
	            	stu.setStuId(rs.getInt("stuId"));
					stu.setStuName(rs.getString("stuName"));
					stu.setClassId(rs.getInt("classId"));
	                return stu;
	            }
	        });
	    }
	  //根据id查询学生信息
	    @SuppressWarnings("unchecked")
	    public Student selectById(int id) {  
	        return (Student) jdbcTemplate.queryForObject(  
	        		"select * from student where stuId=?",   
	                new Object[]{id},  
	                new RowMapper(){  
	                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
	                    	Student stu  = new Student();  
	                    	stu.setStuId(rs.getInt("stuId"));
	    					stu.setStuName(rs.getString("stuName"));
	    					stu.setClassId(rs.getInt("classId")); 
	                        return stu;  
	                    }
	        }); //class是结果数据的java类型  
	    }  
	     //添加
	     public int create(Student stu) {
	        return jdbcTemplate.update("insert into student(stuId, stuName,classId) values(?,?,?)",
	        		stu.getStuId(),stu.getStuName(),stu.getClassId());
	    }
	     
	    //删除
	    public int deleteById(int stuId) {
	        return jdbcTemplate.update("delete from student where stuId = ?", stuId);
	    }
	    //更新
	    public int updateById(Student stu) {
	    	return jdbcTemplate.update("update student set stuName = ?,classId = ? where stuId = ?", 
	    			stu.getStuName(),stu.getClassId(),stu.getStuId());
	    }
	    
	    /**
	    * 求 查询 显示 总页数
	    */
	    public int getTotalPages(int pageSize,List<Student> list) {
//	    int totalRows = jdbcTemplate.queryForInt("select count(*) from student");
//	    List list=jdbcTemplate.queryForList("select count(*) from student");
	    	
	    int totalRows=list.size();
	    // 总行数 % 每页行数 = 0
	    if (totalRows % pageSize == 0) {
	    return totalRows / pageSize;
	    } else {
	    // 总行数 % 每页行数 != 0
	    return totalRows / pageSize + 1;
	    }
	    }
	    
	    /**
	    * 所有记录的分页
	    * 求 员工显示页面 具体页数 ( list 是返回的具体条目 )
	    */
	    public List getPages(int currentPage,int pageSize){
	    List list= jdbcTemplate.queryForList("select * from student limit ?,?",
	    new Object[] { (currentPage * pageSize - pageSize), pageSize });
	    return list;
	    }
	    /**
	     * 条件查询结果的分页
	     * 求 员工显示页面 具体页数 ( list 是返回的具体条目 )
	     */
	    public List selectPages( String item,String str,int currentPage,int pageSize){
	    	String sql = "select * from student where "+item+"=? limit ?,?";
	    	List list= jdbcTemplate.queryForList(sql,
	    			new Object[] { str,(currentPage * pageSize - pageSize), pageSize });
	    	return list;
	    }
	 	
	  //根据条件查询学生信息 
	    public List<Student> getStuList(String item,String str)
	    {
	    	String sql =  "select * from student where "+item+"=?";
	        return (List<Student>)jdbcTemplate.query(sql,new Object[]{str},new RowMapper<Student>()
	        {
	            public Student mapRow(ResultSet rs, int rowNum)
	                throws SQLException
	            {
	            	Student stu = new Student();
	            	stu.setStuId(rs.getInt("stuId"));
					stu.setStuName(rs.getString("stuName"));
					stu.setClassId(rs.getInt("classId"));
	                return stu;
	            }
	        });
	    }
	    
//	    @SuppressWarnings("unchecked")  
//	    public List<Student> getStuList(String item,String str) {  
//	    	System.out.println(item);
//	    	System.out.println(str);
////	    	int a=Integer.valueOf(str);
//	        return (List<Student>) jdbcTemplate.queryForList("select * from student where ? = ?",   
//	                            new Object[]{item,str},  
//	                            Student.class);  
//	    }  
	    
//	    @SuppressWarnings("unchecked")  
//	    public List<Student> getStuList(String item,String str) {  
//	    	int a=Integer.valueOf(str);
//	        return (List<Student>) jdbcTemplate.queryForList("select * from student where ? = ?",  
//	                            new Object[]{a},  
//	                            new int[]{java.sql.Types.VARCHAR},  
//	                            Student.class);  
//	    }
}
