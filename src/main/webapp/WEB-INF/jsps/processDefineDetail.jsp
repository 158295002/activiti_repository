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
    <title>流程定义列表</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/processDefineDetail.css">
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
    <strong>
        <div class="title">
            <span>流程定义列表</span>
            <span class="add">
                <a href="/doJump">返回</a>
                <a href="/importProcess">添加</a>
            </span>
        </div>
    </strong>
    <table cellspacing="3" border="1">
        <tr>
            <th>流程定义ID</th>
            <th>流程定义名称</th>
            <th>流程定义KEY</th>
            <th>流程定义版本</th>
            <th>部署ID</th>
            <th>资源BPMN名称</th>
            <th>资源PNG名称</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${processDefinitions}" var="processDefinition">
            <tr>
                <td>${processDefinition.id}</td>
                <td>${processDefinition.name}</td>
                <td>${processDefinition.key}</td>
                <td>${processDefinition.version}</td>
                <td>${processDefinition.deploymentId}</td>
                <td>${processDefinition.resourceName}</td>
                <td>${processDefinition.diagramResourceName}</td>
                <td><a href="/applyFor/${processDefinition.key}">发起申请</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
