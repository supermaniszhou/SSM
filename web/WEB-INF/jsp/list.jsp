<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--<c:choose>--%>
        <%--<c:when test="${not empty requestScope.list}">--%>
            <c:forEach items="${requestScope.list}" var="data" varStatus="vs">
                ${vs.count} +"   ===" ${data.name}<br/>
            </c:forEach>
        <%--</c:when>--%>
    <%--</c:choose>--%>

dfjkdjfkdjfkdjfdkfjkd
</body>
</html>
