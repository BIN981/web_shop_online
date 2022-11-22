window.onbeforeunload = function () {
    localStorage.setItem("order_at_from", $('#order_at_from').val());
    localStorage.setItem("order_at_to", $('#order_at_to').val());
    localStorage.setItem("customer_name", $('#customer_name').val());
    localStorage.setItem("total_cost_from", $('#total_cost_from').val());
    localStorage.setItem("total_cost_to", $('#total_cost_to').val());
    localStorage.setItem("order_status", $('#order_status').val());
}

window.onload = function() {
   if (localStorage.getItem("order_at_from") !== null) $('#order_at_from').val(localStorage.getItem("order_at_from")); 
   if (localStorage.getItem("order_at_to") !== null) $('#order_at_to').val(localStorage.getItem("order_at_to"));
   if (localStorage.getItem("customer_name") !== null) $('#customer_name').val(localStorage.getItem("customer_name"));
   if (localStorage.getItem("total_cost_from") !== null) $('#total_cost_from').val(localStorage.getItem("total_cost_from"));
   if (localStorage.getItem("total_cost_to") !== null) $('#total_cost_to').val(localStorage.getItem("total_cost_to"));
   if (localStorage.getItem("order_status") !== null) $('#order_status').val(localStorage.getItem("order_status"));
}


