
$(document).on("pagebeforeshow","#graphPage", function(){

    //Graph
    $(function () {
        var options = {
            title: {
                text: "Events by Location"
            },
            data: [{
                type: "pie",
                startAngle: 45,
                showInLegend: false,
                legendText: "{label}",
                indexLabel: "{label} ({y})",
                yValueFormatString:"#,##0.#"%"",
                dataPoints: [
                    { label: "Tel Aviv", y: 36 },
                    { label: "Jerusalem", y: 31 },
                    { label: "Tizinabi", y: 7 },
                    { label: "Twitter", y: 7 },
                    { label: "Facebook", y: 6 },
                ]
            }]
        };

        $("#chartContainer").CanvasJSChart(options);
    });
});


$(document).ready(function(){
    //everytime app loads we should clear the localstorage
    localStorage.clear();

    //SignIn
    $("#signInBtn").click(function(e){
        e.preventDefault();
        var email = $('#email').val()
        var password = $('#password').val()
        var credentials = { email: email, password: password }
        console.log('credentials', credentials);

        $.ajax({
            url: "http://2f08bde21bd9.ngrok.io/mydiary/api/user/verify",
//            url: "http://127.0.0.1:8080/mydiary/api/user/verify",
            type: 'POST',
            contentType: "application/json",
            timeout: 10000,
            cache: false,
            data: JSON.stringify(credentials) ,
            success: function (response) {
                console.log('response', response)
                //save user credentials on success and go inside the app
                window.localStorage.setItem("email", email);
                window.localStorage.setItem("password", password);

                //clean credentials inputs after submitting
                $('#email').val('');
                $('#password').val('');

                $.mobile.changePage($('#listOfEventsPage'));
                $('#signedUserEmail').text(email)
                getEvents(credentials)
            },
            error: function(jqXHR, textStatus, errorThrown) {
                //alert that something wrong with credentials
                $('#signInPopup').popup();
                $('#signInPopup').popup("open");
                setTimeout(function(){  $("#signInPopup").popup("close"); }, 3000);
                console.log(JSON.stringify(jqXHR));
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    });

    function getEvents(credentials) {
        $.ajax({
            url: "http://2f08bde21bd9.ngrok.io/mydiary/api/event/get/all",
//        url: "http://127.0.0.1:8080/mydiary/api/event/get/all",
            type: 'POST',
            contentType: "application/json",
            timeout: 10000,
            cache: false,
            data: JSON.stringify(credentials) ,
            success: function (eventsFromServer) {
                console.log(eventsFromServer)
                if(eventsFromServer.length > 0) {
                    eventsFromServer.forEach(function(event) {
                        var eventHTML = '<div data-event-id="' + event.id + '" class="eventWrapper" style="padding: 35px 5px 15px 5px; margin-top: 10px;background-color: white;">' +
                            '<a data-event-id="' + event.id + '"  data-transition="pop" data-rel="dialog" class="updateEvent ui-icon-edit ui-btn-icon-notext" style="position: relative; left: 12px; top: -15px;"></a>' +
                            '<a data-event-id="' + event.id + '" class="deleteEvent ui-icon-delete ui-btn-icon-notext" style="position: relative; left: 40px; top: -15px"></a>' +
                            '<div class="event">' +
                            '<span class="dateEv">' + event.date + '</span> -  <span class="titleEv">' + event.title + '</span><br>' +
                            'time between: <span class="startsEv">' + event.starts + '</span>  <span class="endsEv">' + event.ends + '</span><br>' +
                            'notes: <span class="noteEv">' + event.note + '</span><br>' +
                            'at <span class="locationEv">' + event.location + '</span>' +
                            '</div>'+
                            '</div>'
                        $('#eventsContainer').prepend(eventHTML);
                    })
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                //alert that something wrong with credentials
                console.log(JSON.stringify(jqXHR));
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }

//SignUp
    $("#signUpBtn").click(function(e) {
        e.preventDefault();

        var email = $('#newEmail').val()
        var password = $('#newPassword').val()
        var verifyPassword = $('#verifyPassword').val()

        if(!email || !password || !verifyPassword) {
            $('#signUpPopup > p').text('Please fill all the fields');
            $("#signUpPopup").popup();
            $("#signUpPopup").popup("open")
            setTimeout(function(){  $("#signUpPopup").popup("close"); }, 3000);
            return;
        }

        if(password != verifyPassword) {
            $('#signUpPopup').find('p').text('Please enter correct Email/Password');
            $("#signUpPopup").popup();
            $("#signUpPopup").popup( "open" )
            setTimeout(function(){  $("#signUpPopup").popup("close"); }, 3000);
            return;
        }

        var credentials = { email: email, password: password }
        console.log('credentials', credentials);
        $.ajax({
            //http://31a4a300f223 -> should be changed according to ngrok while developing
            url: "http://2f08bde21bd9.ngrok.io/mydiary/api/user/new",
//        url: "http://127.0.0.1:8080/mydiary/api/user/new",
            type: 'POST',
            contentType: "application/json",
            timeout: 10000,
            cache: false,
            data: JSON.stringify(credentials) ,
            success: function (response) {
                console.log('response', response)
                //save user credentials on success and go inside the app to post events
                window.localStorage.setItem("email", email);
                window.localStorage.setItem("password", password);

                //clean credentials inputs after submitting
                $('#newEmail').val('');
                $('#newPassword').val('');
                $('#verifyPassword').val('');

                $.mobile.changePage($('#listOfEventsPage'));
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(JSON.stringify(jqXHR));
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    });


    //Events Section
    //Add Event
    $("#addNewEventBtn").click(function(e) {
        e.preventDefault();
        var email = window.localStorage.getItem("email");
        var password = window.localStorage.getItem("password");

        var title = $('#title').val()
        var location = $('#location').val()
        var timeStart = $('#timeStart').val() + ":00"
        var timeEnd = $('#timeEnd').val() + ":00"
        var date = $('#date').val()
        var note = $('#note').val()
        var id = 1;
        var event = [
            email, id, title,  location,  timeStart,  timeEnd,  date,  note
        ];


        var isRequiredFieldEmpty = false;
        $('.addEventSection').each(function(idx, addEventSectionField) {
            if($(this).val() == '') {
                isRequiredFieldEmpty = true
            }
        })

        //if one of the fields is empty, popup notification
        if(isRequiredFieldEmpty) {
            $('#addNewEventPopup').popup();
            $('#addNewEventPopup').popup("open");
            setTimeout(function(){  $("#addNewEventPopup").popup("close"); }, 3000);
            return;
        }

        var requestData = {
            email: email,
            password: password,
            event: event
        }

        $.ajax({
            url: "http://2f08bde21bd9.ngrok.io/mydiary/api/event/new",
//                url: "http://127.0.0.1:8080/mydiary/api/event/new",
            type: 'POST',
            contentType: "application/json",
            timeout: 3000,
            cache: false,
            data: JSON.stringify(requestData) ,
            success: function (eventIdFromServer) {
                cleanUpNewEventFields()
                var eventHTML = '<div data-event-id="' + eventIdFromServer + '" class="eventWrapper" style="padding: 35px 5px 15px 5px; margin-top: 10px;background-color: white;">' +
                    '<a data-event-id="' + eventIdFromServer + '" class="updateEvent ui-icon-edit ui-btn-icon-notext" style="position: relative; left: 12px; top: -15px;"></a>' +
                    '<a data-event-id="' + eventIdFromServer + '" class="deleteEvent ui-icon-delete ui-btn-icon-notext" style="position: relative; left: 40px; top: -15px"></a>' +
                    '<div class="event">' +
                    '<span class="dateEv">' + date + '</span> -  <span class="titleEv">' + title + '</span><br>' +
                    'time between: <span class="startsEv">' + starts + '</span>  <span class="endsEv">' + ends + '</span><br>' +
                    'notes: <span class="noteEv">' + note + '</span><br>' +
                    'at <span class="locationEv">' + location + '</span>' +
                    '</div>'+
                    '</div>'
                $('#eventsContainer').prepend(eventHTML);
                $.mobile.changePage($('#listOfEventsPage'));
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("Error: " + xhr.status + "\n" +
                    "Message: " + xhr.statusText + "\n" +
                    "Response: " + xhr.responseText + "\n" + thrownError);
            }
        });
    });

    function cleanUpNewEventFields() {
        $('#title').val('')
        $('#location').val('')
        $('#timeStart').val('')
        $('#timeEnd').val('')
        $('#date').val('')
        $('#note').val('')
    }



    //Update Event
    $(document).on('click', '.updateEvent', function() {
        var eventId = $(this).attr('data-event-id');
        var email = window.localStorage.getItem("email");
        var password = window.localStorage.getItem("password");

        if(eventId && email && password) {
            var requestData = { email: email, password: password, eventsId: [ eventId ] };
            getEventFromServerAndFillUpdatePage(requestData);
        }
        else {
//            alert somethings wrong
        }
    });

    function getEventFromServerAndFillUpdatePage(requestData) {
        $.ajax({
            url: "http://2f08bde21bd9.ngrok.io/mydiary/api/event/get",
//            url: "http://127.0.0.1:8080/mydiary/api/event/get",
            type: 'POST',
            contentType: "application/json",
            timeout: 10000,
            cache: false,
            data: JSON.stringify(requestData) ,
            success: function (eventsFromServer) {
                console.log(eventsFromServer)
                if(eventsFromServer.length > 0) {
                    $(document).on("pagebeforeshow","#updateEventPage", function(){
                        $('#updateTitle').val(eventsFromServer[0].title)
                        $('#updateLocation').val(eventsFromServer[0].location)
                        $('#updateTimeStart').val(convertAmPMTo24Hours(eventsFromServer[0].starts))
                        $('#updateTimeEnd').val(convertAmPMTo24Hours(eventsFromServer[0].ends))
                        $('#updateDate').val(convertDate(eventsFromServer[0].date))
                        $('#updateNote').val(eventsFromServer[0].note)
                        //also add event's id in order to get it on click
                        $('#updateEventBtn').attr('data-event-id', eventsFromServer[0].id)
                    });
                    $.mobile.changePage($('#updateEventPage'));
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                //alert that something wrong with credentials
                console.log(JSON.stringify(jqXHR));
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }

    $('#updateEventBtn').click(function(e) {
        e.preventDefault();
        var email = window.localStorage.getItem("email");
        var password = window.localStorage.getItem("password");

        var title = $('#updateTitle').val()
        var location = $('#updateLocation').val()
        var timeStart = $('#updateTimeStart').val() + ":00"
        var timeEnd = $('#updateTimeEnd').val() + ":00"
        var date = $('#updateDate').val()
        var note = $('#updateNote').val()
        var id = $(this).attr('data-event-id');
        var event = [
            email, id, title,  location,  timeStart,  timeEnd,  date,  note
        ];

        var isRequiredFieldEmpty = false;
        $('.updateEventSection').each(function(idx, addEventSectionField) {
            if($(this).val() == '') {
                isRequiredFieldEmpty = true
            }
        })

        //if one of the fields is empty, popup notification
        if(isRequiredFieldEmpty) {
            $('#updateEventPopup').popup();
            $('#updateEventPopup').popup("open");
            setTimeout(function(){  $("#updateEventPopup").popup("close"); }, 3000);
            return;
        }

        var requestData = {
            email: email,
            password: password,
            event: event
        }

        $.ajax({
            url: "http://2f08bde21bd9.ngrok.io/mydiary/api/event/update",
//                url: "http://127.0.0.1:8080/mydiary/api/event/update",
            type: 'POST',
            contentType: "application/json",
            timeout: 3000,
            cache: false,
            data: JSON.stringify(requestData) ,
            success: function (eventIdFromServer) {
                cleanUpUpdateEventPageFields();
                $.mobile.changePage($('#listOfEventsPage'));

                $(document).on("pageshow","#listOfEventsPage", function(){
                    $('.eventWrapper[data-event-id="' + id  + '"]').find('.dateEv').text(date)
                    $('.eventWrapper[data-event-id="' + id  + '"]').find('.titleEv').text(title)
                    $('.eventWrapper[data-event-id="' + id  + '"]').find('.startsEv').text(timeStart)
                    $('.eventWrapper[data-event-id="' + id  + '"]').find('.endsEv').text(timeEnd)
                    $('.eventWrapper[data-event-id="' + id  + '"]').find('.noteEv').text(note)
                    $('.eventWrapper[data-event-id="' + id  + '"]').find('.locationEv').text(location)
                });
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("Error: " + xhr.status + "\n" +
                    "Message: " + xhr.statusText + "\n" +
                    "Response: " + xhr.responseText + "\n" + thrownError);
            }
        });
    })

    function cleanUpUpdateEventPageFields() {
        $('#updateTitle').val('')
        $('#updateLocation').val('')
        $('#updateTimeStart').val('')
        $('#updateTimeEnd').val('')
        $('#updateDate').val('')
        $('#updateNote').val('')
    }

    function convertAmPMTo24Hours(time) {
        var hours = Number(time.match(/^(\d+)/)[1]);
        var minutes = Number(time.match(/:(\d+)/)[1]);
        var AMPM = time.match(/\s(.*)$/)[1];
        if (AMPM == "pm" && hours < 12) hours = hours + 12;
        if (AMPM == "am" && hours == 12) hours = hours - 12;
        var sHours = hours.toString();
        var sMinutes = minutes.toString();
        if (hours < 10) sHours = "0" + sHours;
        if (minutes < 10) sMinutes = "0" + sMinutes;
        return (sHours +':'+sMinutes);
    }

    function convertDate(date) {
        // create array of values returned by `.toLocaleDateString()`,
        // delimited by `/`
        console.log('1', date);

        var milliseconds = new Date(date).valueOf();
        var d =  new Date(milliseconds).toLocaleDateString('en-IL').split("/");

        // `y` : year
        var y = d.splice(-1)[0];
        // set `y` as item at index `0` of `d`
        d.splice(0, 0, y);
        // join items within `d` with dash character `"-"`
        var date = d.join("-");
        console.log('2', date);
        return date
    }

    //Delete Event
    $(document).on('click', '.deleteEvent', function() {
        var eventId = $(this).attr('data-event-id');
        var email = window.localStorage.getItem("email");
        var password = window.localStorage.getItem("password");

        if(eventId && email && password) {
            var requestData = {
                email: email,
                password: password,
                eventsId: [ eventId ]
            }
            deleteEvent(eventId, requestData)
        }
    });

    function deleteEvent(eventId, requestData) {
        $.ajax({
            url: "http://2f08bde21bd9.ngrok.io/mydiary/api/event/delete",
//            url: "http://127.0.0.1:8080/mydiary/api/event/delete",
            type: 'POST',
            contentType: "application/json",
            timeout: 3000,
            cache: false,
            data: JSON.stringify(requestData) ,
            success: function (response) {
                $(".eventWrapper[data-event-id=" + eventId + "]").remove();
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("Error: " + xhr.status + "\n" +
                    "Message: " + xhr.statusText + "\n" +
                    "Response: " + xhr.responseText + "\n" + thrownError);
            }
        });
    }

    $("#showGraphBtn").click(function(e) {
        e.preventDefault()
        getDataForStatisticsByLocation()
    })

    $("#signOutBtn").click(function(e) {
        e.preventDefault()
        signOutUser()
    })

    function signOutUser() {
        //when user is redirected to entryPage his/her credentials will be nullified on the pagebeforeshow hook
        $.mobile.changePage($('#entryPage'));
    }

    function getEventsForStatisticsFromServer(callback) {
        var email = window.localStorage.getItem("email");
        var password = window.localStorage.getItem("password");
        var credentials = { email: email, password: password }

        $.ajax({
            url: "http://2f08bde21bd9.ngrok.io/mydiary/api/event/get/all",
//            url: "http://127.0.0.1:8080/mydiary/api/event/get/all",
            type: 'POST',
            contentType: "application/json",
            timeout: 10000,
            cache: false,
            data: JSON.stringify(credentials) ,
            success: function (eventsFromServer) {
                callback(eventsFromServer)
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(JSON.stringify(jqXHR));
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }

    var statisticsByLocation = {};

    function getDataForStatisticsByLocation() {
        //events from db
        var email = window.localStorage.getItem("email");
        var password = window.localStorage.getItem("password");
        var credentials = { email: email, password: password }

        getEventsForStatisticsFromServer(function(events){
            if(events.length > 0) {

                for(var idx = 0; idx < events.length; idx++) {
                    var event = events[idx];

                    if(!statisticsByLocation[event.location]) {
                        statisticsByLocation[event.location] = 1
                    }
                    else {
                        statisticsByLocation[event.location]++
                    }
                }
            }
            else {
                console.log('no data for statistics')
            }
            $.mobile.changePage($('#graphPage'));
            initializeGraph()
        })
    }

    function initializeGraph() {
        var statisticsData = [];

        Object.keys(statisticsByLocation).forEach(function (locationCity) {
            var number = statisticsByLocation[locationCity];
            statisticsData.push({ label: locationCity, y: number})
        });

        //clear global data
        statisticsByLocation = {}
        console.log('statisticsByLocation >>>>', statisticsData)

        var options = {
            title: {
                text: "Events by Location"
            },
            titleWrap: true,
            dataPointWidth: 10,
            axisX:{
                viewportMinimum: -50,
                viewportMaximum: 50
            },
            width: 340,
            backgroundColor: "#F5DEB3", //todo change color
            toolTip:{
                enabled: true,
            },
            data: [
                {
                    type: "doughnut",
//                    startAngle: 60,
                    innerRadius: 60,
                    indexLabelFontSize: 10,
                    indexLabel: "{label} - #percent%",
                    toolTipContent: "<b>{label}:</b> {y} (#percent%)",
                    dataPoints: statisticsData
                }
            ]
        };

        $("#chartContainer").CanvasJSChart(options);
    }



    //Graph
    $(function () {
        var options = {
            title: {
                text: "Events by Location"
            },
            data: [{
                type: "pie",
                startAngle: 45,
                showInLegend: false,
                legendText: "{label}",
                indexLabel: "{label} ({y})",
                yValueFormatString:"#,##0.#"%"",
                dataPoints: [
                    { label: "Tel Aviv", y: 36 },
                    { label: "Jerusalem", y: 31 },
                    { label: "Tizinabi", y: 7 },
                    { label: "Twitter", y: 7 },
                    { label: "Facebook", y: 6 },
                ]
            }]
        };

        $("#chartContainer").CanvasJSChart(options);
    });



});