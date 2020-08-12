<%--
  Created by IntelliJ IDEA.
  User: yuval
  Date: 13/07/2020
  Time: 14:52
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
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
    <link rel="stylesheet" href="style.css">

    <script type="text/javascript">
        //Graph
        $(function () {
            var options = {
                title: {
                    text: "Events by Location"
                },
                // axisX:{
                //     viewportMinimum: -50,
                //     viewportMaximum: 50
                // },
                // width: 600,
                height: 400,
                data: [{
                    type: "doughnut",
                    startAngle: 45,
                    showInLegend: false,
                    legendText: "{label}",
                    indexLabel: "{label} - #percent%",
                    toolTipContent: "<b>{label}:</b> {y} (#percent%)",
                    yValueFormatString:"#,##0.#"%"",
                    dataPoints: [
                        { label: "Tel Aviv", y: 36 },
                        { label: "Jerusalem", y: 31 },
                        { label: "Tizinabi", y: 17 },
                        { label: "Los Angeles", y: 7 },
                        { label: "Lhasa", y: 10 },
                    ]
                }]
            };

            $("#chartContainer").CanvasJSChart(options);
        });

    </script>
    <link rel="stylesheet" href="style.css">
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

        <div data-role="footer" data-position="fixed">
            <div data-role="navbar">
                <ul>
                    <li><a href="/MyDiary/views/home.jsp" data-icon="home">Log Out</a></li>
                    <li><a href="/MyDiary/controller/events/events" data-icon="calendar">All Events</a></li>
                    <li><a href="/MyDiary/views/usabilitygraph.jsp" data-icon="eye">Graph</a></li>
                </ul>
            </div>
        </div>
    </div>



</body>
</html>

