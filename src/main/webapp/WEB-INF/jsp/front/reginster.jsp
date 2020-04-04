<%--
  Created by IntelliJ IDEA.
  User: JK
  Date: 2020-3-9
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>登录文档</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css">
</head>
<style>
	html, body {
		margin: 0 auto;
		width: 100%;
	}

	.login {
		width: 34%;
		margin-top: 2%;
		border-radius: 5%;
		line-height: 30px;
		padding: 2%;
		background-color: #FFF;
	}
</style>

<body style="background-image: url(${pageContext.request.contextPath}/images/banner.jpg);">
<div class="layui-main login">
	<h3 style="text-align: center">注册用户</h3>
	<input type="hidden" value="${pageContext.request.contextPath}" id="srcAddress"/>

	<form class="layui-form" action="" onsubmit="return false;">
		<div class="layui-form-item">
			<label class="layui-form-label">用户框</label>
			<div class="layui-input-inline">
				<input type="text" name="username" id="username" onblur="checkusername()" required lay-verify="required"
				       placeholder="请输入标题" autocomplete="off"
				       class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux" id="name"></div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码框</label>
			<div class="layui-input-inline">
				<input type="password" name="password" id="password" onblur="checkpassword()" required
				       lay-verify="required" placeholder="请输入密码"
				       autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux" id="checkpassword"></div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">重复输入</label>
			<div class="layui-input-inline">
				<input type="password" name="repassword" id="repassword" onblur="checkrepassword()" required
				       lay-verify="required" placeholder="请输入密码"
				       autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux" id="checkrepassword"></div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单选框</label>
			<div class="layui-input-inline">
				<input type="radio" name="usersex" value="男" title="男" checked>
				<input type="radio" name="usersex" value="女" title="女">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学历</label>
			<div class="layui-input-inline">
				<select name="schoolmate" id="schoolmate" required lay-verify="required"
				        style="line-height: 30px;width: 100px">
					<option value="专科">专科</option>
					<option value="本科">本科</option>
					<option value="研究生">研究生</option>
				</select>

			</div>
			<div class="layui-form-mid layui-word-aux" id="school"></div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">职业</label>
			<div class="layui-input-inline">
				<select name="position" id="position" required lay-verify="required"
				        style="line-height: 30px;width: 100px">
					<option value="软件工程师">软件工程师</option>
					<option value="java工程师">java工程师</option>
					<option value="c++工程师">c++工程师</option>
				</select>
			</div>
			<div class="layui-form-mid layui-word-aux" id="posit"></div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">手机号</label>
			<div class="layui-input-inline">
				<input type="text" name="cellphone" id="cellphone" onblur="checkcellphone()" required
				       lay-verify="required" placeholder="请输入手机号" autocomplete="off"
				       class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux" id="divcel"></div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">Email</label>
			<div class="layui-input-inline">
				<input type="text" name="usermail" id="usermail" onblur="checkusermail()" required lay-verify="required"
				       placeholder="请输入Email" autocomplete="off"
				       class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux" id="divemail"></div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">提交注册</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
