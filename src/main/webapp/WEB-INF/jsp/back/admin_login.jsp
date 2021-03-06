<%--
  Created by IntelliJ IDEA.
  User: JK
  Date: 2020-3-9
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
	<title>后台登录文档</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css">
</head>
<style>
	html,body{
		margin: 0 auto;
		width: 100%;
	}
	.login{
		width: 28%;
		margin-top: 12%;
		border-radius: 5%;
		line-height: 40px;
		padding: 2%;
		background-color: #FFF;
	}
</style>

<body style="background-image: url(${pageContext.request.contextPath}/images/banner.jpg);">
<div class="layui-main login">
	<h3 style="text-align: center">登录后台系统</h3>
	<input type="hidden" value="${pageContext.request.contextPath}" id="srcAddress"/>

	<form class="layui-form" action="" onsubmit="return false;">
		<div class="layui-form-item">
			<label class="layui-form-label">用户框</label>
			<div class="layui-input-block">
				<input type="text" name="account" required lay-verify="required" placeholder="请输入用户名" autocomplete="off"
				       class="layui-input" style="width: 71.2%;">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码框</label>
			<div class="layui-input-inline">
				<input type="password" name="adpassword" required lay-verify="required" placeholder="请输入密码"
				       autocomplete="off"
				       class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">辅助文字</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

</div>

<script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
<script>
	//Demo
	layui.use(['form','jquery'],  function(){
		var form = layui.form;
		$ = layui.jquery;
		var src = $("#srcAddress").val();
		console.log(src);
		//监听提交
		form.on('submit(formDemo)', function(data){
			$.ajax({
				url:src+'/servlet/back/admin_login',
				type:'post',
				data: data.field,
				success:function(data){
					console.log("登录后台成功!"+data.toString());
					if(data!="登录失败"){
						layer.msg("登录"+data.username+"账号");
						window.location.href = src + "/servlet/pback/mainPage";
						console.log(data);
					}else{
						layer.msg(data);
					}
				},error:function (err) {
					console.log(err);
				}
			});
			return false;//阻止表单跳转
		});
	});
</script>
</body>
</html>
