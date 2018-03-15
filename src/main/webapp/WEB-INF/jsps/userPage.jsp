<%--
  Created by IntelliJ IDEA.
  User: dingqin
  Date: 2018/1/8
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>流程系统</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/userPage.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/person.css">
</head>
<body>
<div class="person">
    <div class="portrait"></div>
    <span>${name}</span>
    <br>
    <span><a href="/login">退出</a></span>
</div>
<h1>欢迎进入流程系统</h1>
<c:if test="${!empty message}">
    <div class="error">
        信息：${message}
    </div>

</c:if>
<div class="wrapper">
    <div class="content">
        <strong>

            <ul>
                <ui>
                    <a href="/showAllProcessDefines">流程管理</a>
                </ui>
                <%--<ui>--%>
                    <%--<a href="/startProcess">启动流程定义</a>--%>
                <%--</ui>--%>
                <ui>
                    <a href="/showTaskDetail">待办任务</a>
                </ui>
                <ui>
                    <a href="/showHistoryDetail">所有任务</a>
                </ui>
            </ul>
        </strong>
    </div>
</div>
</body>
</html>
