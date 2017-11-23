package com.cn.pojo;


//客户订单明细表
public class OrderDetail {
	private String odID;//订单编号
	private int customerID;//客户编号
	private String  lineName;//线路名
	private double  price;//价格
	private  String  orderDate;//订单时间
	private  double total;//总价
	private  String  lineID;//线路ID
	private  int state;//状态
	private  String  travelDate;//出游时间
	
	public String getOdID() {
		return odID;
	}
	public void setOdID(String odID) {
		this.odID = odID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getLineID() {
		return lineID;
	}
	public void setLineID(String lineID) {
		this.lineID = lineID;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	
	
	
	
	
}
