package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cn.pojo.Customer;
import com.cn.utils.DbUtils;

public class CustomerDao {
	
	//根据账号和密码查询
	public   Customer   findCustomer(String account,String password){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		Customer  customer = null;
		try {
			conn = DbUtils.getConnection();
			String sql= "select * from Customer where account=?";
		    if(password!=null){
		    	sql+=" and password=?";
		    }
			stat = conn.prepareStatement(sql);
			stat.setString(1, account);
			if(password!=null){
				stat.setString(2, password);
		    }
			
			rs = stat.executeQuery();
			if (rs.next()) {
				customer=new Customer();
				customer.setAccount(rs.getString("account"));
				customer.setCustomerID(rs.getInt("customerID"));
				customer.setIdentityID(rs.getString("identityID"));
				customer.setType(rs.getInt("type"));
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

		return customer;
		  
	}
	//添加客户信息
	public  int  addCustomer(Customer customer){
		 Connection conn=null;
		 PreparedStatement stat=null;
		 try {
			 conn=DbUtils.getConnection();                                 
			 String sql="insert into Customer(account,name,password,gender,identityID,tel) values(?,?,?,?,?,?)";
			 stat=conn.prepareStatement(sql);
			 stat.setString(1, customer.getAccount());
			 stat.setString(2, customer.getName());
			 stat.setString(3, customer.getPassword());
			 stat.setString(4, customer.getGender());
			 stat.setString(5, customer.getIdentityID());
			 stat.setString(6,customer.getTel());
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
