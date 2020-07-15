<%--
  Created by IntelliJ IDEA.
  User: yuval
  Date: 13/07/2020
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diary App</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div data-role="page" id="Home" >
    <div data-role = "header" id="head">
        <h2>Diary App</h2>
    </div>

    <div data-role="content" style="background: url(background_main.png);
    height: 100%;
    background-repeat:no-repeat;
    background-position:center;
    background-attachment:scroll;
    background-size:cover;
    color: white;font-size: 15px;text-shadow: 2px 2px black;">
        <h1 class="header" style="text-align: center;">Welcome to your</br>diary!</h1>
        <p class="mc-top-margin-1-5" ><b>Existing Users</b></p>
        <p><a href="/views/Login.jsp" data-transition="pop"
              data-rel="dialog" data-role="button">Sign In</a></p>
        <p class="mc-top-margin-1-5"><b>Don't have an account?</b></p>
        <p><a href="/views/SignUp.jsp" data-transition="pop"
              data-rel="dialog" data-role="button">Sign Up</a></p>
    </div>

    <div data-role="footer" data-position="fixed">
        <h4>This app was developed by:</h4>
        <ul style="list-style-type: none;">
            <li>Alisa Makarova</li>
            <li>Ben Vahaba</li>
            <li>Yuval Nir</li>
        </ul>
    </div>
</div>
</body>
</html>
