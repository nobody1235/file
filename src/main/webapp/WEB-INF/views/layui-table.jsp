<%--
  Created by IntelliJ IDEA.
  User: JK
  Date: 2020-3-9
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
	<title>表格的样式</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css">
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="srcAddress"/>

<div class="layui-form">
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>
		<tr>
			<th>ID</th>
			<th>昵称</th>
			<th>密码</th>
			<th>性别</th>
			<th>内容</th>
		</tr>
		</thead>
		<tbody id="content"></tbody>
	</table>
</div>
<script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
<script>
	window.onload=function(){
		console.log("进来成功");
		//Demo
		layui.use(['jquery'],  function(){
			$ = layui.jquery;
			var src = $("#srcAddress").val();
			console.log(src);
			$.ajax({
				url:src+'/tableServlet?method=staticTable',
				type:'post',
				data: {},
				success:function(data){
					if(data != null){
						console.log("---"+data);
						var html = "";
						for(var i in data){
							html += "<tr>";
							html += "<td>" + data[i].id + "</td>";
							html += "<td>" + data[i].userName + "</td>";
							html += "<td>" + data[i].userPwd + "</td>";
							html += "<td>" + data[i].userSex + "</td>";
							html += "<td>" + data[i].realName + "</td>";
							html += "</tr>";
						}
						$("#content").empty();
						$("#content").append(html);
					}
				},error:function (err) {
					console.log(err);
				}
			});
		});
	}
</script>
</body>
</html>