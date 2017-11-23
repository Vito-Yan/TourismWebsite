<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>途乐行后台管理登录</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/public.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/page.css" />
<script type="text/javascript" src="<%=path%>/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/admin/js/public.js"></script>

<script type="text/javascript">
	
	function replaceImage(){
          var imgObj=document.getElementById("codePic");
          imgObj.src="<%=path%>/VerificationCode?ran="+Math.random();
      }
 
</script>

</head>
<body>

	<!-- 登录页面头部 -->
	<div class="logHead">
		<img src="<%=path%>/admin/img/logo.jpg" />
	</div>
	<!-- 登录页面头部结束 -->

	<!-- 登录body -->
	<form action="<%=path%>/AdminManagerServlet?flag=adminLogin"" method="post">
	<div class="logDiv">
		<img class="logBanner" src="<%=path%>/admin/img/timg.jpg" />
		<div class="logGet">
			<!-- 头部提示信息 -->
			<div class="logD logDtip">
				<p class="p1">途乐行后台管理系统</p>
			</div>
			<!-- 输入框 -->
			<div class="lgD">
				<img class="img1" src="<%=path%>/admin/img/logName.png" /><input type="text"
					placeholder="输入用户名" name="username"/>
			</div>
			<div class="lgD">
				<img class="img1" src="<%=path%>/admin/img/logPwd.png" /><input type="password"
					placeholder="输入用户密码" name="password"/>
			</div>
			<div class="lgD logD2">
				<input class="getYZM" type="text" name="verifycode" value="" maxLength=4/>
				<div class="logYZM">
					<img class="yzm" src="<%=path%>/VerificationCode" id="codePic" onclick="replaceImage()"/> 
					
				</div>
				<br/><span style="color:red;font-size:14px">${msg}</span>
			</div>
		
			<div class="logC">
				<button>登 录</button>
			</div>
			
		</div>
	</div>
	</form>
	<!-- 登录body  end -->

	<!-- 登录页面底部 -->
	<div class="logFoot">
		<p class="p1">版权所有：荆州网络科技有限公司</p>
		<p class="p2">荆州设易网络科技有限公司 登记序号：鄂ICP备11003578号-2</p>
	</div>
	<!-- 登录页面底部end -->
</body>
</html>
