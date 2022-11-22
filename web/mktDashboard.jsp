<%-- 
    Document   : mktDashboard
    Created on : 09-Jun-2022, 14:59:55
    Author     : BinhNH981
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>MKT Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="./css/home-page.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="./css/MKT-dashboard.css"
            />
        <!-- font awesomw -->
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
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <div class="wrapper" style="padding-bottom: 50px;text-align: center;">
            <h2 class="wrapper-title" style="text-align: center;">MKT Dashboard</h2>
            <span>Reulst in: </span>
            <form action="MktDashboard" method="post" style="text-align: center;">
                <input type="date" name="from" id="from" required>
                <span> - </span>
                <input type="date" name="to" id="to" required>
                <button type="submit">Show</button>
                <button> <a href="home">Back Home</a></button>
            </form>
            <div style="margin-top: 150px;text-align: center;">
                <div style="width: 500px;">
                    <h3 style="text-align: center;">Posted</h3>
                    <div class="sale-orders">Posted: ${posted}</div>
                    <c:forEach var="items" items="${listCategory}">
                        <div class="sale-orders"> <div>${items.name}:${items.amount} </div></div>
                    </c:forEach>
                </div>
                <div style="width: 500px;">
                    <h3 style="text-align: center;">Product</h3>
                    <div class="sale-orders">Active: ${countAcitve}</div>
                    <div class="sale-orders">
                        <c:forEach var="itemsProduct" items="${listCategoryProduct}">
                            <div class="sale-orders">
                                <div>${itemsProduct.name} : ${itemsProduct.amount} </div>
                            </div> 
                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>
        <%@include file="footer.jsp" %>
        <script src="js/sale-Dashboard.js"></script>
    </body>
</html>