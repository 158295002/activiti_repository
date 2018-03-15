<%--
  Created by IntelliJ IDEA.
  User: dingqin
  Date: 2018/1/22
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/importFile.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/person.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/activiti.js"></script>
</head>

<body>
<div class="person">
    <div class="portrait"></div>
    <span>${name}</span>
    <br>
    <span><a href="/login">退出</a></span>
</div>
<div class="wrapper">
    <c:if test="${!empty message}">
        <div class="error">
            信息：${message}
        </div>

    </c:if>
    <form action="/deployProcess" method="post" class="form" enctype="multipart/form-data">
        流程定义文件:
        <input id = "fileU" type="file" name="bpmnFile" accept="" />
        <input type="submit" value="上传并发布" class="up" onclick="doUpload()">
    </form>
</div>
</body>
</html>
