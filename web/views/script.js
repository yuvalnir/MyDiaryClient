//$(document).on("mobileinit", function() {
//    $.mobile.allowCrossDomainPages = true;
//    $.support.cors = true;
//});


$(document).ready(function(){

//everytime app loads we should clear the localstorage
localStorage.clear();
//SignUp
    $("#signUpBtn").click(function(e) {
//    e.preventDefault(); // prevent actual form submit
//    e.stopPropagation();
    var firstName = $('#firstName').val()
    var lastName = $('#lastName').val()
    var email = $('#newEmail').val()
    var password = $('#newPassword').val()
    var verifyPassword = $('#verifyPassword').val()

    if(password != verifyPassword) {
//        alert('Passwords doesnt match!');
//        $( "#popupBasic" ).popup();
        $("#popupBasic").popup( "open" )
        return;
    }

    var requestData = {
        email: email,
        password: password,
        firstName: firstName,
        lastName: lastName
     }
    var requestMethod = 'POST'
    var endpoint = 'newuser';



//    sendDataToServer(requestData, requestMethod, endpoint, function (callback) {
//        console.log('sign up callback ', callback);
//        //do stuff render welcome screen
//    });

//    $.ajax({
////            url: "http://127.0.0.1:8080/MyDiary/user/newuser",
////http://31a4a300f223 -> should be changed according to ngrok while developing
//        url: "http://31a4a300f223.ngrok.io/MyDiary/user/newuser",
//        type: "POST",
//        data: JSON.stringify(requestData),
//        contentType: "application/json",
//        dataType: "json",
//        timeout: 3000,
//        cache: false,
//        success: function (response) {
//               console.log('done>>>>>>>> . >>>>>>>response', response)
//           // You will get response from your PHP page (what you echo or print)
//        },
//        error: function(jqXHR, textStatus, errorThrown) {
//            console.log(JSON.stringify({ email: email, password: password }))
//           console.log(JSON.stringify(jqXHR));
//           console.log(textStatus);
//           console.log(errorThrown);
//
//        }
//    });
});

    //SignIn
    $("#signInBtn").click(function(e){
        e.preventDefault();
        var email = $('#email').val()
        var password = $('#password').val()

        console.log(email);
        console.log(password)
        var credentials = { email: email, password: password }

//        window.localStorage.setItem("email", email);
//        window.localStorage.setItem("password", password);

        $('#email').val('');
        $('#password').val('');

        $.mobile.changePage($('#mainPage'));

        // getting the value
//        var username = window.localStorage.getItem("username");

//        $.cookie('auth', {email: email, password: password}, { path: '/' });

        $.ajax({
            url: "http://70014fbef637.ngrok.io/MyDiary/user/verifyuser",
            type: 'POST',
            contentType: "application/json",
//            contentType: "application/json; charset=utf-8",
//            dataType: "json",
            timeout: 10000,
            cache: false,
            data: JSON.stringify(credentials) ,
            success: function (response) {
                console.log('response', response)
//                $.cookie('auth', {email: email, password: password}, { path: '/' });
                window.localStorage.setItem("email", email);
                window.localStorage.setItem("password", password);
                $.mobile.changePage($('#mainPage'));
               // You will get response from your PHP page (what you echo or print)
            },
            error: function(jqXHR, textStatus, errorThrown) {
               console.log(JSON.stringify(jqXHR));
               console.log(textStatus);
               console.log(errorThrown);
            }
        });


//        sendDataToServer(requestData, requestMethod, endpoint, function (callback) {
            // after successfully signing in, user credentials should be stored somewhere in order to save new events
//            $.cookie('auth', {email: email, password: password}, { path: '/' });
//            console.log('sign in callback', callback);
            //do stuff render welcome screen
//        });
    });


    //requestData: (must be an OBJECT) userdata you wish to path to the server-> email, password, etc.
    //method: newuser,verifyuser,newevent,deleteevent,deleteevents,getevent,getevents,updateevent,
    //requestType: 'POST', 'GET'
    function sendDataToServer(requestData, requestMethod, endpoint) {
//        var baseUrl = "http://31a4a300f223.ngrok.io/MyDiary/user/";
        var baseUrl = "http://127.0.0.1:8080/MyDiary/user/";
        var url = baseUrl + endpoint

          $.ajax({
                url: url,
                type: requestMethod,
                contentType: "application/json",
                dataType: "json",
                timeout: 3000,
                cache: false,
                data: JSON.stringify(requestData) ,
                success: function (response) {
                     console.log('done')

                   // You will get response from your PHP page (what you echo or print)
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert("Error: " + xhr.status + "\n" +
                           "Message: " + xhr.statusText + "\n" +
                           "Response: " + xhr.responseText + "\n" + thrownError);
                }
            });
    }




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

        $('#calendarData').prepend('<p>'+ date + ' | ' + title + '</p>');


          $.ajax({
                url: "http://70014fbef637.ngrok.io/MyDiary/user/newevent",
                type: 'POST',
                contentType: "application/json",
//                dataType: "json",
                timeout: 3000,
                cache: false,
                data: JSON.stringify(requestData) ,
                success: function (response) {
                     console.log('done')
                     //build new event
                    $('#calendarData').prepend('<div>YESSS</div>')
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log("Error: " + xhr.status + "\n" +
                           "Message: " + xhr.statusText + "\n" +
                           "Response: " + xhr.responseText + "\n" + thrownError);
                }
            });

//        sendDataToServer(requestData, requestMethod, endpoint, function (callback) {
//            console.log('event success callback ', callback);
//            //add event dynamically to the screen
//        });
    });













});
