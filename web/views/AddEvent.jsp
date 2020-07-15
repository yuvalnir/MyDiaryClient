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
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div data-role="page" id="main" >
    <div data-role="header">
        <h1>Main Page</h1>
    </div>
    <div data-role="content"  style="background: url(background_main.png);
    height: 100%;
    background-repeat:no-repeat;
    background-position:center;
    background-attachment:scroll;
    background-size:cover;
    color: white;font-size: 15px;text-shadow: 2px 2px black;">

        <div>
            <label>Event:</label>
            <input type="text" name="new_item" id="new_item"/>
            <label>Place:</label>
            <input type="text" name="new_item" id="item_place"/>
            <label>Begins:</label>
            <input type="datetime-local" name="new_item" id="item_begins"/>
            <label>Ends:</label>
            <input type="datetime-local" name="new_item" id="item_ends"/>
            <label>Notes:</label>
            <input type="text" name="new_item" style="height:120px;" id="item_notes"/>
            <!--            <label>Deadline Date:</label>-->
            <!--            <input type="date" name="new_date" id="new_date"/>-->
            <a onclick="add()" href="#main"  data-icon="plus" data-transition="pop"
               data-rel="dialog" data-role="button">Add Event</a>
        </div>
    </div>

    <div data-role="footer" data-position="fixed">
        <div data-role="navbar">
            <ul>
                <li><a href="#Home" data-icon="home">Entry Page</a></li>
                <li><a href="#calendar" data-icon="calendar">Calendar</a></li>
                <li><a href="#graph" data-icon="eye">Graph</a></li>
            </ul>
        </div>

    </div>
</div>
</body>
</html>
