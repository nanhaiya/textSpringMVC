<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/6 0006
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript">
        //页面加载，绑定单机事件
        $(function () {
            $("#btn").click(function () {
                // alert("hello btn");
                //发送ajax请求
                $.ajax({
                    //编写json格式，设置属性和值
                    url:"hello/textAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"呵呵","password":"123","age":"21"}',
                    dataType:"Json",
                    type:"post",
                    success:function (data) {
                        //data服务器端响应的json数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.age);
                    }
                });
            });

        });


    </script>

</head>
<body>
<a href="hello/hello_ok">hello</a><br>
<a href="hello/testString">testString</a><br>
<a href="hello/testVoid">testVoid</a><br>
<a href="hello/testModelAndView">testModelAndView</a><br>
<a href="hello/testForwardOrRedirect">testForwardOrRedirect</a><br>

<button id="btn">发送ajax请求</button><br>

<h3>传统上传</h3>
<%--文件上传--%>
<form action="file/fileUoLoad" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"><br>
    <input type="submit" value="上传"><br>
</form>

<h3>springMVC文件上传</h3>
<form action="file/fileUoLoadMVC" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"><br>
    <input type="submit" value="springMVC上传">
</form>
</body>
</html>
