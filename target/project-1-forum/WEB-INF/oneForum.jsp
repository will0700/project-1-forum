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
    <style><%@include file="/static/oneforumStyles.css"%></style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
<div class="col-lg-12" id="container">
    <div class ="page-header">
    <h2>Welcome <c:out value="${user.username}" /> !</h2>
    </div>

    <div class="btn btn-dark" id="logout">
    <a href="/logout">Logout</a>
    </div>

        <div id="header2">
    <h2>Welcome to the <c:out value="${forum.forumName}" /> forum!</h2>
        </div>
    <div>

        <br>
        <form action="/thread" method="POST">
            <div class="form-group">
            <label>Title: </label>
            <input type="text" name="threadTitle">
            </div>
            <div class="form-group">
            <label>Content: </label>
                <textarea id="textbox" type="text" name="threadContent"></textarea>
            <input type="hidden" name="forumId" value="${forum.id}">
                <br>
            <input type="submit" value="Post thread">
            </div>
        </form>
        <br>
        <div id="header3">
            <h2><c:out value="${forum.forumName}"  /> threads listed below</h2>
        </div>
        <div id="nav">
        <c:forEach items="${threads}" var="thread">
            <div class="thread">
                <a href="/thread/${thread.id}"><c:out value="${thread.title}" /></a>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
</body>

</html>
