<%--
  Created by IntelliJ IDEA.
  User: William
  Date: 4/30/2020
  Time: 12:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forum | Login & Registration</title>
    <style><%@include file="/static/loginStyle.css"%></style>
<%--    <link href="static/loginStyle.css" rel="stylesheet" type="text/css">--%>
<%--    <link href='https://fonts.googleapis.com/css?family=Open+Sans:700,600' rel='stylesheet' type='text/css'>--%>
</head>
<body>
    <br>
    <br>
    <br>
    <br>
    <form method="POST" action="login">
        <div class="box">
            <h1>Log In</h1>
            <label>
                <input type="text" name="username" class="email" />
            </label>
            <label>
                <input type="password" name="password" class="email" />
            </label>
            <input class="btn" type="submit" value="Login" />
        </div> <!-- End Box -->
    </form>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <form method="post" action="">
        <div class="box2">
            <h1>Sign Up!</h1>
            <label>
                <input type="email" name="email" value="email"  class="email" />
            </label>
            <label>
                <input type="password" name="email" value="email"  class="email" />
            </label>
            <label>
                <input type="text" name="email" value="username"  class="email" />
            </label>
            <a href="#"><div class="btn">Sign Up</div></a> <!-- End Btn -->
        </div> <!-- End Box -->
    </form>
</body>
</html>