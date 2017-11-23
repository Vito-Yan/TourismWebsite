<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>团购信息</title>
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/prompt.css" rel="stylesheet" type="text/css" />
<style>
#BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:1000px;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
#DialogDiv{position:absolute;width:400px; left:50%; top:50%; margin-left:-200px; height:auto; z-index:100; padding:1px;}
#DialogDiv h2{ height:25px; font-size:14px; background-color:#8FA4F5; position:relative; padding-left:10px; line-height:25px;}
#DialogDiv h2 a{position:absolute; right:5px; font-size:12px; color:#000000}
#DialogDiv .form{padding:10px;}
</style>
<script src="<%=path%>/js/jquery-1.3.1.js" type="text/javascript"></script>
<script>
  function seeCar(){
	   
    var  account="<%=session.getAttribute("account")%>";
    if(account=="null"){
        ShowDIV('DialogDiv');
    }else{
        window.location.href="ShoppingCarManagerServlet?flag=_seeCar";
    }
 }
 function ShowDIV(thisObjID) {
    $("#BgDiv").css({ display: "block", height: $(document).height() });
    $("#" + thisObjID ).css("top", "100px");
    $("#" + thisObjID ).css("display", "block");
    document.documentElement.scrollTop = 0;
}
 function closeDiv(thisObjID) {
    $("#BgDiv").css("display", "none");
    $("#" + thisObjID).css("display", "none");
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
<!--导航-->
<div id="menu">
<div id="menu-m">
<ul>
<li><a href="<%=path%>/LineManagerServlet?flag=_showLine">首页</a></li>
<li style="width:8px;"><img src="<%=path%>/images/t1.jpg" width="8" height="42" /></li>
 <c:forEach var="llm" items="${ltl}" begin="0" end="2"  >
           <li><a href="<%=path%>/LineManagerServlet?typeName=${llm.typeName}&flag=_hot&lineTypeId=${llm.lineTypeID}">${llm.typeName}</a></li>
           <li style="width:8px;"><img src="<%=path%>/images/t1.jpg" width="8" height="42" /></li>
  </c:forEach>
<li><a href="<%=path%>/TeamBuyManagerServlet?flag=_hundredRegiments">团购</a></li>
<li><a href="javascript:seeCar()"><img src="<%=path%>/images/che.jpg" width="176" height="42" /></a></li>
</ul>
</div>
</div>
<!--广告位图片-->
<div id="banner"><img src="<%=path%>/images/tgg1.jpg" width="1003" height="190" /></div>
<!--主体内容第一部分开始-->
<div class="tuangouy" style="overflow: hidden">
<ul>
<c:forEach var="team" items="${atlList}" varStatus="timmer">
<li>
<div><a href="<%=path%>/LineManagerServlet?lineId=${team.lineID}&flag=_specific"><img src="<%=path%>/upload/${team.lineID}/${picList.get(timmer.index).name}" width="305" height="200" /></a></div>
<div class="tuangouy-m"><a href="#">[全国联保]${team.lineName}</a>
<div>简介:${fn:substring(team.introduction,0,15)}...</div>
<span>团价￥<c:out value="${fn:substring(team.teamBuyPrice,0,fn:indexOf(team.teamBuyPrice,'.'))}"/>元</span>&nbsp;&nbsp;原&nbsp;价：￥${fn:substring(team.price,0,fn:indexOf(team.price,'.'))}元
</div>
<div class="fengsu">已有${countList.get(timmer.index)}人出游</div>
</li>
</c:forEach>

</ul>
</div>
<div class="tgfy">
  <a href="<%=path%>/TeamBuyManagerServlet?flag=_hundredRegiments&currentPage=1">首页</a>   
  <a href="<%=path%>/TeamBuyManagerServlet?flag=_hundredRegiments&currentPage=${currentPage<=1?currentPage:currentPage-1}">上一页</a>  
  <a href="<%=path%>/TeamBuyManagerServlet?flag=_hundredRegiments&currentPage=${currentPage<bottomPage?currentPage+1:bottomPage}">下一页</a>   
  <a href="<%=path%>/TeamBuyManagerServlet?flag=_hundredRegiments&currentPage=${bottomPage}">尾页</a>
</div>
<!--主体内容第一部分结束-->
<!--底部-->
<div id="foot">
<div id="foot-t"><p>许可证编号：WE125482154 鄂ICP备125478966</p>
<p><a href="#">隐私保护</a> | <a href="#">诚聘英才</a> | <a href="#">关于我们</a> | <a href="#">网站地图</a> | <a href="#">友情链接</a> | <a href="#">商务合作</a></p>
</div>
</div>
<div id="BgDiv"></div>
   <!--遮罩层显示的DIV1-->
  <div id="DialogDiv" style="display:none">
    <div class="box6">
         <div onclick="closeDiv('DialogDiv')" style="position:relative;left:266px">关闭</div>
       <h1>优旅网系统温馨提示您</h1>   
       <img src="<%=path%>/images/alter.jpg"/>
       <p>对不起，登录后您才可以使用购物车功能</p>
       <div class="box6_corner_lf"></div>
       <div class="box6_corner_rt"></div>
    </div>   
  </div>
</body>
</html>
