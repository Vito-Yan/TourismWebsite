<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css"/>
<style>
.ul{
   list-style: none;text-align: left;
}
.li{ list-style: none;text-align: left;}
#divcss5{border:1px #ff8000 solid; padding:10px; width:150px; 
margin-left:10px; margin-top:10px} 
#divcss5 li{overflow:hidden;text-overflow:ellipsis; } 
#divcss5 li a:hover{ color:#333} 
</style>
<div class="jdtj-inf">
<ul>
<c:forEach var="line" items="${lineList}" varStatus="timmer">
<li><a href="<%=path%>/LineManagerServlet?lineId=${line.lineID}&flag=_specific" target="_blank"><img src="<%=path%>/upload/${line.lineID}/${picList.get(timmer.index).name}" width="240" height="160" /><span>线路名：</span>${line.lineName}</a><span>&nbsp;&nbsp;价格</span>:<c:choose>
    <c:when test="${line.teamBuy==1}">
         <c:out value="${fn:substring(line.teamBuyPrice,0,fn:indexOf(line.teamBuyPrice,'.'))}"/>元
    </c:when>
    <c:otherwise>
          <c:out value="${fn:substring(line.price,0,fn:indexOf(line.price,'.'))}"/>元
    </c:otherwise>
</c:choose><br>
<div>简介:${fn:substring(line.introduction,0,11)}...</div>
</c:forEach>
</ul>
</div>
<div class="jdfy" style="clear:left">
   <a href="<%=path%>/LineManagerServlet?flag=_allLine&lineTypeId=${lineTypeId}&currentPage=1">首页</a>   
   <a href="<%=path%>/LineManagerServlet?flag=_allLine&lineTypeId=${lineTypeId}&currentPage=${currentPage<=1?currentPage:currentPage-1}">上一页</a>  
   <a href="<%=path%>/LineManagerServlet?flag=_allLine&lineTypeId=${lineTypeId}&currentPage=${currentPage<bottomPage?currentPage+1:bottomPage}">下一页</a>   
   <a href="<%=path%>/LineManagerServlet?flag=_allLine&lineTypeId=${lineTypeId}&currentPage=${bottomPage}">尾页</a>
</div>