<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册页面</title>
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css"/>
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
   
    $.validator.addMethod("compare", function (value, element) {    
       var repass=$("#password").val();
       return (value==repass);  
    },'两次密码输入密码不同');  
    //校验身份证
    $.validator.addMethod("regIdCard", function (value, element) {    
       // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
       var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
       return reg.test(value);
    },'身份证号格式不正确');  
    //校验电话
    $.validator.addMethod("regTel", function (value, element) {    
       var reg =  /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;  
       return reg.test(value);
    },'手机号码格式不正确');  
    
   
 
 
	$("#commentForm").validate({
		rules: {
			account: {
				required: true,
				minlength:6,
				maxlength:12
			},
			password:{
			    
			    required: true,
			    minlength:6,
				maxlength:12
			},
			repass:{
			  compare:$("#repass").val()
			},
			realname:{
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
			
			},
			code:{
			  required:true,
			  minlength:4,
			  maxlength:4
			}
			
		},
		
		messages: {
			account: {
				required: '账号不能为空',
				minlength:'账号长度不少于6位',
				maxlength: '账号长度不超过12位'
			},
			password: {
				required: '密码不能为空',
			    minlength:'密码长度不少于6位',
				maxlength: '密码长度不超过12位'
			},
			realname:{
			    required: '姓名不能为空',
			    minlength:'姓名长度不少于2位',
				maxlength:'姓名长度不大于6位'
			},
			IdCard:{
			   required:'身份证号不能为空'
			},
			tel:{
			   required: '手机号码不能为空'
		
			
			},
			code:{
			     required: '验证码不能为空',
			    minlength: '验证码长为4位',
				maxlength: '验证码长为4位'
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
  
  
  function replaceImage(){
          var imgObj=document.getElementById("codePic");
          imgObj.src="<%=path%>/VerificationCode?ran="+Math.random();
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

<!--注册版块-->
<div id="reg">
<div id="reg-top">&nbsp;&nbsp;&nbsp;&nbsp;会员注册</div>
<div id="reg-mleft">
<form  action="<%=path%>/CustomerManagerServlet?flag=_register"  method="post" id="commentForm">
<ul>
<li class="m">用户账户 </li>
<li class="r"><input name="account" type="text" class="box" id="account" /></li>
<li class="m">设置密码 </li>
<li class="r"><input name="password" type="password" class="box" id="password" /></li>
<li class="m">确认密码 </li>
<li class="r"><input name="repass" type="password" class="box" id="repass" /></li>
<li class="m">用户姓名 </li>
<li class="r"><input name="realname" type="text" class="box" id="realname" /></li>
<li class="m">身份证号 </li>
<li class="r"><input name="IdCard" type="text" class="box" id="IdCard" /></li>
<li class="m">性 别 </li>
<li class="r">
    <input name="gender" type="radio" id="radio" value="男" checked="checked" /> 
    男
    <input type="radio" name="gender" id="radio2" value="女" /> 
    女
</li>
<li class="m">手机号码 </li>
<li class="r"><input name="tel" type="text" class="box" id="tel" /></li>
<li class="m">校验码 </li>
<li class="r"><input name="code" type="text" class="box" id="code" size="15" /> 
<img id="codePic" src="<%=path%>/VerificationCode"   style="vertical-align:top;"/>
  看不清？<a href="javascript:replaceImage()">换一张</a></li>
 
<li class="r">
    <input name="button" type="image" id="button" value="提交" src="<%=path%>/images/but2.jpg" />
</li>
  <li class="m"> </li>
<li class="r">
    <input name="checkbox" type="checkbox" id="checkbox" checked="checked" />
《胜境旅游网会员服务条款》 <img src="images/jt.jpg" width="13" height="12" /></li>
</ul>
</form>
</div>

<div id="reg-right">
<div><img src="<%=path%>/images/kf.jpg" width="230" height="150" /></div>
<div id="kf2"><a href="<%=path%>/login.jsp">已有账户，请点击登录</a></div>
<div id="zcdt">欢迎注册成为会员</div>
<div id="dt">
<ul>
   <li><font color='red' size="6">${msg}</font></li>
</ul>
</div>
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
