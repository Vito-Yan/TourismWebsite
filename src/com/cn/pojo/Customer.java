package com.cn.pojo;

/**
 * 客户类
 */
public class Customer {

	private String  tel	;//手机号
	private int  type;//用户类型
	private  int   customerID;//客户编号
	private String name	;//用户真实名
	private String password;//密码
	private String identityID;//身份证号
	private String gender;//性别
	private String account;//登录名
	
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdentityID() {
		return identityID;
	}
	public void setIdentityID(String identityID) {
		this.identityID = identityID;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
