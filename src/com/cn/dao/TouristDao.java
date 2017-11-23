package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cn.pojo.Tourist;
import com.cn.utils.DbUtils;
import com.cn.utils.TypeEnum;

public class TouristDao {
	
	public  int  addTourist(Tourist  tourist){
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql="insert into Tourist(touristID,IDCard,tel,realName) values(?,?,?,?)";
			
			 stat=conn.prepareStatement(sql);
			 stat.setString(1, tourist.getTouristID());
			 stat.setString(2, tourist.getIDCard());
			 stat.setString(3,tourist.getTel());
			 stat.setString(4,tourist.getRealName());
			 return stat.executeUpdate();
	    }catch (SQLException e) {
			 e.printStackTrace();
		}finally{
			try {
				DbUtils.close(null, stat, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
		
	}
	//通过线路id查询该线路有多少人出游
	public  int findTouristCount(String  lineID){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = DbUtils.getConnection();
			String sql="select count(*) as totals from dbo.Tourist " +
					   " where touristID in( select touristID" +
					   " from   dbo.OT_Detail where odID in(" +
					   " select  odID from dbo.OrderDetail" +
					   " where LineID=?))";
	        stat=conn.prepareStatement(sql);
	  
	        stat.setString(1,lineID);
	      
	        
			rs = stat.executeQuery();
			if (rs.next()) {
				count=rs.getInt("totals");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(null, stat, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;	

	}
	//查询游客人数为共出游的人数
	public   int findAllTouristCount(){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = DbUtils.getConnection();
			String sql="select count(*) as totals from Tourist";
	        stat=conn.prepareStatement(sql);
			rs = stat.executeQuery();
			if (rs.next()) {
				count=rs.getInt("totals");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(null, stat, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;	
	}

}
