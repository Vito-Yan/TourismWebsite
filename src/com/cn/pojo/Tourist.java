package com.cn.pojo;

public class Tourist {
	
	private  String touristID;//游客ID
	private  String IDCard;//身份证号
	private  String  tel;//手机号
	private  String  realName;//真实姓名
	
	public String getTouristID() {
		return touristID;
	}
	public void setTouristID(String touristID) {
		this.touristID = touristID;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
//	select lin2.lineID
//	from(
//
//	    select top 3  count(lineID) as total,lineID
//	    from dbo.OrderDetail
//	    where lineID not in
//	    (
//	       select lin.lineID
//	       from
//			(
//			  select top 0 count(lineID) as total,lineID
//			  from dbo.OrderDetail
//			  group by  lineID
//			  order by total  desc
//			) as lin
//	     )
//	    group by  lineID
//	    order by total  desc
//	 ) as lin2
//	    
//	)
//



}
