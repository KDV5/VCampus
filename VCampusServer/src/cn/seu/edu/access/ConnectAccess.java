package cn.seu.edu.access;

import java.sql.*;

/*
 * ConnectAccess
 * 连接数据库的类
 *
 * @author Ge Danwei
 *
 * @Date 2016/8/30
 *
 */

public class ConnectAccess {
	private static final String dbpath = "Database//vCampus.accdb";  //数据库相对路径
	private static final String url="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+ dbpath; 
	public PreparedStatement pstmt = null;
	public Connection conn = null;
	
	public ConnectAccess(){	
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection(url,"","");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void finalize(){
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
