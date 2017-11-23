package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.pojo.Line;
import com.cn.pojo.LineType;
import com.cn.utils.DbUtils;

public class LineTypeDao {
	
	//查出前maxResult主页面显示的产品分类
		public   List<LineType>   findTopLine(int maxResult){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			LineType  lineType = null;
			List<LineType> lineList=new ArrayList<LineType>();
			try {
				conn = DbUtils.getConnection();
				String sql="";
				if(maxResult>0){
					 sql= "select top "+maxResult+" * from  LineType   order by time desc";
				}else{
					sql= "select  * from  LineType   order by time desc";
				}
				
			    stat=conn.prepareStatement(sql);
			  
				rs = stat.executeQuery();
				while (rs.next()) {
					lineType=new LineType();
					lineType.setLineTypeID(rs.getString("lineTypeID"));
					lineType.setTypeName(rs.getString("typeName"));
					lineType.setIcon(rs.getString("icon"));
					lineList.add(lineType);
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

			return lineList;
			  
		}
		//添加路线类型
		public  int  addLineType(LineType lineType){
			System.out.println(lineType.getLineTypeID());
			 Connection conn=null;
			 PreparedStatement stat=null;
			 try {
				 conn=DbUtils.getConnection();
				 String sql=" insert LineType (typeName,icon,time,lineTypeID)" +
				 		    " values(?,?,?,?)";
				 stat=conn.prepareStatement(sql);
				 stat.setString(1, lineType.getTypeName());
				 stat.setString(2, lineType.getIcon());
				 stat.setString(3,lineType.getOntime());
				 stat.setString(4,lineType.getLineTypeID());
				 
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
		//查找所有路线类型
		public   List<LineType>   queryAllLineType(int beginNum ,int maxResult){
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			LineType  lineType = null;
			List<LineType> lineList=new ArrayList<LineType>();
			try {
				conn = DbUtils.getConnection();
				String sql=" select top "+maxResult+" * from LineType " +
						   " where  lineTypeID not in(" +
						   " select top "+beginNum+" lineTypeID " +
						   " from LineType order by  time desc )" +
						   " order  by time desc";
				stat=conn.prepareStatement(sql);
				System.out.println(sql);
				rs = stat.executeQuery();
				while (rs.next()) {
					lineType=new LineType();
					lineType.setLineTypeID(rs.getString("lineTypeID"));
					lineType.setTypeName(rs.getString("typeName"));
					lineType.setIcon(rs.getString("icon"));
					lineList.add(lineType);
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

			return lineList;
			  
		}
	//查询线路类型总数	
    public  int  queryLineTypeCount(){	
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			int count=0;
			try {
				conn = DbUtils.getConnection();
				String sql= "select count(*) as totals from LineType" ;
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
    
    //按照线路类型id查询线路类型
    public  LineType  queryLineType(String typeId){
    	Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		LineType  lineType = null;
		try {
			conn = DbUtils.getConnection();
			String sql=" select * from LineType where  lineTypeID=?";
			stat=conn.prepareStatement(sql);
			stat.setString(1, typeId);
		    rs=stat.executeQuery();
			if(rs.next()) {
				lineType=new LineType();
				lineType.setLineTypeID(rs.getString("lineTypeID"));
				lineType.setTypeName(rs.getString("typeName"));
				lineType.setIcon(rs.getString("icon"));

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

		return lineType;
    	
    }
    //修改线路类型
    public  int  updateLineType(LineType lineType){
    	Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql="update LineType set typeName=?";
			 if((lineType.getOntime()!=null) && lineType.getIcon()!=null){
				 
				sql+=", time=? , icon=?  where lineTypeID=?";
			 }else if((lineType.getOntime()==null) && lineType.getIcon()!=null){
				 
				sql+=" , icon=?  where lineTypeID=?";
				 
			 }else if((lineType.getOntime()!=null) && lineType.getIcon()==null){
				 
				sql+=" , time=?  where lineTypeID=?";
				 
			 }else{
				 
				 sql+=" where lineTypeID=?";
			 }
			 System.out.println(sql);
			 stat=conn.prepareStatement(sql);
			 stat.setString(1, lineType.getTypeName());
			 if((lineType.getOntime()!=null) && lineType.getIcon()!=null){
				 stat.setString(2, lineType.getOntime());
				 stat.setString(3, lineType.getIcon());
				 stat.setString(4, lineType.getLineTypeID());
		      }else if((lineType.getOntime()==null) && lineType.getIcon()!=null){
				  stat.setString(2, lineType.getIcon());
				  stat.setString(3, lineType.getLineTypeID());
			  }else if((lineType.getOntime()!=null) && lineType.getIcon()==null){
				  stat.setString(2, lineType.getOntime());
				  stat.setString(3, lineType.getLineTypeID());	 
			  }else{
				  stat.setString(2, lineType.getLineTypeID());
				  
			  }
			  int a=stat.executeUpdate();
			 return a;
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
