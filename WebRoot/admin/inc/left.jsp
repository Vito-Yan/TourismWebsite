<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页左侧导航</title>
<link rel="stylesheet" type="text/css" href="../css/public.css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/public.js"></script>
<head></head>

<body id="bg">
	<!-- 左边节点 -->
	<div class="container">

		<div class="leftsidebar_box">
			<a href="../main.jsp" target="main"><div class="line">
					<img src="../img/coin01.png" />&nbsp;&nbsp;首页
				</div></a>
			<!-- <dl class="system_log">
			<dt><img class="icon1" src="../img/coin01.png" /><img class="icon2"src="../img/coin02.png" />
				首页<img class="icon3" src="../img/coin19.png" /><img class="icon4" src="../img/coin20.png" /></dt>
		</dl> -->
			<dl class="system_log">
				<dt>
					<img class="icon1" src="../img/coin03.png" /><img class="icon2"
						src="../img/coin04.png" /> 网站管理<img class="icon3"
						src="../img/coin19.png" /><img class="icon4"
						src="../img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a class="cks" href="../user.html"
						target="main">管理员管理</a><img class="icon5" src="../img/coin21.png" />
				</dd>
			</dl>
			<dl class="system_log">
				<dt>
					<img class="icon1" src="../img/coin05.png" /><img class="icon2"
						src="../img/coin06.png" /> 旅游线路管理<img class="icon3"
						src="../img/coin19.png" /><img class="icon4"
						src="../img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a class="cks" href="<%=path%>/AdminManagerServlet?flag=_showType"
						target="main">添加线路</a><img class="icon5" src="../img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a class="cks" href="../seeLine.jsp"
						target="main">查看线路</a><img class="icon5" src="../img/coin21.png" />
				</dd>
			</dl>
			<dl class="system_log">
				<dt>
					<img class="icon1" src="../img/coin07.png" /><img class="icon2"
						src="../img/coin08.png" /> 团购信息管理<img class="icon3"
						src="../img/coin19.png" /><img class="icon4"
						src="../img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a class="cks" href="../seeLine.jsp?op=team"
						target="main">查看线路</a><img class="icon5" src="../img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a class="cks" href="../seeTeam.jsp"
						target="main">查看团购</a><img class="icon5" src="../img/coin21.png" />
				</dd>
			</dl>
			<dl class="system_log">
				<dt>
					<img class="icon1" src="../img/coin14.png" /><img class="icon2"
						src="../img/coin13.png" /> 线路类型管理<img class="icon3"
						src="../img/coin19.png" /><img class="icon4"
						src="../img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a class="cks" href="../addLineType.jsp"
						target="main">添加线路类型</a><img class="icon5" src="../img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a class="cks" href="../seeLineType.jsp"
						target="main">查看线路类型</a><img class="icon5" src="../img/coin21.png" />
				</dd>
			</dl>
			<dl class="system_log">
				<dt>
					<img class="icon1" src="../img/coinL1.png" /><img class="icon2"
						src="../img/coinL2.png" /> 系统管理<img class="icon3"
						src="../img/coin19.png" /><img class="icon4"
						src="../img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a href="../changepwd.html"
						target="main" class="cks">修改密码</a><img class="icon5"
						src="../img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="../img/coin111.png" /><img class="coin22"
						src="../img/coin222.png" /><a class="cks">退出</a><img
						class="icon5" src="../img/coin21.png" />
				</dd>
			</dl>

		</div>

	</div>
</body>
</html>
