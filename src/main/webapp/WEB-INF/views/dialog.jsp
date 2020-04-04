<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>弹出层</title>
    <link rel="stylesheet" type="text/css" href="../js/layui/css/layui.css"/>
    <script type="text/javascript" src="../js/layui/layui.js"></script>
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="srcAddress"/>

<button data-method="dialog" class="layui-btn">弹出层简单例子</button>
<script>
    layui.use(['layer','form'], function(){
        var layer = layui.layer, $ = layui.jquery;
        $('.layui-btn').on('click', function(){
            var othis = $(this), //othis当前button对象
                method = othis.data('method');//data-method="dialog"中的值
            if(method == "dialog"){
                layer.open({
                    type: 2,
                    title: '添加信息',
                    area: ['500px', '400px'],
                    btn: ['添加', '取消'],
                    btn1: function(index, layero){
                        var src = $("#srcAddress").val() ;
                        //layer.getChildFrame("form", index)获取iframe的表单
                        //serializeArray jquery方法，将表单对象序列化为数组
                        var formData = serializeObject($, layer.getChildFrame("form", index).serializeArray());
                        $.ajax({
                            url:src+'/formServlet',
                            type:'post',
                            data: formData,
                            success:function(data){
                                layer.msg(data);
                                layer.close(index);
                            },error:function (err) {
                                console.log(err);
                            }
                        });
                    },
                    content: '../views/form.jsp' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                    ,success: function(layero, index){
                        console.log(layero, index);
                    }
                });
            }
        });
    });

    //将表单转为js对象数据
    function serializeObject($, array){
        var obj=new Object();
        $.each(array, function(index,param){
            if(!(param.name in obj)){
                obj[param.name]=param.value;
            }
        });
        return obj;
    };
</script>
</body>
</html>