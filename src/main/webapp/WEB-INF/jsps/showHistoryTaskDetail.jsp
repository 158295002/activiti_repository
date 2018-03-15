<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dingqin
  Date: 2018/1/8
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任何列表</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/taskDetail.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/person.css">
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
            <th>任务下指派人</th>
            <th>任务开始时间</th>
            <th>流程类型</th>
            <th>状态</th>
        </tr>
        <c:forEach items="${taskDTOS}" var="task">
            <tr>
                <td>${task.historicTaskInstance.id}</td>
                <td>${task.historicTaskInstance.processDefinitionId}</td>
                <td>${task.historicTaskInstance.processInstanceId}</td>
                <td>${task.historicTaskInstance.name}</td>
                <td>${task.nextAssignee}</td>
                <td>
                        ${task.historicTaskInstance.createTime}
                </td>
                <td>${task.proc_def_name}</td>
                    <%--<td><a href="/doComplete?name=${name}">提交</a></td>--%>
                <td>${task.status}</td>
            </tr>
        </c:forEach>
    </table>
    <div style=" float: right;margin-right: 60px;">
        <a href="/doJump">返回</a>
    </div>
</div>
</body>
</html>
