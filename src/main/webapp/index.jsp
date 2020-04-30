<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forum | Login & Registration</title>
<%--    Note, .css FILES have to be included with a style tag, like below.--%>
    <style><%@include file="/static/loginStyle.css"%></style>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:700,600' rel='stylesheet' type='text/css'>
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