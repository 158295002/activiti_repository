<%--
  Created by IntelliJ IDEA.
  User: dingqin
  Date: 2018/1/8
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签收</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/leave.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/leave.js"></script>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <form action="/completeTask" method="get">
            <div class="apply">
                申请人:
                <input type="text" name="name" value="${leaveModel.name}" disabled="disabled">
            </div>
            <%--<div>--%>
                <%--申请开始时间:--%>
                <%--<input type="date" name="startDate" id="start" value="${leaveModel.startDate}" disabled="disabled"/>--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--申请结束时间:--%>
                <%--<input type="date" name="endDate" id="end" value="${leaveModel.endDate}" disabled="disabled"/>--%>
            <%--</div>--%>
            <div class="reason">
                申请理由：
                <input type="text"  value="${leaveModel.reason}" disabled="disabled"></textarea>
            </div>
            <div class="reason">
                &nbsp;&nbsp;申请时间：
                <input type="text" id="leaveDays" name="leaveDays" disabled="disabled" value="${leaveModel.leaveDays}">
            </div>
            <div class="next">
                审批意见：
                <input type="text" name="reason">
            </div>
            <div class="submit">
                <input type="submit" value="提交">
            </div>
        </form>
    </div>
</div>
</body>
</html>
