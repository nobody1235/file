<%--
  Created by IntelliJ IDEA.
  User: junlong
  Date: 2019-11-13
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <title>用户信息框</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/layui/layui.js"></script>
</head>
<body>
<form class="layui-form" action="../formServlet" lay-filter="test1" onsubmit="return false;">
    <input type="hidden" name="userid" id="userid" required  lay-verify="required|phone|number" autocomplete="on" class="layui-input">
    <div class="layui-form-item">
        <label class="layui-form-label">用户框</label>
        <div class="layui-input-block">
            <input type="text" name="username" id="username" required  lay-verify="required|phone|number" placeholder="请输入标题" autocomplete="on" class="layui-input">
        </div>
    </div>
<%--    <div class="layui-form-item">--%>
<%--        <label class="layui-form-label">密码框</label>--%>
<%--        <div class="layui-input-inline">--%>
<%--            <input type="text" name="password" id="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">--%>
<%--        </div>--%>
<%--        <div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
<%--    </div>--%>
<%--    <div class="layui-form-item">--%>
<%--        <label class="layui-form-label">单选框</label>--%>
<%--        <div class="layui-input-block">--%>
<%--            <input type="radio" name="usersex" value="男" title="男">--%>
<%--            <input type="radio" name="usersex" value="女" title="女">--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">积分</label>
        <div class="layui-input-block">
            <input name="addscore" id="addscore" placeholder="请输入内容" class="layui-textarea"></input>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">用户状态</label>
        <div class="layui-input-block">
            <input name="userstate" id="userstate" placeholder="请输入内容" class="layui-textarea"></input>
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
    });
</script>
</body>
</html>