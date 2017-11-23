<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>订单</title>
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css" />
<style>
em { font-weight: bold; padding-right: 1em; vertical-align: top; }
em.error {
  background:url("<%=path%>/images/admin/images/unchecked.gif") no-repeat 0px 0px;
  padding-left: 16px;
}
em.success {
  background:url("<%=path%>/images/admin/images/checked.gif") no-repeat 0px 0px;
  padding-left: 16px;
}

.txt{
     border:0px;

}
</style>
<script src="<%=path%>/js/jquery-1.3.1.js" type="text/javascript"></script>
<script src="<%=path%>/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/js/date.js" type="text/javascript"></script>
<script>
   //添加游客信息
   var  count=1;
   function addInfo(){
      count++;
      $("#info").append($("<div class='dingdan-mm' id='"+(count)+"'><div class='dingdan-x'>游客信息</div>"+
                        " <div class='youke'><ul><li><span>*</span> 真实姓名"+
                        "<input name='realName' type='text' id='name"+(count)+"' size='20'  class='txt'/>"+
                        "</li><li><span>*</span> 手机 <input name='tel'class='txt' type='text' id='tel"+count+"' size='20' />"+
                        "</li><li><span>*</span> 身 份 证&nbsp;<input name='IdCard' class='txt' type='text' id='IdCard"+count+"' size='20'/>"+
                        "</li><li><span onclick='addInfo()'>+添加游客信息</span> "+
                        " <span onclick='removeInfo("+count+")'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-移除该信息栏 </span></li></ul></div>'"));
     
   }
   //移除游客信息
   function  removeInfo(id){
         $("#"+id).remove();
   
   }
  
</script>
<script>
  $(document).ready(function(){
  //设置validator为测试模式不提交表单
    //校验身份证
    $.validator.addMethod("regIdCard", function (value, element) {    
       // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
       var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
       return reg.test(value);
    },'身份证号格式有误');  
     //校验出行日期,出行日期必须大于今天
    $.validator.addMethod("ckDate", function (value, element) {  
       var  date=new Date();  
       var  time=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
       var time1 = new Date(time.replace("-", "/").replace("-", "/"));
       var time2 = new Date(value.replace("-", "/").replace("-", "/"));
       return (time2>time1);
    },'请选择大于今天的出游日期');  
    //校验电话
    $.validator.addMethod("regTel", function (value, element) {    
       var reg =  /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;  
       return reg.test(value);
    },'手机号码格有误');  
   
 	
 	
 	
	$("#commentForm").validate({
		rules: {
			realName:{
			    required: true,
			    minlength:2,
				maxlength:6
			},
			IdCard:{
			   required: true,
			   regIdCard:$("#IdCard").val()
			
			},
			tel:{
			   required: true,
			   regTel:$("#tel").val()
			
			},td:{
			  required:true,
			  ckDate:$("#td").val()
			}
			
		},
		
		messages: {
			
			realName:{
			    required: '姓名不能为空',
			    minlength:'长度不少于2位',
				maxlength:'长度不大于6位'
			},
			IdCard:{
			   required:'身份证号不为空'
			},
			tel:{
			   required: '手机号码不为空'
			},
			td:{
			  required:'出游时间不为空'
			}
			
		},	
		
		errorElement: "em",				//用来创建错误提示信息标签
		success: function(label) {			//验证成功后的执行的回调函数
			//label指向上面那个错误提示信息标签em
			label.text(" ").addClass("success");	//加上自定义的success类
				
		}
        
	  });
	
  }); 
  
</script>
</head>

