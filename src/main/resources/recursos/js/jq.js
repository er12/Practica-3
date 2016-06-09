$(document).ready(function()
{
    var myDiv = $('#parrafoEsp');
    if(myDiv.text().length>70)
        myDiv.text(myDiv.text().substring(0,70)+ "...");

});

// hide logout button initially
$(document).ready('pagebeforeshow', function () {
    $('#button_logout').toggle();
});

// hide login and show logout
$(document).on('click', '#button_login', function () {
    $('#button_logout').addClass('ui-btn-right').toggle();
    $(this).removeClass('ui-btn-right').toggle();
});

// hide logout and show login
$(document).on('click', '#button_logout', function () {
    $('#button_login').addClass('ui-btn-right').toggle();
    $(this).removeClass('ui-btn-right').toggle();
});