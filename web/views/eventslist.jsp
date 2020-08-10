<%@ page import="il.ac.hit.mvc.utils.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.stream.JsonReader" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="il.ac.hit.mvc.utils.GsonDateDeSerializer" %>
<%@ page import="java.util.Date" %><%--
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
    <script src="script.js"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div data-role="page" id="listOfEventsPage" >
    <div data-role="header">
        <h1>List of events</h1>
    </div>
    <div data-role="content"  style="background: url(background_main.png);
        background-repeat:repeat-y;
        background-position:center center;
        background-attachment:scroll;
        background-size:100% 100%;
        color: white;font-size: 15px;text-shadow: 2px 2px black;">
        <p class="mc-top-margin-1-5"><b>Want to add a new event?</b></p>
        <div id="addEvent">
            <a href="/MyDiary/views/addevent.jsp" data-transition="pop"
               data-rel="dialog" data-icon="plus" data-role="button">Add Event</a>
        </div>
        <div id="eventsContainer"></div>

           <% String jsonEvent = (String) request.getAttribute("events");
            Gson gson = new Gson();

            Type listType = new TypeToken<List<Event>>(){}.getType();
            List<Event> events = gson.fromJson(jsonEvent, listType);
               for (Event event : events) {%>
        <div class="event" style="padding: 10px; margin-top: 10px; background-color: white;">
            <p><%=event.getEmail()%></p>
            <p><%=event.getTitle()%></p>
            <p><%=event.getLocation()%></p>
            <p><%=event.getDate()%></p>
            <p><%=event.getStarts()%></p>
            <p><%=event.getEnds()%></p>
            <p><%=event.getNote()%></p>
        </div>
        <% } %>
        </div>
    </div>

    <div data-role="footer" data-position="fixed">
        <div data-role="navbar">
            <ul>
                <li><a href="/MyDiary/views/home.jsp" data-icon="home">Log Out</a></li>
                <li><a href="/MyDiary/views/eventslist.jsp" data-icon="calendar">All Events</a></li>
                <li><a href="/MyDiary/views/usabilitygraph.jsp" data-icon="eye">Graph</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
