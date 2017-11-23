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
<title>${typeName}</title>
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
<div id="banner"> <img src="<%=path%>/images/banner.jpg" width="1003" height="93" /></div>

<!--主体内容第一部分开始-->
<div class="jindinf">
<div class="jindinf-left">
<ul>
<c:forEach  var="line" items="${hostLineList}" varStatus="timmer">
<li onmouseover="this.style.cursor='pointer'" onclick="window.location.href='<%=path%>/LineManagerServlet?lineId=${line.lineID}&flag=_specific'">第${timmer.count}名${line.lineName}</li>
<li>
<a>
价格:
<c:choose>
    <c:when test="${line.teamBuy==1}">
         <c:out value="${fn:substring(line.teamBuyPrice,0,fn:indexOf(line.teamBuyPrice,'.'))}"/>元
    </c:when>
    <c:otherwise>
          <c:out value="${fn:substring(line.price,0,fn:indexOf(line.price,'.'))}"/>元
    </c:otherwise>
</c:choose>
</a>  

<a>| 出游工具:
${line.vehicle}</a></li>
</c:forEach>
</ul>
</div>
<div class="jindinf-right"><img src="<%=path%>/images/banner2.jpg" width="793" height="300" /></div>
</div>
<!--主体内容第一部分结束-->
<!--主体内容第二部分开始-->
<div class="huodong">
<div class="huodong-meun1"><ul><li>${typeName}热门游Top10线路</li></ul></div>
<div class="huodong-inf">
<ul>
<c:forEach  var="line" items="${htpLineList}" varStatus="timmer">
<li><a href="<%=path%>/LineManagerServlet?lineId=${line.lineID}&flag=_specific"><img src="<%=path%>/upload/${line.lineID}/${picList.get(timmer.index).name}" width="180" height="120" />
名称：${line.lineName}</a>&nbsp;价格:
<c:choose>
    <c:when test="${line.teamBuy==1}">
         <c:out value="${fn:substring(line.teamBuyPrice,0,fn:indexOf(line.teamBuyPrice,'.'))}"/>元
    </c:when>
    <c:otherwise>
          <c:out value="${fn:substring(line.price,0,fn:indexOf(line.price,'.'))}"/>元
    </c:otherwise>
</c:choose> 
</li>
</c:forEach>
</ul>
</div>
<div></div>
</div>
<!--主体内容第二部分结束-->
<!--主体内容第三部分开始-->
<div class="jinrtg">
<div class="jinrtg-meun">${typeName}团购线路</div>
<div class="jinrtg-inf">
<ul>
<c:forEach  var="line" items="${tlList}" varStatus="timmer">
<li>
  <div class="w16"><a href="<%=path%>/LineManagerServlet?lineId=${line.lineID}&flag=_specific"><img src="<%=path%>/upload/${line.lineID}/${pList.get(timmer.index).name}" width="160" height="120" /></a></div>
  <div class="w16-r"><span style="font-size:15px;color:orange"><strong>(团购)${line.lineName}</strong></span></div>
  <div class="w16-rx">团购价<span>￥<c:out value="${fn:substring(line.teamBuyPrice,0,fn:indexOf(line.teamBuyPrice,'.'))}"/>元
  </span>省<c:out value="${fn:substring(line.price-line.teamBuyPrice,0,fn:indexOf(line.price-line.teamBuyPrice,'.'))}"/>元
  <a href="<%=path%>/LineManagerServlet?lineId=${line.lineID}&flag=_specific"><img src="<%=path%>/images/qg.jpg" width="92" height="32" /></a>
  </div>
</li>
</c:forEach>
</ul>
</div>
</div>
<!--主体内容第三部分结束-->
<!--主体内容第四部分开始-->
<div class="jdtj">
<div class="jdtj-meun">${typeName}所有线路</div>
<iframe  width="1090" src="<%=path%>/LineManagerServlet?flag=_allLine&lineTypeId=${lineTypeId}" height="460px" frameborder="0"></iframe>
</div>
<!--主体内容第四部分结束-->
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
