<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>

<%
    Gson gsonObj = new Gson();
    Map<Object,Object> map = null;
    List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

    //Map<String, Integer> locationList = new HashMap<String, Integer>();
    //locationList.putAll((Map<String, Integer>)request.getSession().getAttribute("locationList"));
    Map<String, Integer> locationList = (Map<String, Integer>) request.getAttribute("locationList");

    for (String location : locationList.keySet()) {
        map = new HashMap<Object,Object>();
        map.put("label", location);
        map.put("y", locationList.get(location));
        list.add(map);
    }

    String dataPoints = gsonObj.toJson(list);
%>

<!DOCTYPE HTML>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Usability Graph</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <link rel="stylesheet" href="style.css">

    <script type="text/javascript">
        window.onload = function() {

            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                theme: "light2",
                title: {
                    text: "Events by Location Graph"
                },
                data: [{
                    type: "doughnut",
                    yValueFormatString: "#,##0",
                    indexLabel: "{label}: {y}",
                    toolTipContent: "{y}",
                    dataPoints : <%=dataPoints%>
                }]
            });
            chart.render();
        }
    </script>

</head>
<body>

<div data-role="page" id="graphPage" style="background: url(background_main.png);
            background-repeat:repeat-y;
            background-position:center center;
            background-attachment:scroll;
            background-size:100% 100%;
            color: white;font-size: 15px; height: 100%">
    <div data-role="header">
        <h1>Statistics Graph</h1>
    </div>
    <div>
        <div id="chartContainer" data-role="content" style="height: 400px; width: 96%;"></div>
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
</body>
</html>