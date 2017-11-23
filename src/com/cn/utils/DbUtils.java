package com.cn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
		private  static  final String URL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Tule";              
		private  static  final String USER="sa";
		private  static  final String PASSWORD="sa";
		static{
			  try {  
				  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");                
			   } catch (ClassNotFoundException e) {
				   e.printStackTrace();
			   }
		}
		//建立连接
		public  static  Connection  getConnection() throws SQLException{
			return  DriverManager.getConnection(URL,USER,PASSWORD);
		}
		//关闭资源
		public  static  void close(ResultSet rs,Statement  stat,Connection conn) throws SQLException{
			if(rs!=null){
				rs.close();
			}if(stat!=null){
				stat.close();
			}if(conn!=null){
				conn.close();
			}
		}	
}
