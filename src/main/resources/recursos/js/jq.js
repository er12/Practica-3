$(document).ready(function()
{
    $('.parrafoEsp').each(function() {
        if($(this).text().length>70)
            $(this).text($(this).text().substring(0,70)+ "...");
    });

    });  
     
