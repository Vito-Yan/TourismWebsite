package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.pojo.Line;
import com.cn.pojo.Picture;
import com.cn.utils.DbUtils;

public class PictureDao {
	
	
	public  int  addPicture(Picture picture){
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql=" insert into  Picture(introduction,name,lineID) values(?,?,?)";
			 stat=conn.prepareStatement(sql);
			 stat.setString(1,picture.getIntroduction());
			 stat.setString(2,picture.getName());
			 stat.setString(3,picture.getNo());
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
	//通过线路ID查询图片
	public   List<Picture> findPictrue(String  lineId){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Picture> pictureList=new ArrayList<Picture>();
		try {
			conn = DbUtils.getConnection();
			String sql= "select  * from Picture  where lineID=?";
			stat=conn.prepareStatement(sql);
	        stat.setString(1, lineId);
			rs = stat.executeQuery();
			while (rs.next()) {
				Picture  picture=new Picture();
				picture.setPictureID(rs.getInt("pictureID"));
				picture.setName(rs.getString("name"));
				picture.setIntroduction(rs.getString("introduction"));
				picture.setNo(rs.getString("lineID"));
				pictureList.add(picture);
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

		return pictureList;
	}
	//修改图片
	public  int  updatePicture(Picture picture){
		
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql=null;
			 if(picture.getIntroduction()==null){
				 
				sql="update  Picture  set name=? where pictureID=? ";
			 }else if(picture.getName()==null){
				 
				 sql="update  Picture  set introduction=? where pictureID=? ";
			 }
			
			 stat=conn.prepareStatement(sql);
			 
			 if(picture.getIntroduction()==null){
				 
					stat.setString(1, picture.getName());
			 }else if(picture.getName()==null){

						stat.setString(1, picture.getIntroduction());
		     }
			
			 stat.setInt(2,picture.getPictureID());
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
