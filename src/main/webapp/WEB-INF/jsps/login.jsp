<%--
  Created by IntelliJ IDEA.
  User: dingqin
  Date: 2018/1/8
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
</head>

<h2>
    用户登录
</h2>
<div class="wrapper">
    <div class="content">
        <form action="/doLogin" method="get">
            <div class="userName">
                用户名：<input type="text" name="name">
            </div>
            <div>
                &nbsp;密码：<input type="password" name="password">
            </div>
            <div>
                <input type="submit" value="登录" class="login">
            </div>
        </form>
    </div>
</div>
</body>
</html>
