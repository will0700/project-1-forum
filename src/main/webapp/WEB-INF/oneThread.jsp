<%--
  Created by IntelliJ IDEA.
  User: William
  Date: 5/1/2020
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Forum | ${thread.title}</title>
    <style><%@include file="/static/oneThread.css"%></style>
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

    <div>
        <div class="threadContent">
        <h2><c:out value="${thread.title}" /></h2>
        <p><c:out value="${thread.content}" /></p>
        </div>

        <div class = "comment">
        <form action="/comment" method="POST">
            <div>
                <label>Write a new comment: </label>
                <input type="text" name="comment" placeholder="Write your comment here">
                <input type="hidden" name="threadId" value="${thread.id}"> <!--pass thread id as hidden input-->
            </div>
            <input type="submit" value="post comment">
        </form>
        </div>
        <div id="comment-container">
            <c:forEach items="${comments}" var="comment">
                <p class="comment">${comment.comment}</p>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>