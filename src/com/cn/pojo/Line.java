package com.cn.pojo;



/**
 *线路类
 *
 */
public class Line {

	private  String lineID	;//线路ID
	private  String lineTypeID;//线路类型ID
	private  String lineName;//线路名
	private  int     days;//几日游
	private  double  price;//价格
	private  String vehicle;//交通工具
	private  String introduction;//线路介绍
	private  int    teamBuy;//是否团购
	private  double teamBuyPrice;//团购价格  
	private  String reason;//路线推荐理由
	private  String arrange;//行程安排
	private  double discount;//优惠
	private  String  beginTime;//团购开始日期
	private  String  endTime;//团购截止日期
	private  String  onTime;//线路上架时间
	private  String  typeName;//线路类型
	public String getLineID() {
		return lineID;
	}
	public void setLineID(String lineID) {
		this.lineID = lineID;
	}
	
	public String getLineTypeID() {
		return lineTypeID;
	}
	public void setLineTypeID(String lineTypeID) {
		this.lineTypeID = lineTypeID;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getTeamBuy() {
		return teamBuy;
	}
	public void setTeamBuy(int teamBuy) {
		this.teamBuy = teamBuy;
	}
	public double getTeamBuyPrice() {
		return teamBuyPrice;
	}
	public void setTeamBuyPrice(double teamBuyPrice) {
		this.teamBuyPrice = teamBuyPrice;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getArrange() {
		return arrange;
	}
	public void setArrange(String arrange) {
		this.arrange = arrange;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getOnTime() {
		return onTime;
	}
	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
	
	
	
	
	
}
