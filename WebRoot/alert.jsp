<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>提示页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
	body {
    font-size: 11px;
    font-family: 'Open Sans', sans-serif;
    color: #4A4A4A ;
    text-align: center; 
}

.box6{
    margin: 20px auto;
    width: 300px;
    min-height: 150px;
    padding: 10px;
    position:relative;
    background: -webkit-gradient(linear, 0% 20%, 0% 92%, from(#fff), to(#f3f3f3), color-stop(.1,#fff));
    border-top: 1px solid #ccc;
    border-right: 1px solid #ccc;
    border-left: 1px solid #ccc;
    -webkit-border-top-left-radius: 60px 5px;
    -webkit-border-top-right-radius: 60px 5px;
    -webkit-border-bottom-right-radius: 60px 60px ;
    -webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.3) ;
    
}

.box6:before{
    content:'';    
    width: 25px;
    height: 20px;
    position: absolute;
    bottom:0;
    right:0;
    -webkit-border-bottom-right-radius: 30px;
    -webkit-box-shadow: -2px -2px 5px rgba(0, 0, 0, 0.3);
    -webkit-transform:
    rotate(-20deg)
    skew(-40deg,-3deg)
    translate(-13px,-13px);
}

.box6:after{
    content: '';
    z-index: -10;
    width: 100px;
    height: 100px;
    position:absolute;
    bottom:0;
    right:0;
    background: rgba(0, 0, 0, 0.2);
    display: inline-block;
    -webkit-box-shadow: 20px 20px 8px rgba(0, 0, 0, 0.2);
    -webkit-transform: rotate(0deg)
                        translate(-45px,-20px)
                           skew(20deg);
}
.box6_corner_lf{
    width: 100px;
    height: 100px;
    top:0; left:0;
    position:absolute;
    z-index:-6;
    display: inline-block;
    -webkit-box-shadow: -10px -10px 10px rgba(0, 0, 0, 0.2);
    -webkit-transform: rotate(2deg)
                        translate(20px,25px)
                           skew(20deg);
}

.box6_corner_rt{
    content: '';
    width: 50px;
    height: 50px;
    top:0; right:0;
    position:absolute;
    display: inline-block;
    z-index:-6;
    -webkit-box-shadow: 10px -10px 8px rgba(0, 0, 0, 0.2);
    -webkit-transform: rotate(2deg)
                        translate(-14px,20px)
                           skew(-20deg);
}

.box6 img {
    width: 100%;
    margin-top: 15px;
}

p{ 
    margin-top: 15px;
    text-align: justify;
}

h1{
    font-size: 20px;
    font-weight: bold;
    margin-top: 5px; 
    text-shadow: 1px 1px 3px rgba(0,0,0,0.3);
}

a{
    text-decoration: none;
    color: #4A4A4A !important;
}

a:hover{
    text-decoration: underline;
    color: #6B6B6B !important ;
}
</style>	
  </head>
  
  <body>
    <div class="box6">
       <h1>途乐行系统温馨提示您</h1>   
       <img src="<%=path%>/images/timg.jpg">
        <p>
                      恭喜，订单交易成功，系统在<span id="time">5</span>秒后跳转至优旅网主页
       </p>
       <script>
           
          
              var span=document.getElementById("time");
              var count=5;
              var flag=window.setInterval(function(){
                  count--;
                  span.innerHTML=count;
                  if(count==1){
                    window.location.href="<%=path%>/LineManagerServlet?flag=_showLine";
                    window.clearTimeout(flag);
                  }
              
              }, 1000);
        </script>
       <br />
        <span>http://www.tlx.com</span>
       <div class="box6_corner_lf"></div>
       <div class="box6_corner_rt"></div>
</div>

  </body>
</html>
 