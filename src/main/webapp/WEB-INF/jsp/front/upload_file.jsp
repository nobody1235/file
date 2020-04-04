<%--
  Created by IntelliJ IDEA.
  User: JK
  Date: 2020-3-10
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<title>文件上传</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" media="all">
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="srcAddress"/>

<div class="layui-form-item">
	<label class="layui-form-label">新增的名称:</label>
	<div class="layui-inline">
		<input type="text" name="newName" id="newName" placeholder="请输入" autocomplete="off" class="layui-input" style="width: 300px">
	</div>
	<div class="layui-form-mid layui-word-aux" id="divname"></div>
</div>

<div class="layui-form-item layui-form-text">
	<label class="layui-form-label">请填写描述:</label>
	<div class="layui-inline">
		<textarea placeholder="请输入内容" class="layui-textarea" name="content" id="content"></textarea>
	</div>
	<div class="layui-form-mid layui-word-aux" id="divcontent"></div>
</div>

<div class="layui-upload" style="margin-left: 40px">
	<button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
</div>

<div class="layui-form-item layui-form-text">
	<label class="layui-form-label">下载积分：</label>
	<div class="layui-inline">
		<input type="text" placeholder="请输入积分" oninput="value=value.replace(/[^\d]/g,'')" class="layui-textarea" name="downloadscore" id="downloadscore"/>
	</div>
	<div class="layui-form-mid layui-word-aux" id="divscore"></div>
</div>

<br/>
<div class="demoTable" style="margin-left: 40px">
	<div style="padding-bottom: 10px;">
		<div class="layui-upload">
			<button type="button" class="layui-btn" id="test9">保存</button>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
<script>
	layui.use(['upload','jquery'], function () {
		var upload = layui.upload;
		$=layui.jquery;
		var src = $("#srcAddress").val();
		console.log(src);
		console.log($("#newName").val()+"="+$("#content").val()+"="+$("#downloadscore").val());
		//执行实例
		var uploadInst = upload.render({
			elem: '#test8' //绑定元素--上传的内容
			, url: src+'/servlet/front/fileact' //上传接口
			,auto: false //选择文件后不自动上传
			,accept: 'file'//上传文件的格式
			,bindAction: '#test9' //指向一个按钮触发上传
			,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致。
				this.data={
					documentname:$("#newName").val(),
				documentcontent:$("#content").val(),
				downloadscore:$("#downloadscore").val()
				}//携带额外的数
			}
			,done: function (res) {
				//上传完毕回调
				layer.msg("上传成功!");
				$("#newName").val("");
				$("#content").val("");
				$("#downloadscore").val("");
			}
			, error: function () {
				//请求异常回调
				layer.msg("上传失败!");
			}
		});

		//文档名称
		$("#newName").on('blur',function () {
			console.log("进去到对应的文档名称设置！");
			//1.获取手机号的值
			var age = $("#newName").val();
			//3.判断值是不是空的规则
			if (age == "") {
				// div.innerHTML = "不能为空！";
				layer.msg("名称不能为空");
				$("#newName").focus();
				return false;
			}
			return true;
		});

		//文档内容
		$("#content").on('blur',function () {
			console.log("进去到对应的下载积分里面设置！");
			//获取值
			var age = $("#content").val();
			//判断值是不是空的规则
			if (age == "") {
				layer.msg("内容不能为空");
				$("#content").focus();
				return false;
			}
			return true;
		});

		//文件内容
		$("#test8").on('blur',function () {
			console.log("进去到对应的文件内容！");
			//获取值
			var age = $("#test8").val();
			//判断值是不是空的规则
			if (age == "") {
				layer.msg("文件不能为空");
				$("#test8").focus();
				return false;
			}
			return true;
		});

		//下载积分
		$("#downloadscore").on('blur',function () {
			console.log("进去到对应的下载积分里面设置！");
			var div = document.getElementById("divscore");
			div.innerHTML = "";
			//1.获取手机号的值
			var num = $("#downloadscore").val();
			//2、判断手机号是不是符合
			var mynum = /^[0-9]{1,5}$/;
			//3.判断值是不是空的规则
			if (num == "") {
				layer.msg("不能为空！");
				$("#downloadscore").focus();
				return false;
			}
			//4.判断值是否符合正则的规则
			if (!mynum.test(num)) {
				layer.msg("非法输入，请输入1-5位数字");
				$("#downloadscore").focus();
				return false;
			}
			return true;
		});
	});

	// //服务价格的判断使用
	// function checkMoney() {
	//
	// }
</script>
</body>
</html>
