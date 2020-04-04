<%--
  Created by IntelliJ IDEA.
  User: JK
  Date: 2020-3-10
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
	<title>文档审核</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" media="all">
</head>
<style>
	.demoTable {
		line-height: 50px;
		margin: 0 70px;
	}

	.selects {
		margin: 0 30px;
	}
</style>
<body>

<!-- 增加搜索条件 -->
<div class="demoTable">
	文档标题：
	<div class="layui-inline selects">

		<input class="layui-input" name="documentname" id="documentTitle" autocomplete="off">
	</div>
	上传人：
	<div class="layui-inline selects">

		<input class="layui-input" name="uploadpeople" id="uploadPeople" autocomplete="off">
	</div>
	文档类型：
	<div class="layui-inline selects">

		<select name="documenttype" id="documenttype" lay-verify="documenttype" style="line-height: 30px;width: 100px">
			<option value="暂无" selected>--请选择--</option>
			<option value="ppt">ppt</option>
			<option value="doc">doc</option>
			<option value="jpg">jpg</option>
		</select>
	</div>
	<br/>
	上传时间：
	<div class="layui-inline selects">
		<input type="date" class="layui-input" name="beginTime" id="beginTime" autocomplete="off">
	</div>
	至
	<div class="layui-inline selects">
		<input type="date" class="layui-input" name="overTime" id="overTime" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload">查询</button>

</div>


<input type="hidden" value="${pageContext.request.contextPath}" id="srcAddress"/>

<table id="demo" lay-filter="test"></table>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-primary layui-btn-xs download" lay-event="down">下载</a>
	<a class="layui-btn edit layui-btn-xs" lay-event="approved">审核通过</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="noapproved">审核不通过</a>
</script>

<script src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
<script>

	layui.use(['table', 'layer', 'form'], function () {
		var table = layui.table;
		var layer = layui.layer;
		var form = layui.form;
		$ = layui.jquery;
		var src = $("#srcAddress").val();
		//第一个实例
		table.render({
			elem: '#demo'
			, height: 312
			, limit: 5//设置的一页要有几条的记录
			, limits: [5, 10]//设置的是对应的是有几个内容值
			, url: src + '/servlet/back/documentTable' //数据接口
			, page: true //开启分页
			, id: 'demotable'//当对应的进行条件查询的时候
			, cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
			, cols: [[ //表头
				{field: 'documentname', title: '文档标题', sort: true, fixed: 'left', width: 140}
				, {field: 'uploadpeople', title: '上传人', width: 120}
				, {field: 'uploadtime', title: '上传时间', sort: true, width: 200}
				, {field: 'downloadscore', title: '下载积分', width: 100}
				, {field: 'documenttype', title: '文档类型', width: 120}
				, {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
			]]
		});

		//监听行工具事件
		table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data //获得当前行数据
				, layEvent = obj.event; //获得 lay-event 对应的值
			if (layEvent === 'down') {
				layer.confirm('确认是否下载?', function (index) {
					window.location.href = src + "/servlet/back/download?documentname=" + data.documentname + "&documenttype=" + data.documenttype;
					return true;
				});
				return false;//阻止表单跳转
			} else if (layEvent === 'approved') {
				layer.confirm('是否确认审核通过', function (index) {
					// obj.del(); //删除对应行（tr）的DOM结构
					// layer.close(index);
					//向服务端发送删除指令
					$.ajax({
						url: src + '/servlet/back/approved',
						type: 'post'
						, data: {uploadtime: data.uploadtime},
						success: function (data) {
							console.log("--" + data.toString());
							if (data == "审核通过成功") {
								layer.alert(data);
								window.location.href = src + "/servlet/pback/documentReviewManage";
							} else {
								layer.msg(data);
							}
						}, error: function (err) {
							console.log(err);
						}
					});
					return false;//阻止表单跳转
				});
			}
			else if(layEvent ==='noapproved')
			{
				layer.confirm('是否确认审核不通过',function () {
					$.ajax({
						url: src + '/servlet/back/noapproved',
						type: 'post'
						, data: {uploadtime: data.uploadtime},
						success: function (data) {
							console.log("--" + data.toString());
							if (data == "审核不通过成功") {
								layer.alert(data);
								window.location.href = src + "/servlet/pback/documentReviewManage";
							} else {
								layer.msg(data);
							}
						}, error: function (err) {
							console.log(err);
						}
					});
				});
			}
			// else if (layEvent === 'edit') {
			// 	var othis = $(this), //othis当前button对象
			// 		method = othis.data('method');//data-method="dialog"中的值
			// 	if (method == "dialog") {
			// 		layer.open({
			// 			type: 2,
			// 			offset: '100px',
			// 			title: '修改信息',
			// 			area: ['500px', '400px'],
			// 			btn: ['修改', '取消'],
			// 			btn1: function (index, layero) {
			// 				var src = $("#srcAddress").val();
			// 				//layer.getChildFrame("form", index)获取iframe的表单
			// 				//serializeArray jquery方法，将表单对象序列化为数组
			// 				var formData = serializeObject($, layer.getChildFrame("form", index).serializeArray());
			//
			// 				$.ajax({
			// 					url: src + '/servlet/back/updateTable',
			// 					type: 'post',
			// 					data: formData,
			// 					success: function (data) {
			// 						layer.msg(data);
			// 						layer.close(index);
			// 						window.location.href = src + "/servlet/pback/userManage";
			// 					}, error: function (err) {
			// 						console.log(err);
			// 					}
			// 				});
			// 			},
			// 			content: src + '/servlet/pback/form' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
			// 			, success: function (layero, index) {
			// 				console.log(layero, index);
			// 				//	显示
			// 				var frameId = $(layero).find("iframe").attr('id');
			// 				$(window.frames[frameId].document).find("#userid").val(data.userid);
			// 				$(window.frames[frameId].document).find("#username").val(data.username);
			// 				$(window.frames[frameId].document).find("#password").val(data.password);
			// 				var isChecked = data.usersex == "男" ? "男" : "女";
			// 				console.log(data.usersex);
			// 				$(window.frames[frameId].document).find("input[name=usersex]").each(function () {//循环判断添加 radio
			// 					if ($(this).val() == isChecked) {
			// 						console.log($(this).val() + "对应的性别");
			// 						$(this).attr("checked", "checked")
			// 					}
			// 				});
			// 				form.render(); //更新全部
			// 				$(window.frames[frameId].document).find("#realname").val(data.realname);
			// 			}
			// 		});
			// 	}
			// }
		});

		var s = $("#demoReload").val();
		$('.demoTable .layui-btn').on('click', function () {
			var type = $(this).data('type');
			//	重载--点击对应的搜索按钮的时候进行对应的重载
			if (type == 'reload') {
				//执行重载--只重载数据
				table.reload('demotable', {
					where: { //设定异步数据接口的额外参数，任意设
						documentname: $("#documentTitle").val(),
						uploadpeople: $("#uploadPeople").val(),
						beginTime: $("#beginTime").val(),
						overTime: $("#overTime").val(),
						documenttype: $("#documenttype").val()
					}
					, page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			}
		});

		//将表单转为js对象数据
		function serializeObject($, array) {
			var obj = new Object();
			$.each(array, function (index, param) {
				if (!(param.name in obj)) {
					obj[param.name] = param.value;
				}
			});
			return obj;
		}
	});
</script>
</body>
</html>
