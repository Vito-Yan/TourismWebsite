<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
%>
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
	background: url("<%=path%>/admin/img/no.png") no-repeat 0px 0px;
	padding-left: 16px;
}

em.success {
	background: url("<%=path%>/admin/img/ok.png") no-repeat 0px 0px;
	padding-left: 16px;
}
</style>
<script src="<%=path%>/admin/js/jquery-1.3.1.js" type="text/javascript"></script>
<script src="<%=path%>/admin/js/jquery.validate.js"
	type="text/javascript"></script>

<script>
	$(document).ready(function() {


		$.validator.addMethod("decimal", function(value, element) {
			var decimal = /^(:?(:?\d+.\d+)|(:?\d+))$/;
			return this.optional(element) || (decimal.test(value));
		}, '价格输入有误');

		$.validator.addMethod("options", function(value, element) {
			return value != "sel";
		}, '请选择');
		//自定义评语内容验证方法
		$.validator.addMethod("suffix", function(value, element) {
			var reg = /(\\+)/g; //验证正则表达式
			var pfn = value.replace(reg, "#");
			var arrpfn = pfn.split("#");
			var fn = arrpfn[arrpfn.length - 1];
			var arrfn = fn.split(".");
			var name = arrfn[arrfn.length - 1];
			var suffixArray = [ "png", "gif", "jpg", "jpeg", "bmp" ];
			var flag = false;
			for (var i = 0; i < suffixArray.length; i++) {
				if (suffixArray[i] == name) {
					flag = true;
					return true;
				}
				if (flag) {
					flag = false;
					return true;
				}

			}
			return false;

		}, '文件后缀为只能是[jpg,jpeg,bmp,gif]');





		$("#commentForm").validate({
			rules : {
				lineName : {
					required : true,
					minlength : 4,
					maxlength : 12
				},
				price : {
					required : true,
					decimal : $("#price").val(),
					maxlength : 7
				},
				vehicle : {
					options : $("#vehicle").val()
				},
				days : {
					options : $("#days").val()
				},
				lineType : {
					options : $("#lineType").val()
				},
				pic1 : {
					required : true,
					suffix : $("#pic1").val()
				},
				pic2 : {
					required : true,
					suffix : $("#pic2").val()
				},
				pic3 : {
					required : true,
					suffix : $("#pic3").val()
				},
				pic4 : {
					required : true,
					suffix : $("#pic4").val()
				},
				picIntroduction : {
					required : true,
					minlength : 2,
					maxlength : 12
				}
			},

			messages : {
				lineName : {
					required : '请输入线路名称',
					minlength : '请至少输入4个字符',
					maxlength : '请最多输入12个字符'
				},
				price : {
					required : '价格不能为空',
					maxlength : '长度不能超过7位'
				},
				pic1 : {
					required : '没有选择上传的文件'
				},
				pic2 : {
					required : '没有选择上传的文件'
				},
				pic3 : {
					required : '没有选择上传的文件'
				},
				pic4 : {
					required : '没有选择上传的文件'
				},
				picIntroduction : {
					required : '请输入图片说明',
					minlength : '长度最少为2个字符',
					maxlength : '长度不能超过12个字符'
				}
			},

			errorElement : "em", //用来创建错误提示信息标签
			success : function(label) { //验证成功后的执行的回调函数
				//label指向上面那个错误提示信息标签em
				label.text(" ") //清空错误提示消息
					.addClass("success"); //加上自定义的success类
			}
		});

	});
	$(function() {
		var sel = $('#days option:selected');
		var options = $('#days option');

		options.each(function(i, n) {
			if ($(n).val() != "sel") {
				if (sel.val() == $(n).val()) {

					$("#days option[index=" + (i + 1) + "]").remove();
					return false;
				}
			}

		})


	});

	$(function() {
		var tool = $('#vehicle option:selected');
		var toolOptions = $('#vehicle option');
		toolOptions.each(function(i, n) {
			if ($(n).val() != "sel") {
				if (tool.val() == $(n).val()) {

					$("#vehicle option[index=" + (i + 1) + "]").remove();
					return false;
				}
			}

		})


	});

	$(function() {
		var lineType = $('#lineType option:selected');
		var lineTypeOptions = $('#vehicle option');
		lineTypeOptions.each(function(i, n) {
			if ($(n).val() != "sel") {
				if (lineType.val() == $(n).val()) {

					$("#lineType option[index=" + (i + 1) + "]").remove();
					return false;
				}
			}

		})


	});
</script>

