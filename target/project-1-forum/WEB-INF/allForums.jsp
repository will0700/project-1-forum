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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <style><%@include file="/static/forumStyles.css"%></style>
</head>
<body>
    <div class="col-lg-12" id="main-container" class="container-fluid">
        <div id="navigation-container">
            <div class ="page-header">
                <h2>Welcome <c:out value="${user.username}" /> !</h2>
            </div>

            <div class="btn btn-dark" id="logout">
                <a class href="/logout">Logout</a>
            </div>
        </div>

        <div id="forums-container">
            <div id="header2">
                <h2>Forums listed below</h2>
            </div>

            <div id="nav">
                <c:forEach items="${forums}" var="forum">
                    <div class="forum">
                        <a href="forum/${forum.id}"><c:out value="${forum.forumName}" /></a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
