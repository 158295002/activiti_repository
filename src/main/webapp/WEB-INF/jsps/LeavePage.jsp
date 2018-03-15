<%--
  Created by IntelliJ IDEA.
  User: dingqin
  Date: 2018/1/23
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发起申请</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/leave.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/leave.js"></script>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <form action="/doApplayFor/${key}" method="post">
            <input type="hidden" value="${key}">
            <div class="apply">
                申请人:
                <input type="text" name="name" value="${name}" disabled="disabled">
            </div>
            <div>
                申请开始时间:
                <input type="date" name="startDate" id="start"/>
            </div>
            <div>
                申请结束时间:
                <input type="date" name="endDate" id="end" onblur="funcount()"/>
            </div>
            <div class="reason">
                申请理由：
                <textarea rows="3" name="reason"></textarea>
            </div>
            <div class="reason">
                &nbsp;&nbsp;申请时间：
                <input type="text" id="leaveDays" name="leaveDays" disabled="disabled">
            </div>
            <div class="next">
                下个审批人:
                <input type="text" name="nextAssignee">
            </div>
            <div class="submit">
                <input type="submit" value="提交">
            </div>
        </form>
    </div>
</div>
</body>
</html>
