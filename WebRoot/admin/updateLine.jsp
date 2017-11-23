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

#hides {
	display: none;
}

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
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 14px;
}

span {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #666;
}

em {
	font-weight: bold;
	padding-right: 1em;
	vertical-align: top;
}

em.error {
	background: url("<%=path%>/admin/img/no.png") no-repeat
		0px 0px;
	padding-left: 16px;
}

em.success {
	background: url("<%=path%>/admin/img/ok.png") no-repeat
		0px 0px;
	padding-left: 16px;
}
</style>
<script src="<%=path%>/admin/js/jquery-1.3.1.js" type="text/javascript"></script>
<script src="<%=path%>/admin/js/jquery.validate.js"
	type="text/javascript"></script>
<script src="<%=path%>/admin/js/date.js" type="text/javascript"></script>

<script>
 $(document).ready(function(){
       
 	
	  $.validator.addMethod("decimal", function (value, element) {    
      var decimal = /^(:?(:?\d+.\d+)|(:?\d+))$/;  
      return this.optional(element) || (decimal.test(value));  
      },'价格输入有误');  
	  
      $.validator.addMethod("options", function (value, element) {    
       return  value!="sel";
      },'请选择'); 
      //比较团购价格和路线价格的大小
      $.validator.addMethod("comparePrice", function (value, element) {    
          var price=parseInt($("#price").val());//得到线路价格
          var tp=parseInt(value);
          return   tp>price?false:true;
       
      },'团购价需小于线路价'); 
      
      
      
      $.validator.addMethod("suffix", function (value, element) {    
            var reg = /(\\+)/g;
	        var pfn =value.replace(reg, "#");
	        var arrpfn = pfn.split("#");
	        var fn = arrpfn[arrpfn.length - 1];
	        var arrfn = fn.split(".");
	        var name=arrfn[arrfn.length - 1];
	        var  suffixArray=["png","gif","jpg","jpeg","bmp",""];
	        var flag=false;
	       for(var i=0;i<suffixArray.length;i++){
	            if(suffixArray[i]==name){
	               flag=true;
	               return true;
	            }
	            if(flag){
	                 flag=false;
	                 return true;
	            }
	       
	       }
	       return false;
            
      },'文件后缀为只能是[jpg,jpeg,bmp,gif]');
      
      
      
     
 
	$("#commentForm").validate({
		rules: {
			lineName: {
				required: true,
				minlength: 4,
				maxlength:12
			},
			price:{
			    required: true,
			    decimal:$("#price").val(),
			    maxlength:7
			    
			},
			vehicle:{
			   options:$("#vehicle").val()
			},
			days:{
			   options:$("#days").val()
			},
			lineType:{
			   options:$("#lineType").val()
			},
			pic1:{
			    
		
			    suffix:$("#pic1").val()
			},
			pic2:{
			    
			  
			    suffix:$("#pic2").val()
			},
			pic3:{
			    
			
			    suffix:$("#pic3").val()
			},
			pic4:{
			    
			
			    suffix:$("#pic4").val()
			},
			picIntroduction:{
			   required: true,
			   minlength: 2,
			   maxlength:12
			},
			teamprice:{
			  required: true,
			  maxlength:7,
			  decimal:$("#teamprice").val(),
			  comparePrice:$("#teamprice").val()
			}
			
		},
		
		messages: {
			lineName: {
				required: '请输入线路名称',
				minlength: '不少于入4个字符',
				maxlength: '请最多输入12个字符'
			},
			price: {
				required: '价格不能为空',
				maxlength:'长度不能超过7位'
			},
			
			picIntroduction:{
			   required: '请输入图片说明',
			   minlength: '长度最少为2个字符',
			   maxlength:'长度不能超过12个字符'
			},
			teamprice:{
			   required:'请输入团购价格',
			   maxlength:'长度不能超过7位'
			},
			beginTime:{
			   required:'请输入时间'
			},
			endTime:{
			  required:'请输入时间'
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
  $(function(){
         var  sel= $('#days option:selected');
          var options=$('#days option');
         
          options.each(function(i,n){
               if($(n).val()!="sel"){
                   if(sel.val()==$(n).val()){
           
                   $("#days option[index="+(i+1)+"]").remove();     
                     return false;
                   }
               }
          
          })
         
  
  });
  
   $(function(){
         var  tool= $('#vehicle option:selected');
          var toolOptions=$('#vehicle option');
          toolOptions.each(function(i,n){
               if($(n).val()!="sel"){
                   if(tool.val()==$(n).val()){
           
                   $("#vehicle option[index="+(i+1)+"]").remove();     
                     return false;
                   }
               }
          
          })
         
  
  });
  
   $(function(){
         var  lineType= $('#lineType option:selected');
          var lineTypeOptions=$('#vehicle option');
          lineTypeOptions.each(function(i,n){
               if($(n).val()!="sel"){
                   if(lineType.val()==$(n).val()){
           
                   $("#lineType option[index="+(i+1)+"]").remove();     
                     return false;
                   }
               }
          
          })

  });
  function sub(){
       var  hVal1=$("#pic1").attr("cont")+"-"+getName($("#pic1").val());
       var  hVal2=$("#pic2").attr("cont")+"-"+getName($("#pic2").val());
       var  hVal3=$("#pic3").attr("cont")+"-"+getName($("#pic3").val());
       var  hVal4=$("#pic4").attr("cont")+"-"+getName($("#pic4").val());
 

       var  td=$("#td");
       td.append("<input type='hidden' name='hid' value="+hVal1+">");
       td.append("<input type='hidden' name='hid' value="+hVal2+">");
       td.append("<input type='hidden' name='hid' value="+hVal3+">");
       td.append("<input type='hidden' name='hid' value="+hVal4+">");
      
  }

  function getName(value){
            var reg = /(\\+)/g;
	        var pfn =value.replace(reg, "#");
	        var arrpfn = pfn.split("#");
	        var fn = arrpfn[arrpfn.length - 1];
	        return fn;
	        
  
  }

 </script>
<body>
	<form
		action="<%=path%>/AdminManagerServlet?flag=_update&lineID=${line.lineID}"
		enctype="multipart/form-data" method="post" id="commentForm">
		<div class="pageTop">
			<div class="page">
				<img src="<%=path%>/admin/img/coin02.png" /><span><a
					href="#">首页</a>&nbsp;-&nbsp;<a href="#">设置信息</a>&nbsp;-</span>&nbsp;设置线路信息
			</div>
		</div>
		<div style="margin-left:20px">
		<table width="800px" align="center" height="144" border="0"
			cellpadding="0" cellspacing="0">
			<tr>
				<td width="50%"><span class="_text">线路名称&nbsp;<input
						type="text" name="lineName" value="${line.lineName}"></span></td>
				<td width="50%" class="_text"><span>线路价格&nbsp;<input
						type="text" name="price" id="price" value="${line.price}"></span></td>
				<td><input type="submit" onclick="sub()" " value="修改线路信息">
				</td>
			</tr>
			<tr>
				<td width="50%"><span class="_text">出游天数 <select
						name="days" id="days">
							<option selected="selected" value="sel">==请选择出游天数==</option>
							<c:if test="${line.days==1}">
								<option value="1" selected="selected" />1天</option>
							</c:if>
							<option value="1" />1天
							</option>
							<c:if test="${line.days==2}">
								<option value="2" selected="selected" />2天</option>
							</c:if>
							<option value="2" />2天
							</option>
							<c:if test="${line.days==3}">
								<option value="3" selected="selected" />3天</option>
							</c:if>
							<option value="3" />3天
							</option>
							<c:if test="${line.days==4}">
								<option value="4" selected="selected" />4天</option>
							</c:if>
							<option value="4" />4天
							</option>
							<c:if test="${line.days==5}">
								<option value="5" selected="selected" />5天</option>
							</c:if>
							<option value="5" />5天
							</option>
							<c:if test="${line.days==6}">
								<option value="6" selected="selected" />6天</option>
							</c:if>
							<option value="6" />6天
							</option>
							<c:if test="${line.days==7}">
								<option value="7" selected="selected" />7天</option>
							</c:if>
							<option value="7" />7天
							</option>
							<c:if test="${line.days==8}">
								<option value="8" selected="selected" />8天</option>
							</c:if>
							<option value="8" />8天
							</option>
							<c:if test="${line.days==9}">
								<option value="9" selected="selected" />9天</option>
							</c:if>
							<option value="9" />9天
							</option>
							<c:if test="${line.days==10}">
								<option value="10" selected="selected" />10天</option>
							</c:if>
							<option value="10" />10天
							</option>

					</select></span></td>

			</tr>
			<tr>
				<td width="50%"><span class="_text">交通工具 <select
						name="vehicle" id="vehicle">
							<option selected="selected" value="sel">==请选择交通工具==</option>
							<c:if test="${line.vehicle=='飞机'}">
								<option value="飞机" selected="selected" />飞机</option>
							</c:if>
							<option value="飞机">飞机</option>

							<c:if test="${line.vehicle=='火车'}">
								<option value="火车" selected="selected" />火车</option>
							</c:if>
							<option value="火车">火车</option>

							<c:if test="${line.vehicle=='汽车'}">
								<option value="汽车" selected="selected" />汽车</option>
							</c:if>
							<option value="汽车">汽车</option>

							<c:if test="${line.vehicle=='轮船'}">
								<option value="轮船" selected="selected" />轮船</option>
							</c:if>
							<option value="轮船">轮船</option>


					</select></span></td>
				<td width="50%"><span class="_text">路线类型 <select
						name="lineType" id="lineType">
							<option selected="selected" value="sel">==请选择路线类型==</option>
							<c:forEach var="lineType" items="${lineTypeList}">
								<c:if test="${line.lineTypeID==lineType.lineTypeID}">
									<option value="${lineType.lineTypeID}" selected="selected" />${lineType.typeName}</option>
								</c:if>
								<option value="${lineType.lineTypeID}">${lineType.typeName}</option>
							</c:forEach>

					</select>
				</span></td>


			</tr>
		</table>
		<div>
			<script charset="utf-8" src="<%=path%>/admin/js/kindeditor-min.js"></script>
			<script charset="utf-8" src="<%=path%>/admin/js/lang/zh_CN.js"></script>
			<script>
			KindEditor.ready(function(K) {
				K.each({
					'plug-align' : {
						name : '对齐方式',
						method : {
							'justifyleft' : '左对齐',
							'justifycenter' : '居中对齐',
							'justifyright' : '右对齐'
						}
					},
					'plug-order' : {
						name : '编号',
						method : {
							'insertorderedlist' : '数字编号',
							'insertunorderedlist' : '项目编号'
						}
					},
					'plug-indent' : {
						name : '缩进',
						method : {
							'indent' : '向右缩进',
							'outdent' : '向左缩进'
						}
					}
				},function( pluginName, pluginData ){
					var lang = {};
					lang[pluginName] = pluginData.name;
					KindEditor.lang( lang );
					KindEditor.plugin( pluginName, function(K) {
						var self = this;
						self.clickToolbar( pluginName, function() {
							var menu = self.createMenu({
									name : pluginName,
									width : pluginData.width || 100
								});
							K.each( pluginData.method, function( i, v ){
								menu.addItem({
									title : v,
									checked : false,
									iconClass : pluginName+'-'+i,
									click : function() {
										self.exec(i).hideMenu();
									}
								});
							})
						});
					});
				});
				K.create('#reason', {
					themeType : 'qq',
					items : [
						'bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','plug-order','plug-indent','link'
					]
				});
				K.create('#arrange', {
					themeType : 'qq',
					items : [
						'bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','plug-order','plug-indent','link'
					]
				});
				
				K.create('#introduction', {
					themeType : 'qq',
					items : [
						'bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','plug-order','plug-indent','link'
					]
				});
			});
		</script>

			<table width="100%">
				<tr>
					<td align="center" width="33%" align="center"><span
						class="_text">推荐理由</span></td>
					<td align="center" width="34%" align="center"><span
						class="_text">行程安排</span></td>
					<td align="center" width="33%" align="center"><span
						class="_text">路线简介</span></td>
				</tr>
				<tr>
					<td><textarea id="reason" name="reason" cols="40">${line.reason}</textarea></td>
					<td><textarea id="arrange" name="arrange" cols="40">${line.arrange}</textarea></td>
					<td><textarea id="introduction" name="introduction" cols="40">${line.introduction}</textarea></td>
				</tr>
			</table>

			<table>
				<tr>
					<td><span class="_text">简介图片</span></td>
				</tr>
				<tr>
					<c:forEach var="pic" items="${picList}">
						<td><img width="250px" height="240px"
							src="<%=path%>/upload/${line.lineID}/${pic.name}"></td>
					</c:forEach>


				</tr>

				<tr>
					<c:forEach var="pic" items="${picList}" varStatus="num">
						<td align="center"><span class="_text">图片介绍: <input
								style="width:190px;" type="text" id="i${num.count}"
								name="picIntroduction" value="${pic.introduction}">
						</span></td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="pic" items="${picList}" varStatus="num">
						<td align="center"><span class="_text"> <input
								type="file" id="pic${num.count}" name="pic${num.count}"
								value="${pic.pictureID}" cont="${pic.pictureID}"
								explain="${pic.introduction}">
						</span></td>

					</c:forEach>
				</tr>
			</table>
		</div>
	</form>
</body>
