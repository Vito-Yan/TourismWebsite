package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cn.pojo.OrderDetail;
import com.cn.utils.DbUtils;


public class OrderDetailDao {

	public  int  addOrderDetail(OrderDetail ordersDetail){
		
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql="insert into OrderDetail(odID,customerID,lineName,price,orderDate,total,lineID,state,travelDate) values(?,?,?,?,?,?,?,?,?)";
			 stat=conn.prepareStatement(sql);
			 stat.setString(1, ordersDetail.getOdID());
			 stat.setInt(2, ordersDetail.getCustomerID());
			 stat.setString(3, ordersDetail.getLineName());
			 stat.setDouble(4,ordersDetail.getPrice());
			 stat.setString(5,ordersDetail.getOrderDate());
			 stat.setDouble(6, ordersDetail.getTotal());
			 stat.setString(7, ordersDetail.getLineID());
			 stat.setInt(8, ordersDetail.getState());
			 stat.setString(9, ordersDetail.getTravelDate());
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
	
	//通过客户编号查询该用户所有订单按照订单订购时间排序
	public    List<OrderDetail>  findOrderDetailByCustomerId(int beginNum,int maxResult,int customerId,int state){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<OrderDetail> orderList=new ArrayList<OrderDetail>();
		try {
			conn = DbUtils.getConnection();
			String sql= " select  top "+maxResult+" * from dbo.OrderDetail" +
					    " where  odID not in(select top "+beginNum+" odID " +
					    " from  dbo.OrderDetail where  customerID=? and state=? order by  " +
					    " orderDate desc ) and customerID=? and state=? order by orderDate desc ";
	        stat=conn.prepareStatement(sql); 
		    stat.setInt(1, customerId);
		    stat.setInt(2,state);
		    stat.setInt(3, customerId);
		    stat.setInt(4,state);
			rs = stat.executeQuery();
			while (rs.next()) {
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setCustomerID(rs.getInt("customerID"));
				orderDetail.setLineID(rs.getString("lineID"));
				orderDetail.setOdID(rs.getString("odID"));
				orderDetail.setOrderDate(rs.getString("orderDate"));
				orderDetail.setLineName(rs.getString("lineName"));
				orderDetail.setTravelDate(rs.getString("travelDate"));
				orderDetail.setPrice(rs.getDouble("price"));
				orderDetail.setTotal(rs.getDouble("total"));
				orderList.add(orderDetail);
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

		return orderList;
		
	}
	//查询当前用户订单总数
	public  int   findOrderDetailByCustomerIdCount(int customerId,int state){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = DbUtils.getConnection();
			String sql= " select count(*) as totals" +
					    " from  dbo.OrderDetail" +
					    " where  customerID=? and state=?";
	        stat=conn.prepareStatement(sql);
            stat.setInt(1, customerId);
            stat.setInt(2, state);
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
	//按照订单编号修改当单状态
	public int  updateOrderDetatil(OrderDetail  orderDetail){
		
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();
			 String sql="update OrderDetail set state=? where odID=?";
			 stat=conn.prepareStatement(sql);
			 stat.setInt(1,orderDetail.getState());
			 stat.setString(2, orderDetail.getOdID());
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
