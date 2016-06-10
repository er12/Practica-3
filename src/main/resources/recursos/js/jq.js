$(document).ready(function()
{
    $( '#parrafoEsp' ).each(function() {
        if($(this).text().length>70)
            myDiv.text(myDiv.text().substring(0,70)+ "...");
    });

    });  
     