<script>
	// 注册
	layui.use(['form', 'jquery'], function () {
		var form = layui.form;
		$ = layui.jquery;
		var src = $("#srcAddress").val();
		console.log(src);

		//监听提交
		form.on('submit(formDemo)', function (data) {
			if (checkusername() && checkpassword() && checkrepassword() && checkcellphone() && checkusermail()) {
				//注册的信息显示-提交
				console.log("进入该方法里面");
				if (confirm('确定要注册该用户吗？')) {
					$.ajax({
						url: src + '/servlet/reginster',
						type: 'post',
						data: data.field,//表单提交的方式
						success: function (data) {
							console.log("注册成功!" + data.toString());
							if (data != "注册失败") {
								layer.msg(data);
								window.location.href = src + "/servlet/pfront/login";
							} else {
								layer.msg(data);
							}
						}, error: function (err) {
							console.log(err);
						}
					});
					return true;
				}
				return false;//阻止表单跳转
			}
			else {
				layer.msg("填写正确格式信息！");
				return false;
			}
		});
	});

	
	//1、用户名的正则表达式的判断
	function checkusername() {
		layui.use(['jquery'], function () {
			$ = layui.jquery;
			console.log("按钮点击");
			var div = document.getElementById("name");
			div.innerHTML = "";
			var username = $("#username").val();
			//2.定义正则表达式
			var regName = /^[\u4e00-\u9fa5]{1,5}$|^[\dA-Za-z_]{3,12}$/;
			//判断密码是不是空的
			if (username == "") {
				div.innerHTML = "姓名不能为空！";
				$("#username").focus();
				return false;
			}
			//3.判断值是否符合正则的规则
			if (!regName.test(username)) {
				div.innerHTML = "不能大于(1-5汉字/3-12字母)";
				$("#username").select();
				return false;
			}
			//判断姓名是否为空
			var srcAddress = $("#srcAddress").val();
			$.ajax({
				url: srcAddress + "/servlet/selectName",
				async: true,
				type: "post",
				data: {"username": username},
				dataType: "text",
				success: function (msg) {
					if (msg === "success") {
						layer.msg("账号已使用！");
						$("#username").focus();
					} else {
						div.innerHTML = "该名可以使用";
					}
				},
				error: function () {
					alert("网络繁忙");
				}
			});
		});
		console.log("输出的值是:用户名");
		return true;
	}

	//2、密码的正则表达式的判断
	function checkpassword() {
		layui.use(['jquery'], function () {
			$ = layui.jquery;
			var div = document.getElementById("checkpassword");
			div.innerHTML = "";
			//1.获取密码的值
			var password = $("#password").val();
			//2.定义正则表达式
			var regPwd = /^[a-zA-Z0-9]{4,12}$/;
			//判断密码是不是空的
			if (password == "") {
				div.innerHTML = "密码不能为空！";
				$("#password").focus();
				return false;
			}
			//3.判断值是否符合正则的规则
			if (!regPwd.test(password)) {
				div.innerHTML = "请输入密码长度4-12位";
				$("#password").select();
				return false;
			}
		});
		console.log("输出的值是:密码");
		return true;
	}

	//3、两次输入密码是否一致-----密码之间的验证
	function checkrepassword() {
		layui.use(['jquery'], function () {
			$ = layui.jquery;
			var div = document.getElementById("checkrepassword");
			div.innerHTML = "";
			var password = $("#password").val();
			var repass = $("#repassword").val();
			if (repass == "") {
				div.innerHTML = "密码不能为空！";
				$("#repassword").focus();
				return false;
			}
			if (password != repass) {
				div.innerHTML = "两次密码不一致";
				$("#repassword").focus();
				return false;
			}
		});
		console.log("输出的值是:重置密码");
		return true;
	}

	//4、手机号验证
	function checkcellphone() {
		layui.use(['jquery'], function () {
			$ = layui.jquery;
			var div = document.getElementById("divcel");
			div.innerHTML = "";
			//1.获取手机号的值
			var cellPhone = $("#cellphone").val();
			//2、判断手机号是不是符合
			var myCell = /^[1][3,4,5,7,8][0-9]{9}$/;
			//3.判断值是不是空的规则
			if (cellPhone == "") {
				div.innerHTML = "手机号不能为空！";
				$("#cellphone").focus();
				return false;
			}
			//4.判断值是否符合正则的规则
			if (!myCell.test(cellPhone)) {
				div.innerHTML = "请输入正确的格式";
				$("#cellphone").focus();
				return false;
			}
		});
		console.log("输出的值是:手机号");
		return true;
	}

	//5、邮箱验证
	function checkusermail() {
		layui.use(['jquery'], function () {
			$ = layui.jquery;
			var email = $("#usermail").val();
			var email_prompt = document.getElementById("divemail");
			email_prompt.innerHTML = "";
			//^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$
			var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			//如果不正确的话就进行对应的错误验证
			if (!reg.test(email)) {
				email_prompt.innerHTML = "Email格式不正确，例如web@sohu.com";
				return false;
			}
		});
		console.log("输出的值是:邮箱");
		return true;
	}
</script>
</body>
</html>
