<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--不能使用对应的jstl文件的原因是因为要加上**********isELIgnored="false"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<title>管理员主页面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui/css/layui.css" type="text/css">
	<script charset="UTF-8" src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainPage.css" type="text/css">
	<script charset="UTF-8" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
	<script charset="UTF-8" src="${pageContext.request.contextPath}/js/mainPage.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
		<div class="layui-logo" style="width:350px;font-size: 22px;text-align: left;text-indent: 20px;" ><h5>文档后台管理</h5></div>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
<%--					${tbl_bom.bom_name}--%>${loginAdmin.username}
				</a>
				<dl class="layui-nav-child">
<%--					${pageContext.request.contextPath}/BackServlet?method=exit"%--%>
					<dd><a href="${pageContext.request.contextPath}/servlet/back/exit">注销</a></dd>
				</dl>
			</li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black" >
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<div class="layui-side layui-bg-black" style="margin-top:20px;">
				<div class="layui-side-scroll">
					<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="text">
						<c:if test="${not empty menuMap}">
							<c:forEach items="${menuMap}" var="i">
								<li class="layui-nav-item">
									<a href="javascript:void(0);">${i.key}</a>
									<dl class="layui-nav-child">
										<c:forEach items="${i.value}" var="j">
											<dd><a href="javascript:void(0);" title="${pageContext.request.contextPath}/${j.menuurl}" onclick="changePath(this)">${j.menuname}</a></dd>
										</c:forEach>
									</dl>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="layui-body" id="div-right">
		<iframe frameborder="0" name="manager" id="div-right-iframe" style="margin-top:20px;"></iframe>
	</div>
	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© 传一科技
	</div>
</div>
</body>
</html>
