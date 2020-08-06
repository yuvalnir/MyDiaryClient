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
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="script.js"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div data-role="page" id="entryPage" >
    <div data-role = "header" id="head">
        <h2>Diary App</h2>
    </div>
    <div data-role="content" style="background: url(background_main.png);
    background-repeat:repeat-y;
    background-position:center center;
    background-attachment:scroll;
    background-size:100% 100%;
    color: white;font-size: 15px;text-shadow: 2px 2px black;">
        <h1 class="header" style="text-align: center;">Welcome to your</br>diary!</h1>
        <div>
            <p class="mc-top-margin-1-5" ><b>Existing Users</b></p>

            <form method="post" action="/MyDiary/controller/user/login">
                <label for="email">Enter your email:</label>
                <input type="text" name="email" id="email">
                <label for="password">Enter your password:</label>
                <input type="text" name="password" id="password">
                <input type="submit" value="Sign In">
            </form>

            <p class="mc-top-margin-1-5"><b>Don't have an account?</b></p>
            <!--             data-role="fieldcontain" -->
            <div id="signUp">
                <p>
                    <a href="/MyDiary/views/signup.jsp" data-transition="pop" data-rel="external" data-role="button">Sign Up</a></p>
            </div>
        </div>
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
