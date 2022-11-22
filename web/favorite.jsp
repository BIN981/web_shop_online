<%-- 
    Document   : favo
    Created on : May 22, 2022, 12:42:03 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>My Favorite</title>
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
            href="css/cart.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/notification.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/footer.css"
            />

        <!-- font awesome -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
            integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />

        <!-- Slick slider -->
        <link
            rel="stylesheet"
            type="text/css"
            href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"
            />

        <!-- jQuery -->
        <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"
        ></script>
    </head>
    <body>
        <!-- navbar -->
        <%@ include file="navbar.jsp" %>
        <div id="notification-box"></div>
        <div class="cart">
            <h1 class="cart__title">My Favorite</h1>
            <ul class="cart__list">
                <c:forEach items="${favoriteProducts}" var="item">
                    <li class="cart__item">
                        <span class="item__remove">
                            <a onclick="RemoveFavoriteItem(${item.id})">
                                <i class="fa-solid fa-circle-xmark"></i> 
                            </a>
                        </span>
                        <img
                            src="${item.image}"
                            class="item__image"
                            />
                        <div class="item__detail">
                            <h2 class="item__name">${item.name}</h2>
                            <span class="item__price">${item.price}
                            </span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <%@include file="footer.jsp" %>
        <script src="js/favorite.js"></script>
    </body>
</html>
