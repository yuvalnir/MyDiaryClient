$(document).on("pagebeforeshow","#entryPage", function(){
    localStorage.clear();
    $('#eventsContainer').html('');
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
        //for testing
//                window.localStorage.setItem("email", 'vahababen@gmail.com');
//                window.localStorage.setItem("password", 'a1b2');
//                $.mobile.changePage($('#listOfEventsPage'));
//                getEvents(credentials)

        $.ajax({
            url: "http://ea559dffb1ae.ngrok.io/MyDiary/user/verifyuser",
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
            url: "http://ea559dffb1ae.ngrok.io/MyDiary/user/getevents",
            type: 'POST',
            contentType: "application/json",
            timeout: 10000,
            cache: false,
            data: JSON.stringify(credentials) ,
            success: function (eventsFromServer) {
                console.log(eventsFromServer)
                eventsFromServer.forEach(function(event) {
                    var eventHTML = '<div class="event" style="padding: 10px; margin-top: 10px; background-color: white;">'
                        + event.date + '  ' + event.title + '  ' + event.starts + '  ' + event.ends + '  ' + event.note + '  ' + event.location +
                        '</div>'
                    $('#eventsContainer').prepend(eventHTML);
                })

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
            // url: "http://127.0.0.1:8080/MyDiary/user/newuser",
            //http://31a4a300f223 -> should be changed according to ngrok while developing
            url: "http://ea559dffb1ae.ngrok.io/MyDiary/user/newuser",
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

    //Events
    //add event
    $("#addNewEventBtn").click(function(e) {
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

        var requestData = {
            email: email,
            password: password,
            event: event
        }

        $.ajax({
            url: "http://ea559dffb1ae.ngrok.io/MyDiary/user/newevent",
            type: 'POST',
            contentType: "application/json",
            timeout: 3000,
            cache: false,
            data: JSON.stringify(requestData) ,
            success: function (response) {
                var eventHTML = '<div class="event" style="padding: 10px; margin-top: 10px; background-color: white;">'
                    + date + '  ' + title + '  ' + timeStart + '  ' + timeEnd + '  ' + note + '  ' + location +
                    '</div>'
                $('#eventsContainer').prepend(eventHTML);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("Error: " + xhr.status + "\n" +
                    "Message: " + xhr.statusText + "\n" +
                    "Response: " + xhr.responseText + "\n" + thrownError);
            }
        });
    });

});