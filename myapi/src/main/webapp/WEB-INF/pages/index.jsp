<%--
  Created by IntelliJ IDEA.
  User: qwy
  Date: 17/6/5
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>博客猿首页</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<h1>这里是博客猿首页</h1>

<!-- 如果用户信息为空（未登录） -->
<c:if test="${empty user}">
    <div class="alert alert-warning" role="alert">
        &nbsp; &nbsp; <a href="/admin/users/login">立刻登录</a> &nbsp; &nbsp; 没有帐号？<a href="/admin/users/add">快速注册</a>
    </div>
</c:if>

<c:if test="${!empty user}">
    &nbsp; &nbsp; <h4>欢迎回来，${user.username} &nbsp; &nbsp; <a href="/admin/users/logout">退出登录</a></h4>
</c:if>
&nbsp; &nbsp; <h3>查看<a href="/admin/users">在线用户</a></h3>
&nbsp; &nbsp; <h3>查看<a href="/admin/blogs">博客列表</a></h3>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
