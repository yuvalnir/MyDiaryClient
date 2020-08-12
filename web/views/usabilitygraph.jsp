<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<%
    Gson gsonObj = new Gson();
    Map<Object,Object> map = null;
    List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

    map = new HashMap<Object,Object>(); map.put("label", "Australia"); map.put("y", 81000); list.add(map);
    map = new HashMap<Object,Object>(); map.put("label", "China"); map.put("y", 47000); list.add(map);
    map = new HashMap<Object,Object>(); map.put("label", "Brazil"); map.put("y", 32500); list.add(map);
    map = new HashMap<Object,Object>(); map.put("label", "Guinea"); map.put("y", 19300); list.add(map);
    map = new HashMap<Object,Object>(); map.put("label", "India"); map.put("y", 19000); list.add(map);
    map = new HashMap<Object,Object>(); map.put("label", "Jamaica"); map.put("y", 9800); list.add(map);
    map = new HashMap<Object,Object>(); map.put("label", "Kazakhstan"); map.put("y", 5500); list.add(map);
    map = new HashMap<Object,Object>(); map.put("label", "Russia"); map.put("y", 5300); list.add(map);
    map = new HashMap<Object,Object>(); map.put("label", "Others"); map.put("y", 15060); list.add(map);

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
    <link rel="stylesheet" href="style.css">
    <script type="text/javascript">
        window.onload = function() {

            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                theme: "light2",
                title: {
                    text: "World Bauxite Production"
                },
                subtitles: [{
                    text: "in tonnes"
                }],
                data: [{
                    type: "doughnut",
                    yValueFormatString: "#,##0",
                    indexLabel: "{label}: {y}K",
                    toolTipContent: "{y}K tonnes",
                    dataPoints : <%=dataPoints%>
                }]
            });
            chart.render();

        }
    </script>
</head>
<body>
<h1>Statistics by Location</h1>

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

</div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>