<%--
  Created by IntelliJ IDEA.
  User: yuval
  Date: 14/07/2020
  Time: 18:08
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
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div data-role="page" id="calendar">
    <div data-role="header">
        <h1>Calendar Page</h1>
    </div>
    <div data-role="content"  style="background: url(background_main.png);
    background-repeat:repeat-y;
    background-position:center center;
    background-attachment:scroll;
    background-size:100% 100%;
    color: white;font-size: 15px;text-shadow: 2px 2px black;">
        <div type="date"></div>
    </div>
</div>
</body>
</html>
