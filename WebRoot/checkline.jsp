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
<title>无标题文档</title>
<link href="<%=path%>/css/layout.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/prompt.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/nf.lightbox.css" rel="stylesheet" type="text/css" media="screen" />
<script src="<%=path%>/js/jquery-1.3.1.js" type="text/javascript"></script>
<script src="<%=path%>/js/NFLightBox.js" type="text/javascript"></script>
 <style type="text/css">
        #gallery
        {
    
            padding: 10px;
          
        }
        #gallery ul
        {   margin-left:20px;
            background:red;
            list-style: none;
        }
        #gallery ul li
        {
            float: left;
            list-style:none;
            margin-right:28px;
        }
        #gallery ul img
        {
          
           
            border: 1px solid orange;
            border-width: 1px 1px 1px;
        }
        #gallery ul a:hover img
        {
            border-width: 1px 1px 10px;
         
        }
        #gallery ul a:hover
        {
            color: #orange;
        }
 </style>
<style>
#BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:1000px;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
#DialogDiv{position:absolute;width:400px; left:50%; top:50%; margin-left:-200px; height:auto; z-index:100;padding:1px;}
#DialogDiv h2{ height:25px; font-size:14px; background-color:#8FA4F5; position:relative; padding-left:10px; line-height:25px;}
#DialogDiv h2 a{position:absolute; right:5px; font-size:12px; color:#000000}
#DialogDiv .form{padding:10px;}
</style>
 <script>
       $(function() {
     
        var settings = { containerResizeSpeed: 350
            };
            $('#gallery a').lightBox(settings);
        });
           
</script>
<script>

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
     //添加购物车，判断用户是否登录如果未登录弹出层，提示用户需要登录
    function addCar(id){
	   
        var  account="<%=session.getAttribute("account")%>";
	    if(account=="null"){
	        ShowDIV('DialogDiv');
	    }else{
	         window.location.href="<%=path%>/ShoppingCarManagerServlet?flag=_addCar&lineId="+id;
	    }
    }
    //结算，判断用户是否登录如果未登录弹出层，提示用户需要登录
    function accounting(id){
      var  account="<%=session.getAttribute("account")%>";
      if(account=="null"){
	        ShowDIV('DialogDiv');
	  }else{
	       window.location.href="<%=path%>/OrderManagerServlet?flag=_place&target=lineOrder&lineId="+id;
	  }
    }
    //查看购物车,判断用户是否登录如果未登录弹出层，提示用户需要登录
     function seeCar(){
        var  account="<%=session.getAttribute("account")%>";
        
        if(account=="null"){
           
            ShowDIV('DialogDiv');
        
        }else{
            window.location.href="<%=path%>/ShoppingCarManagerServlet?flag=_seeCar";
        }
     }
    
    window.onload=function(){
          var isHave="<%=request.getAttribute("isHave")%>";
          
          if(isHave!="null"){
            alert(isHave);
          }
    };
    
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
<div id="banner"><img src="<%=path%>/images/banner.jpg" width="1003" height="93" /></div>

<!--主体内容第一部分开始-->
<div class="jindck">
<c:choose>
<c:when test="${line.teamBuy==1}">
<div class="jindck-meun" style=" background:url(<%=path%>/images/gty.jpg) top right no-repeat; height:63px; font-size:22px;
 font-family:'微软雅黑'; color:#090; font-weight:bold; padding-left:20px; line-height:60px; ">[优惠多多+满意多多+优质服务]&nbsp;&nbsp;${line.lineName}
</div>
</c:when>
<c:otherwise>
<div class="jindck-meun" style="height:63px; font-size:22px;
 font-family:'微软雅黑'; color:#090; font-weight:bold; padding-left:20px; line-height:60px; ">[优惠多多+满意多多+优质服务]&nbsp;品质全&nbsp;${line.lineName}
</div>
</c:otherwise>

</c:choose>

<div class="jin2">
<div class="jin2-left" id="gallery"><img src="<%=path%>/upload/${line.lineID}/${picList.get(0).name}" width="400" height="250" style='padding-bottom: 10px' />
   <ul>
            <c:forEach  var="pic" items="${picList}" begin="1" end="3">
             <li>
                <a href="<%=path%>/upload/${line.lineID}/${pic.name}" title="${pic.introduction}">
                    <img src="<%=path%>/upload/${line.lineID}/${pic.name}" width="100" alt="${pic.introduction}" />
                </a>
             </li>
            </c:forEach>
         
           
        </ul>
</div>
<div class="jin2-right">
<ul>
<c:choose>
<c:when test="${line.teamBuy==1}">
<li>途乐行价：￥<c:out value="${fn:substring(line.price,0,fn:indexOf(line.price,'.'))}"/>元</li>
</c:when>
 <c:otherwise>
 <li>途乐行价：<strong>￥<c:out value="${fn:substring(line.price,0,fn:indexOf(line.price,'.'))}"/>元</strong></li>
 </c:otherwise>
</c:choose>
<li>出游工具：${line.vehicle}</li>
<c:if test="${line.teamBuy==1}">
<li>团购价：<strong><c:out value="${fn:substring(line.teamBuyPrice,0,fn:indexOf(line.teamBuyPrice,'.'))}"/>元</strong></li>
<li>优惠：<strong><c:out value="${fn:substring(line.price-line.teamBuyPrice,0,fn:indexOf(line.price-line.teamBuyPrice,'.'))}"/>元</strong></li>
</c:if>
<li>出游天数：${line.days}天</li>
<li>出游人数：已有<span>${count}</span>人出游此线路</li>
<li>提前报名：建议提前一天报名</li>

<li style="margin-top:18px">
 <a href="javascript:addCar('${line.lineID}')"><img src="<%=path%>/images/jgwc.jpg" width="122" height="42" /></a>
 <a href="javascript:accounting('${line.lineID}')"><img src="<%=path%>/images/js.jpg" width="123" height="43" style="margin-left:100px"/></a>
</li>
</ul>
</div>
</div>
<div class="tuijian">
<div class="tuijian-m">推荐理由</div>
<div class="tuijian-inf">
<ul>
<li>${line.reason}</li>
</ul>
</div>
<div class="tuijian-t">行程安排</div>
<div class="tuijian-inf">
<ul>
<li>${line.arrange}</li>
</ul>
</div>
</div>
<div class="jianjie">
<div class="jianjie-m">线路简介</div>
<div>${line.introduction}</div>
</div>
<div class="tuijian">
  <div class="tuijian-m">温馨提示</div>
  <div class="tuijian-inf">
    <ul>
      <li> 在出游时保管好个人的钱财，证件注意人身安全 </li>
      <li> 当地购物时请慎重考虑，把握好质量与价格，务必索要发票。 </li>
      <li> 出游通知最晚于出游前1天发送，若能提前确定，我们将会第一时间通知您。 </li>
      <li> 出游过程中，如产生退费情况，以退费项目旅行社折扣价为依据，均不以挂牌价为准。 </li>
      <li> 如遇国家政策性调整门票、交通价格等，按调整后的实际价格结算。 </li>
    </ul>
  </div>
</div>
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
