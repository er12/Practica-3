$(document).ready(function()
{
    var myDiv = $('#parrafoEsp');
    if(myDiv.text().length>70)
        myDiv.text(myDiv.text().substring(0,70)+ "...");

    $('#button_login').click(function() {
        if ($(this).hasClass('show')) {
            $(this).removeClass('show');
            $(this).addClass('hide');
            $('#button_logout').removeClass('hide');
            $('#button_logout').addClass('show');

        }
    });   
    
            $('#button_logout').click(function(){
                if ($(this).hasClass('show')) {
                    $(this).removeClass('show');
                    $(this).addClass('hide');
                    $('#button_login').removeClass('hide');
                    $('#button_login').addClass('show');

                }  
               
        }); 

    });  
     



// hide logout button initially
/*$('#button_logout').hide();

$('#button_login').click(function() {
    $(this).hide();
    $('#button_logout').show();

});

$('#button_logout').click(function() {
    $(this).hide();
    $('#button_login').show();

});*/