<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%String path=request.getContextPath();%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<style type="text/css">
* {
	padding: 0;
	margin: 0;
	font-family: "Microsoft YaHei";
	font-size: 14px;
}
#hides{display: none;}
a {
	text-decoration: none;
}

li {
	list-style-type: none;
}

#pageAll {
	width: 100%;
	overflow: hidden;
}

#pageAll .page {
	width: 96%;
	margin: 0px auto;
}

.pageTop {
	width: 100%;
	height: 45px;
	line-height: 45px;
	background-color: #f2f2f2;
}

.pageTop span {
	margin-left: 15px;
}

.pageTop a {
	color: #3695cc;
}

</style>

<body>
	<div class="pageTop">
		<div class="page">
			<img src="img/coin02.png" /><span><a href="#">首页</a>&nbsp;-&nbsp;<a
				href="#">线路类型管理</a>&nbsp;-</span>&nbsp;查看线路类型
		</div>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" >
      <tr>     
        <td >
       <iframe src="<%=path%>/AdminManagerServlet?flag=_seeType"  width="100%" height="430px" frameborder="0" border="0"></iframe>
    	</td>
      </tr>   
	</table>    
</body>
