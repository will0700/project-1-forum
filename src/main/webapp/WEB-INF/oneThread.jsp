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
</head>
<body>
    <h2>Welcome <c:out value="${user.username}" /> !</h2>
    <a href="/logout">Logout</a>
    <div>
        <h2><c:out value="${thread.title}" /></h2>
        <p><c:out value="${thread.content}" /></p>


        <form action="/comment" method="POST">
            <div>
                <label>Write a new comment: </label>
                <input type="text" name="comment" placeholder="Write your comment here">
                <input type="hidden" name="threadId" value="${thread.id}"> <!--pass thread id as hidden input-->
            </div>
            <input type="submit" value="post comment">
        </form>
        <div id="comment-container">
            <c:forEach items="${comments}" var="comment">
                <p class="comment">${comment.comment}</p>
            </c:forEach>
        </div>
    </div>
</body>
</html>