<%--
  Created by IntelliJ IDEA.
  User: dingqin
  Date: 2018/1/8
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>任何列表</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/taskDetail.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/person.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/activiti.js"></script>
</head>
<body>
<div class="person">
    <div class="portrait">
    </div>
    <span>${name}</span>
    <br>
    <span><a href="/login">退出</a></span>
</div>
<div class="wrapper">
    <table cellspacing="3" border="1">
        <tr>
            <th>任务ID</th>
            <th>流程定义ID</th>
            <th>流程实例ID</th>
            <th>任务名称</th>
            <th>任务指派人</th>
            <th>任务开始时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${tasks}" var="task">
            <tr>
                <td>${task.id}</td>
                <td>${task.processDefinitionId}</td>
                <td>${task.processInstanceId}</td>
                <td>${task.name}</td>
                <td>${task.assignee}</td>
                <td>
                    <%--还不能转换--%>
                    <%--<script>--%>
                    <%--${task.createTime}.format("yyyy-MM-dd hh:mm:ss");--%>
                <%--</script>--%>
                    ${task.createTime}
                </td>
                    <%--<td><a href="/doComplete?name=${name}">提交</a></td>--%>
                <td><a href="/doComplete/${task.id}">签收</a></td>
            </tr>
        </c:forEach>
    </table>
    <%--<c:if test="${empty tasks}">--%>
    <%--<a href="/doJump?name=${name}" }>返回</a>--%>
    <%--</c:if>--%>
    <%--<a href="/login">重新登录</a>--%>
</div>
</body>
</html>
