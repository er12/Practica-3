$(document).ready(function()
{
    var myDiv = $('#parrafoEsp');
    if(myDiv.text().length>70)
        myDiv.text(myDiv.text().substring(0,70)+ "...");
    });  
     