<body>
<!--头部-->
<div id="top-cont">
<div id="top-min">
<div id="min-left"></div>
<div id="min-right"></div>
</div>
</div>
<!--导航-->
<div id="menu">
<div id="menu-m">
<ul>
<li><a href="#">首页</a></li>
<li style="width:8px;"><img src="<%=path%>/images/t1.jpg" width="8" height="42" /></li>
<li><a href="#">境内游</a></li>
<li style="width:8px;"><img src="<%=path%>/images/t1.jpg" width="8" height="42" /></li>
<li><a href="#">境外游</a></li>
<li style="width:8px;"><img src="<%=path%>/images/t1.jpg" width="8" height="42" /></li>
<li><a href="#">热门游</a></li> 
<li style="width:8px;"><img src="<%=path%>/images/t1.jpg" width="8" height="42" /></li>
<li><a href="#">团购</a></li>
<li><a href="#"><img src="<%=path%>/images/che.jpg" width="176" height="42" /></a></li>
</ul>
</div>
</div>
<!--广告位图片-->
<div id="banner"><img src="<%=path%>/images/banner.jpg" width="1003" height="93" /></div>
<!--主体内容第一部分开始-->
<div class="dingdan">
<div class="dingdan-m">
<ul>
<li><img src="<%=path%>/images/n11.jpg" width="205" height="62"  id="one"/></li>
<li><img src="<%=path%>/images/jt21.jpg" width="44" height="60"  id="oneNext"/></li>
<li><img src="<%=path%>/images/n2.jpg" width="205" height="62" id="two"/></li>
<li><img src="<%=path%>/images/jt2.jpg" width="44" height="60" id="twoNext"/></li>
<li><img src="<%=path%>/images/n33.jpg" width="205" height="62" id="three"/></li>
<li></li>
</ul>
</div>
<form action="<%=path%>/OrderManagerServlet?flag=_confirm" method="post" id="commentForm">
<div class="dingdan-inf" >
<div class="dingdan-infm">线路信息</div>
<div class="dingdan-mm">
<div class="dingdan-x">线路信息</div>
<div class="dingdan-lx">
<ul>
<li class="ds">线路编号</li>
<li class="ds">线路名称</li>
<li class="ds">单价</li>
<li class="ds">出行工具</li>
<li>${fn:substring(lineId,0,fn:indexOf(lineId,'-'))}</li>
<li>
${lineName}
   <input type="hidden" name="lineName" value="${lineName}"/>
   <input type="hidden" name="lineId" value="${lineId}"/>
   <input type="hidden" name="price" value="${price}"/>
   <input type="hidden" name="vehicle" value="${vehicle}"/>
   <input type="hidden" name="carId" value="${carId}"/>
   <input type="hidden" name="target" value="${target}"/>
</li>
 <li>￥${price}元</li>
 <li>
${vehicle}

</li>
</ul>
</div>
</div>
<div class="dingdan-infm">填写游客信息&nbsp;&nbsp;&nbsp;&nbsp;<span>请准确填写游客信息。</span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  &nbsp;<span style="color:red">*</span>
  <span style="color:black">出游时间
  <input type="text" class="txt" size="10" name="td" id="td" value="${td}" onclick="SelectDate(this)" readonly="readonly"></input>
  </span>
</div>
<c:forEach  items="${rNames}" varStatus="calc">
<div class="dingdan-mm">
  <div class="dingdan-x">游客信息</div>
  <div class="youke">
  <ul>
  <li>
      <span>*</span> 真实姓名 
      <input name="realName" type="text" value="${rNames[calc.index]}" id="realName"  class="txt" size="20" />
  </li>
   <li>
         <span>*</span> 手机 
          <input name="tel" type="text"  value="${tels[calc.index]}" id="tel" size="20"   class="txt" />
   </li>
   <li>
     <span>*</span> 身 份 证&nbsp;
     <input name="IdCard" type="text"  value="${IdCards[calc.index]}" id="IdCard"   class="txt" size="20" />
   </li>
   <li>
      <span onclick="addInfo()">+添加游客信息 </span>
   </li>
    </ul>
  </div>
</div>
</c:forEach>
<div id="info">

</div>


<div class="xyb">
  <input name="button" type="image" id="button" onclick="next();" value="提交" src="<%=path%>/images/xyb2.jpg" />
</div>
</div>
</form>
</div>

<!--主体内容第一部分结束-->
<!--底部-->
<div id="foot">
<div id="foot-t"><p>许可证编号：WE125482154 鄂ICP备125478966</p>
<p><a href="#">隐私保护</a> | <a href="#">诚聘英才</a> | <a href="#">关于我们</a> | <a href="#">网站地图</a> | <a href="#">友情链接</a> | <a href="#">商务合作</a></p>
</div>
</div>
</body>
</html>
