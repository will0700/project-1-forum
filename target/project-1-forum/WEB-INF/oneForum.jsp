<%--
  Created by IntelliJ IDEA.
  User: William
  Date: 5/1/2020
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Forum | <c:out value="${forum.forumName}" /></title>
</head>
<body>
    <h2>Welcome <c:out value="${user.username}" /> !</h2>
    <a href="/logout">Logout</a>
    <h2>Welcome to the <c:out value="${forum.forumName}" /> forum!</h2>
    <a href="/logout">Logout</a>
    <div>
        <c:forEach items="${threads}" var="thread">
            <div class="thread">
                <a href="/thread/${thread.id}"><c:out value="${thread.title}" /></a>
            </div>
        </c:forEach>
        <br>
        <form action="/thread" method="POST">
            <label>Title: </label>
            <input type="text" name="threadTitle">
            <label>Content: </label>
            <input type="text" name="threadContent">
            <input type="hidden" name="forumId" value="${forum.id}">
            <input type="submit" value="Post thread">
        </form>
    </div>
</body>
</html>
