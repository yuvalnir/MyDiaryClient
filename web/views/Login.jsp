<%--
  Created by IntelliJ IDEA.
  User: yuval
  Date: 13/07/2020
  Time: 14:50
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
<div data-role="page" id="signIn">
    <div data-role="header">
        <h1>Sign In Page</h1>
    </div>
    <div data-role="content" style="background: url(background_main.png);
    height: 100%;
    background-repeat:no-repeat;
    background-position:center;
    background-attachment:scroll;
    background-size:cover;
    color: white;font-size: 15px;text-shadow: 2px 2px black;">
        <form method="post" action="demoform.asp">
            <label for="email">Enter your email:</label>
            <input type="text" name="fname" id="email">
            <label for="password">Enter your password:</label>
            <input type="text" name="lname" id="password">
            <div id="submitIn" data-role="fieldcontain" style="width: 30%">
                <a href="#main" data-transition="pop"
                   data-rel="dialog" data-role="button">Submit</a>
            </div>
        </form>
    </div>
</div>

<div data-role="page" id="signUp">
    <div data-role="header">
        <h1>Sign Up Page</h1>
    </div>
    <div data-role="content"  style="background: url(background_main.png);
    height: 100%;
    background-repeat:no-repeat;
    background-position:center;
    background-attachment:scroll;
    background-size:cover;
    color: white;font-size: 15px;text-shadow: 2px 2px black;">
        <form>
            <label for="fname">First name:</label>
            <input type="text" name="fname" id="fname">
            <label for="fname">Last name:</label>
            <input type="text" name="lname" id="lname">
            <label for="email">Enter your email:</label>
            <input type="text" name="newEmail" id="newEmail">
            <label for="password">Enter your password:</label>
            <input type="text" name="newPassword" id="newPassword">
            <label for="varifiedPassword">Varify your password:</label>
            <input type="text" name="vPassword" id="varifiedPassword">
            <div id="submitUp" data-role="fieldcontain" style="width: 30%;">
                <a href="#main" data-transition="pop"
                   data-rel="dialog" data-role="button">Submit</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
