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

<div data-role="page" id="addEventPage" >
    <div data-role="header">
        <h1>Add Event</h1>
    </div>
    <div data-role="content"  style="background: url(background_main.png);
    background-repeat:repeat-y;
    background-position:center center;
    background-attachment:scroll;
    background-size:100% 100%;
    color: white;font-size: 15px;text-shadow: 2px 2px black;">

        <form method="post" action="/MyDiary/controller/user/addevent" style="width: 400px; margin: 0 auto;">
            <label>Event:</label>
            <input type="text" name="title" id="title"/>
            <label>Place:</label>
            <input type="text" name="location" id="location"/>
            <label>Begins:</label>
            <input type="time" name="timeStart" id="timeStart"/>
            <label>Ends:</label>
            <input type="time" name="timeEnd" id="timeEnd"/>
            <label>Deadline Date:</label>
            <input type="date" name="date" id="date"/>
            <label>Note:</label>
            <input type="text" name="note" style="height:60px;" id="note"/>
            <input type="submit" value="Add Event">
        </form>
    </div>
</div>

</body>
</html>
