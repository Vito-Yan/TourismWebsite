package com.cn.pojo;

/**
 * 购物车类
 *
 */
public class Car {
	private  int  carID ;//购物车ID
	private  int  customerID	;//客户ID
	private  String   lineID	;//线路ID
	private  String  time;//放入时间
	
	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID = carID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getLineID() {
		return lineID;
	}
	public void setLineID(String lineID) {
		this.lineID = lineID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

	
	
	
}
