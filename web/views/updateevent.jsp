<%@ page import="il.ac.hit.mvc.utils.Event" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yuval
  Date: 13/08/2020
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>

<%
    List<Event> events = (List<Event>)request.getAttribute("events");
%>

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

<div data-role="page" id="updateEventPage" >
    <div data-role="header">
        <h1>update Event</h1>
    </div>
    <div data-role="content"  style="background: url(background_main.png);
    background-repeat:repeat-y;
    background-position:center center;
    background-attachment:scroll;
    background-size:100% 100%;
    color: white;font-size: 15px;text-shadow: 2px 2px black;">

        <form method="post" action="/MyDiary/controller/events/updateevent" style="width: 400px; margin: 0 auto;">
            <label>Event:</label>
            <input type="text" name="title" id="title" value="<%=events.get(0).getTitle()%>"/>
            <label>Place:</label>
            <input type="text" name="location" id="location" value="<%=events.get(0).getLocation()%>"/>
            <label>Begins:</label>
            <input type="time" name="timeStart" id="timeStart" value="<%=events.get(0).getStarts()%>"/>
            <label>Ends:</label>
            <input type="time" name="timeEnd" id="timeEnd" value="<%=events.get(0).getEnds()%>"/>
            <label>Deadline Date:</label>
            <input type="date" name="date" id="date" value="<%=events.get(0).getDate()%>"/>
            <label>Note:</label>
            <input type="text" name="note" style="height:60px;" id="note" value="<%=events.get(0).getNote()%>"/>
            <input type="hidden" name="eventid" id="eventid" value="<%=events.get(0).getId()%>">
            <input type="submit" value="Update Event">
        </form>
        <a href="/MyDiary/controller/events/eventslist" data-role="button" data-icon="back" style="width: 400px; margin: 0 auto;">Back</a>
    </div>
</div>

</body>
</html>
