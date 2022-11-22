window.onbeforeunload = function () {
    localStorage.setItem("from", $('#from').val());
    localStorage.setItem("to", $('#to').val());
    
}

window.onload = function() {
   if (localStorage.getItem("from") !== null) $('#from').val(localStorage.getItem("from")); 
   if (localStorage.getItem("to") !== null) $('#to').val(localStorage.getItem("to"));
   
}


