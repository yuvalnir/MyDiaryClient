<%--
  Created by IntelliJ IDEA.
  User: yuval
  Date: 14/07/2020
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

<div data-role="page" id="signUpPage">
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

        <form method="post" action="/MyDiary/controller/user/signup">
            <label for="email">Enter your email:</label>
            <input type="text" name="email" id="email" placeholder="Email">
            <label for="password">Enter your password:</label>
            <input type="password" name="password" id="password" placeholder="Password">
            <label for="verifyPassword">Verify your password:</label>
            <input type="password" name="verifyPassword" id="verifyPassword" placeholder="Enter Password Again">
            <input type="submit" value="Submit">
        </form>
    </div>
    <div data-role="popup" id="signUpPopup" data-position-to="window" data-transition="turn"><p>Oops! Passwords doesnt match!</p></div>
</div>

</body>
</html>
