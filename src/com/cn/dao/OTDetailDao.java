package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cn.pojo.OTDetail;
import com.cn.utils.DbUtils;

public class OTDetailDao {
	
	
	public int   addOTDetail(OTDetail  oTDetail){
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			
			 String sql="insert into OT_Detail(odID,touristID) values(?,?)";
			 stat=conn.prepareStatement(sql);
			 stat.setString(1, oTDetail.getOdID());
			 stat.setString(2, oTDetail.getTouristID());
			
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

}
