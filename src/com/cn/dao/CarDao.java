package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.pojo.Car;
import com.cn.pojo.Line;
import com.cn.utils.DbUtils;

public class CarDao {

	public  int  addCar(Car car){
		System.out.println("-----------"+car.getLineID());
			 Connection conn=null;
			 PreparedStatement stat=null;
			 try {
				 conn=DbUtils.getConnection();
				 String sql="insert into Car(customerID,lineID,time) values(?,?,?)";
				 stat=conn.prepareStatement(sql);
				 stat.setInt(1, car.getCustomerID());
				 stat.setString(2, car.getLineID());
				 stat.setString(3,car.getTime());
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
	//根据编号ID查询总数量
	public int     count(int customerID){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = DbUtils.getConnection();
			String sql= "select count(*) as totals from  Car";
	        if(customerID>0){
	        	 sql+= " where customerID=?";
	        }
		    stat=conn.prepareStatement(sql);
		    if(customerID>0){
			   stat.setInt(1, customerID);
		    }
		   
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
	
	public  Line   findCardLine(int customerID,String  lineID){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		Line line=null;
		try {
			conn = DbUtils.getConnection();
			String sql= " select * from Car where  " +
					    " customerID=? and Car.lineID=?";
			stat=conn.prepareStatement(sql);
			stat.setInt(1, customerID);
			stat.setString(2, lineID);
			rs = stat.executeQuery();
			if(rs.next()){
				
		      line=new Line();
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
		return line;
		
	}
	//根据客户ID查询购物车
	public List<Car> findCar(int customerID,int beginNum,int maxResult){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Car> carList=new ArrayList<Car>();
		try {
			conn = DbUtils.getConnection();
			String sql= " select top  "+maxResult+" * from Car " +
					    " where carID not in(" +
					    " select top "+beginNum+" Car.carID from Car" +
					    " order by time  desc ) and customerID=? order by time  desc";
			stat=conn.prepareStatement(sql);
			stat.setInt(1, customerID);
			rs = stat.executeQuery();
			while(rs.next()){
				Car car=new Car();
				car.setCarID(rs.getInt("carID"));
		        car.setCustomerID(rs.getInt("customerID"));
		        car.setLineID(rs.getString("lineID"));
		        car.setTime(rs.getString("time"));
		        carList.add(car);
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
		return carList;
		
	}
	//删除购物车中的数据
	public   int    deleteCar(Car  car){
		
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql="delete  from  Car where carID=?";
			 stat=conn.prepareStatement(sql);
			 stat.setInt(1, car.getCarID());
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
