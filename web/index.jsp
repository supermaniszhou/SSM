<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI() + path;
%>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<a href="<%=basePath%>index/toupload">文件上传</a>
<a href="${pageContext.request.contextPath}/listfileServlet">获取下载文件列表</a>

</body>
</html>
