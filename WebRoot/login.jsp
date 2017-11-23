<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>登录页面</title>
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
</style>
<script src="<%=path%>/js/jquery-1.3.1.js" type="text/javascript"></script>
<script src="<%=path%>/js/jquery.validate.js" type="text/javascript"></script>
<script>
 $(document).ready(function(){
	$("#commentForm").validate({
		rules: {
			account: {
				required: true,
				maxlength:12
			},
			password:{
			    
			    required: true,
				maxlength:12
			}
			
		},
		
		messages: {
			account: {
				required: '账号不能为空',
				maxlength: '长度不超过12位'
			},
			password: {
				required: '密码不能为空',
			    maxlength: '长度不超过12位'
			}
			
		},	
		
		errorElement: "em",				//用来创建错误提示信息标签
		success: function(label) {			//验证成功后的执行的回调函数
			//label指向上面那个错误提示信息标签em
			label.text(" ")				//清空错误提示消息
				.addClass("success");	//加上自定义的success类
		}


	  });
	
  }); 
  window.onload=function(){
   
      var  msg="<%=(String)request.getAttribute("msg")%>";
       if(msg!="null"){
      
         alert(msg);
       }
      
  }
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

<!--广告位图片-->
<div id="banner"><img src="<%=path%>/images/banner.jpg" width="1003" height="93" /></div>

<!--登录版块-->
<div id="login-bg">
<div id="login-m">
<form  action="<%=path%>/CustomerManagerServlet?flag=_login"  method="post"  id="commentForm">
<ul>
<li>登录名
    <input name="account" type="text" class="box" id="account" size="25" />
</li>
<li>密&nbsp;&nbsp;&nbsp;码
    <input name="password" type="password" class="box" id="password" size="25" style="width:155px" />
</li>
<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input name="timeout" type="checkbox" id="checkbox" checked="checked" />
  30天内自动登录</li>
  <li>
      <input name="button" type="image" id="button" value="提交" src="<%=path%>/images/login-b.jpg" />
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <img  onclick="window.location.href='register.jsp'"      src="<%=path%>/images/login-c.jpg" />
  </li>
  
</ul>
</form>
</div>
</div>

<!--底部-->
<div id="foot">
<div id="foot-t"><p>许可证编号：WE125482154 鄂ICP备125478966</p>
<p><a href="#">隐私保护</a> | <a href="#">诚聘英才</a> | <a href="#">关于我们</a> | <a href="#">网站地图</a> | <a href="#">友情链接</a> | <a href="#">商务合作</a></p>
</div>
</div>
</body>
</html>
