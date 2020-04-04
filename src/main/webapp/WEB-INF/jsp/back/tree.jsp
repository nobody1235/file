<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>树组件</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/layui/css/layui.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="srcAddress"/>
<div id="test1"></div>
<button type="button" class="layui-btn layui-btn-normal" id="update">修改保存</button>
<input type="hidden" value="${roleid}" id="roleid"/>

<script>
	layui.use(['layer', 'jquery', 'tree'], function () {
		var layer = layui.layer;
		var tree = layui.tree;
		$ = layui.jquery;
		//获取对应的roleid信息
		var roleid = $("#roleid").val();
		console.log(roleid+"对应的内容是：");
		//查询数据库的对应的菜单的信息
		var src = $("#srcAddress").val();
		var data = [];
		$.ajax({
			url: src + '/servlet/back/selectMenu',//后台数据请求地址
			type: "post",
			success: function (resut) {
				console.log(resut);
				data = resut;
				console.log(data);
				//渲染---树格式：集合里面写一个集合再使用[]数组包起来
				var inst1 = tree.render({
					elem: '#test1'  //绑定元素
					, showCheckbox: true
					, data: data.menuAll
					, id: 'demoId' //定义索引---目的是为了勾选使用的
				});
				console.log("角色的ID" + data.menuroleId);
				var list = data.menuroleId;
				console.log("选择的ID" + data.menuroleId);
				var arr = [];
				//通过数组去拿
				for (var i = 0; i < list.length; i++) {
					//父节点放进来使用

					//判断子节点是不是正确的------子节点放进来
					if (list[i].children != null && list[i].children.length > 0) {
						for (var k = 0; k < list[i].children.length; k++) {
							getId(list[i].children[k], arr);
						}
					}else{
						arr.push(list[i].id);
					}
				}
				tree.setChecked('demoId', arr); //批量勾选 id 为arr数组
			}
			,error:function (err) {
				console.log(err);
			}
		});

		//修改对应的权限的信息
		$("#update").click(function () {
			//获得选中的节点
			var checkData = tree.getChecked('demoId');
			console.log("对应选择的是："+checkData);
			var arr = [];
			//通过数组去拿
			for (var i = 0; i < checkData.length; i++) {
				//父节点放进来使用
				arr.push(checkData[i].id);
				//判断子节点是不是正确的------子节点放进来
				if (checkData[i].children != null && checkData[i].children.length > 0) {
					for (var k = 0; k < checkData[i].children.length; k++) {
						getId(checkData[i].children[k], arr);
					}
				}
			}
			console.log(arr);
			$.ajax({
				url:src+'/servlet/back/updateTreeMenu',
				type:'post',
				data: {menuid:arr,roleid:roleid},
				dataType: "text",
				success:function(data){
					if(data == "success")
					{
						layer.msg("修改成功,下次登录生效");
						// window.location.href = src + "/menuServlet?method=findMenu";
					}
					else{
						layer.msg(data);
					}
				},error:function (err) {
					console.log(err);
				}
			});
		});
	});

	//    递归进行对应的权限的复选框的添加
	function getId(data, arr) {
		arr.push(data.id);
		if (data.children != null && data.children.length > 0) {
			for (var k = 0; k < data.children.length; k++) {
				getId(data.children[k], arr);
			}
		}
	}
</script>
</body>
</html>