<body>
	<form action="<%=path%>/AdminManagerServlet?flag=_addLine"
		enctype="multipart/form-data" method="post" id="commentForm">
		<div class="pageTop">
			<div class="page">
				<img src="<%=path%>/admin/img/coin02.png" /><span><a
					href="#">首页</a>&nbsp;-&nbsp;<a href="#">旅游路线管理</a>&nbsp;-</span>&nbsp;添加路线
			</div>
		</div>
		<div style="margin-left:80px">
			<table width="800px" align="center" height="144" border="0"
				cellpadding="0" cellspacing="0">
				<tr>
					<td width="40%"><span class="_text">线路名称&nbsp;<input
							type="text" name="lineName" value="${line.lineName}"></span></td>
					<td width="40%" class="_text"><span>线路价格&nbsp;<input
							type="text" name="price" id="price" value="${line.price}"></span></td>
					<td><input type="submit" onclick="test()" value="添加"></td>
				</tr>
				<tr>
					<td width="50%"><span class="_text">出游天数 <select
							name="days" id="days">
								<option selected="selected" value="sel">==请选择出游天数==</option>
								<c:if test="${line.days==1}">
									<option value="1" selected="select" />1天</option>
								</c:if>
								<option value="1" />1天
								</option>
								<c:if test="${line.days==2}">
									<option value="2" selected="select" />2天</option>
								</c:if>
								<option value="2" />2天
								</option>
								<c:if test="${line.days==3}">
									<option value="3" selected="select" />3天</option>
								</c:if>
								<option value="3" />3天
								</option>
								<c:if test="${line.days==4}">
									<option value="4" selected="select" />4天</option>
								</c:if>
								<option value="4" />4天
								</option>
								<c:if test="${line.days==5}">
									<option value="5" selected="select" />5天</option>
								</c:if>
								<option value="5" />5天
								</option>
								<c:if test="${line.days==6}">
									<option value="6" selected="select" />6天</option>
								</c:if>
								<option value="6" />6天
								</option>
								<c:if test="${line.days==7}">
									<option value="7" selected="select" />7天</option>
								</c:if>
								<option value="7" />7天
								</option>
								<c:if test="${line.days==8}">
									<option value="8" selected="select" />8天</option>
								</c:if>
								<option value="8" />8天
								</option>
								<c:if test="${line.days==9}">
									<option value="9" selected="select" />9天</option>
								</c:if>
								<option value="9" />9天
								</option>
								<c:if test="${line.days==10}">
									<option value="10" selected="select" />10天</option>
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
									<option value="飞机" selected="select" />飞机</option>
								</c:if>
								<option value="飞机">飞机</option>

								<c:if test="${line.vehicle=='火车'}">
									<option value="火车" selected="select" />火车</option>
								</c:if>
								<option value="火车">火车</option>

								<c:if test="${line.vehicle=='汽车'}">
									<option value="汽车" selected="select" />汽车</option>
								</c:if>
								<option value="汽车">汽车</option>

								<c:if test="${line.vehicle=='轮船'}">
									<option value="轮船" selected="select" />轮船</option>
								</c:if>
								<option value="轮船">轮船</option>


						</select></span></td>
					<td width="50%"><span class="_text">路线类型 <select
							name="lineType" id="lineType">
								<option selected="selected" value="sel">==请选择路线类型==</option>
								<c:forEach var="lineType" items="${lineTypeList}">
									<c:if test="${line.lineTypeID==lineType.lineTypeID}">
										<option value="${lineType.lineTypeID}" selected="select" />${lineType.typeName}</option>
									</c:if>
									<option value="${lineType.lineTypeID}">${lineType.typeName}</option>
								</c:forEach>

						</select>
					</span></td>


				</tr>
			</table>
			<div>
				<script charset="utf-8" src="<%=path%>/admin/js/kindeditor-min.js"></script>
				<script charset="utf-8" src="<%=path%>/admin/js/zh_CN.js"></script>
				<script charset="gbk">
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
						}, function(pluginName, pluginData) {
							var lang = {};
							lang[pluginName] = pluginData.name;
							KindEditor.lang(lang);
							KindEditor.plugin(pluginName, function(K) {
								var self = this;
								self.clickToolbar(pluginName, function() {
									var menu = self.createMenu({
										name : pluginName,
										width : pluginData.width || 100
									});
									K.each(pluginData.method, function(i, v) {
										menu.addItem({
											title : v,
											checked : false,
											iconClass : pluginName + '-' + i,
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
								'bold', 'italic', 'underline', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'plug-align', 'plug-order', 'plug-indent', 'link'
							]
						});
						K.create('#arrange', {
							themeType : 'qq',
							items : [
								'bold', 'italic', 'underline', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'plug-align', 'plug-order', 'plug-indent', 'link'
							]
						});
				
						K.create('#introduction', {
							themeType : 'qq',
							items : [
								'bold', 'italic', 'underline', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'plug-align', 'plug-order', 'plug-indent', 'link'
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
							class="_text">线路简介</span></td>
					</tr>
					<tr>
						<td><textarea id="reason" name="reason" cols="40">${line.reason}</textarea></td>
						<td><textarea id="arrange" name="arrange" cols="40">${line.arrange}</textarea></td>
						<td><textarea id="introduction" name="introduction" cols="40">${line.introduction}</textarea></td>

					</tr>

				</table>

			</div>
			<div style="margin-top:10px;float:left">
				<p>
					<span class="_text" style="margin-top:3px">选择上传线路图片</span> <span
						class="_text" style="margin-top:3px">(说明:用户查看具体路线时有4张图片作为简介部分)</span>
				</p>
				<p>
					<span class="_text" style="margin-top:3px"> 图片1&nbsp;</span><span
						class="_text"><input type="file" id="pic1" name="pic1"></span>
					<span class="_text">图片介绍 <input type="text"
						name="picIntroduction" id="i1"
						value="${pList.get(0).introduction}"></span>
				</p>
				<p>
					<span class="_text" style="margin-top:3px"> 图片2&nbsp;</span><span
						class="_text"><input type="file" id="pic2" name="pic2"></span>
					<span class="_text">图片介绍 <input type="text"
						name="picIntroduction" id="i2"
						value="${pList.get(1).introduction}"></span>
				</p>
				<p>
					<span class="_text" style="margin-top:3px"> 图片3&nbsp;</span><span
						class="_text"><input type="file" id="pic3" name="pic3"></span>
					<span class="_text">图片介绍 <input type="text"
						name="picIntroduction" id="i3"
						value="${pList.get(2).introduction}"></span>
				</p>
				<p>
					<span class="_text" style="margin-top:3px"> 图片4&nbsp;</span><span
						class="_text"><input type="file" id="pic4" name="pic4"></span>
					<span class="_text">图片介绍 <input type="text"
						name="picIntroduction" id="i4"
						value="${pList.get(3).introduction}"></span>
				</p>
			</div>
		</div>
	</form>
</body>
