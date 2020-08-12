<%@ page import="il.ac.hit.mvc.utils.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="il.ac.hit.mvc.controller.UserController" %>
<%--
  Created by IntelliJ IDEA.
  User: yuval
  Date: 14/07/2020
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
    <script src="script.js"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<script type="text/javascript">
    $(document).ready(function (){
        $('.deleteEvent').click(function(e) {
            e.preventDefault()
            console.log('deleteeeee>>>>>>')
        })

    })
</script>

<div data-role="page" id="listOfEventsPage" style="background: url(background_main.png);
        background-repeat:repeat-y;
        background-position:center center;
        background-attachment:scroll;
        background-size:100% 100%;">
    <div data-role="header">
        <h1>List of events</h1>
    </div>
    <div data-role="content"  style="
        width: 400px;
        margin: 0 auto;

        color: white;font-size: 15px;text-shadow: 2px 2px black;">
        <p class="mc-top-margin-1-5"><b>Want to add a new event?</b></p>
        <div id="addEvent">
            <a href="/MyDiary/views/addevent.jsp" data-transition="pop"
               data-rel="dialog" data-icon="plus" data-role="button">Add Event</a>
        </div>
        <div id="eventsContainer"></div>

        <%
            List<Event> events = (List<Event>)request.getAttribute("events");
            for (Event event : events) {%>
        <div class="event" style="padding: 10px; margin-top: 10px; background-color: white;">
            <a data-event-id="  <% event.getId(); %>  "  data-transition="pop" data-rel="dialog" class="updateEvent ui-icon-edit ui-btn-icon-notext" style="position: relative; left: 12px; top: 4px;"></a>
            <a data-event-id="  <% event.getId(); %>  " class="deleteEvent ui-icon-delete ui-btn-icon-notext" style="position: relative; left: 40px; top: 4px"></a>

            <p><%="Title: " + event.getTitle() + "  Location: " + event.getLocation()%></p>
            <p><%="Date: " +event.getDate() + "  Starts: " + event.getStarts() + "  Ends: " + event.getEnds()%></p>
            <p><%="Note: " +event.getNote()%></p>
        </div>
        <% } %>
    </div>


    <div data-role="footer" data-position="fixed">
        <div data-role="navbar">
            <ul>
                <li><a href="/MyDiary/controller/user/logout" data-icon="home">Log Out</a></li>
                <li><a href="/MyDiary/controller/events/eventslist" data-icon="calendar">All Events</a></li>
                <li><a href="/MyDiary/controller/user/usabilitygraph" data-icon="eye">Graph</a></li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function (){
        $('.deleteEvent').click(function(e) {
            e.preventDefault()
            console.log('deleteeeee>>>>>>')
        })

    })
</script>

</body>
</html>
