<%--
  Created by IntelliJ IDEA.
  User: William
  Date: 4/29/2020
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Forums</title>
</head>
<body>
    <h2>Welcome <c:out value="${user.username}" /> !</h2>
    <a href="/logout">Logout</a>
    <h2>These are the forums</h2>
    <div>
        <c:forEach items="${forums}" var="forum">
            <div class="forum">
                <a href="forum/${forum.id}"><c:out value="${forum.forumName}" /></a>
            </div>
        </c:forEach>
    </div>
</body>
</html>
