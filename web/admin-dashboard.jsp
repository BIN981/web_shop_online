<%-- 
    Document   : admin-dashboard
    Created on : Jul 7, 2022, 5:44:00 PM
    Author     : ChauDM
--%>

<%@page import="service.impl.AdminServiceImpl"%>
<%@page import="service.AdminService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>ADMIN Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/home-page.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/admin-dashboard.css"
            />
        <!-- font awesome -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
            integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <!-- jQuery -->
        <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
        ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/canvasjs/1.7.0/canvasjs.min.js" 
                integrity="sha512-FJ2OYvUIXUqCcPf1stu+oTBlhn54W0UisZB/TNrZaVMHHhYvLBV9jMbvJYtvDe5x/WVaoXZ6KB+Uqe5hT2vlyA==" 
                crossorigin="anonymous" referrerpolicy="no-referrer"
        ></script>

        <script type="text/javascript">

        </script>
    </head>
    <body>
        <!-- navbar -->
        <%@ include file="navbar.jsp" %>
        
        <div class="wrapper">
            <h2 class="title">
                ADMIN Dashboard <i class="fa-solid fa-chart-pie"></i>
            </h2>
            <ul class="navigation">
                <li class="nav-item active" id="monthly-revenue">
                    <i class="fa-solid fa-sack-dollar"></i>Monthly Revenue
                </li>
                <li class="nav-item" id="daily-revenue">
                    <i class="fa-solid fa-sack-dollar"></i>Daily Revenue
                </li>
                <li class="nav-item" id="monthly-order">
                    <i class="fa-solid fa-file-waveform"></i>Monthly Orders
                </li>
                <li class="nav-item" id="daily-order">
                    <i class="fa-solid fa-file-waveform"></i>Daily Orders
                </li>
                <li class="nav-item" id="daily-order">
                    <i class="fa-solid fa-file-waveform"></i>Revenue by category
                </li>
                <li class="nav-item" id="daily-order">
                    <i class="fa-solid fa-file-waveform"></i>Quantity by category
                </li>
            </ul>
            <div class="detail">
                <form class="filter" action="#" method="get">
                    <h2>Filter By</h2>
                    <div class="input">
                        <span>Date</span>
                        <input type="date" name="from" id="from" />
                        <label>-</label>
                        <input type="date" name="to" id="to" />
                        <span class="submit" onclick="GetStatistic()">Show</span>
                    </div>
                    <!--          <div class="input">
                            <span>Status</span>
                    <select name="status" id="status">
                    <option value="ALL">ALL</option>
          <option value="SUBMITTED">SUBMITTED</option>
          <option value="CONFIRMED">CONFIRMED</option>
          <option value="COMPLETED">COMPLETED</option>
          <option value="CANCELED">CANCELED</option>
          </select>
          </div>-->

                </form>
                <div class="result">
                    <h2>Chart</h2>
                    <div class="chart" id="chart"></div>
                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        
        <script src="js/admin-dashboard.js"></script>
    </body>
</html>

