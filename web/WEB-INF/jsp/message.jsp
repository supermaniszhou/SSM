<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/17 0017
  Time: 下午 3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI() + path;
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message}
</body>
</html>
