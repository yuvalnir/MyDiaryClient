//$(document).on("mobileinit", function() {
//    $.mobile.allowCrossDomainPages = true;
//    $.support.cors = true;
//});

//$(document).on("pagecreate","#entryPage", function(){
//    $("#signInPopup").on("click", function(){
//        $("#signInPopup").popup("open");
//        setTimeout(function(){  $("#signInPopup").popup("close"); }, 3000);
//    });
//});

//used to imitate signout user
$(document).on("pagebeforeshow","#entryPage", function(){
//clear credentials with personal user data
    localStorage.clear();
    $('#signedUserEmail').text('');
    $('#eventsContainer').html('');
});


$(document).on("pagehide","#updateEventPage", function(){
    $('#updateTitle').val('')
    $('#updateLocation').val('')
    $('#updateTimeStart').val('')
    $('#updateTimeEnd').val('')
    $('#updateDate').val('')
    $('#updateNote').val('')
});
