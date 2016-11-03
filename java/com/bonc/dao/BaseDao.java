package com.bonc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	public final static String driver = "com.mysql.jdbc.Driver";
//	public final static String url = "jdbc:mysql://localhost:3306/test?";
//	public final static String dbName = "test";
//	public final static String dbPass = "root";
	public final static String url = "jdbc:mysql://localhost:3306/test?"
            + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
	
	public Connection getConn() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}
	public void closeAll(Connection conn,PreparedStatement stat,ResultSet rs){
		try {
			if(rs!=null)
				{
				rs.close();
				}
			 if(rs!=null){
				stat.close();
				}
			 if(conn!=null)
			   {
				conn.close();
			   }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
//	public int executeSQL(String preparedSql,String[] param){
//		return 0;
//		
//	}
	
	
	public int excuteUpdate(String sql, Object[] obj) {
		Connection conn = null;
		PreparedStatement stat = null;
		int ret = 0;
		try {
			conn = getConn();
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				stat.setObject(i + 1, obj[i]);
			}
			ret = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}


}
