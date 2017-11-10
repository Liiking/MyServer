<%--
  Created by IntelliJ IDEA.
  User: liheyao
  Date: 17/6/12
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加博客</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <%-- 日期选择控件 --%>
    <link rel="stylesheet" type="text/css" href="jquery.datetimepicker.css"/>
    <script src="jquery.js"></script>
    <script src="jquery.datetimepicker.js"></script>
    <script>
        $('#datetimepicker').datetimepicker({lang:'ch'});
    </script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>添加博客</h1>
    <hr/>
    <form:form action="/admin/blogs/addP" method="post" commandName="blogA" role="form">
        <div class="form-group">
            <label for="title">标题：</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="请输入博客标题"/>
        </div>
        <div class="form-group">
            <label for="userByUserId.id">作者：</label>
            <select class="form-control" id="userByUserId.id" name="userByUserId.id">
                <c:forEach items="${userList}" var="user">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="content">内容：</label>
            <textarea class="form-control" id="content" name="content" rows="3" placeholder="请输入博客内容"></textarea>
        </div>
        <div class="form-group">
            <label for="pubDate">发布时间：</label>
            <input type="text" id="pubDate" name="pubDate" />
            <%--<input type="date" id="pubDate" name="pubDate" value="2015-09-24" min="2015-09-16" max="2015-09-26"/>--%>
            <%--<input type="date" class="form-control" id="pubDate" name="pubDate"/>--%>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>

<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script>
    //执行一个laydate实例
    laydate.render({
        elem: '#pubDate' //指定元素
    });
</script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>