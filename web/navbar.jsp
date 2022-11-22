<%-- 
    Document   : navbar
    Created on : May 16, 2022, 9:47:18 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/navbar.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/slider.css"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
            integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
    </head>
    <body>
        <!-- menu desktop -->
        <div class="card__menu-desktop">
            <div class="card__menu--logo-desktop">
                <img src="image/logo.png" alt="logo" />
            </div>
            <ul class="card__menu--list-desktop">
                <li class="card__menu--item-desktop">
                    <span><a href="home">Home</a></span>
                    <i class="fa-solid fa-house"></i>
                </li>
                <li class="card__menu--item-desktop category">
                    <span> Category</span>
                    <i class="fa-solid fa-layer-group"></i>
                    <ul class="menu__category hide">
                        <c:forEach items="${product_categories}" var="pc">
                            <li class="menu__category-item"><a href="home?productCategoryId=${pc.id}">${pc.name}</a></li>
                            </c:forEach>

                    </ul>
                </li>
                <li class="card__menu--item-desktop">
                    <span><a href="blogListController">Blogs</a> </span>
                    <i class="fa-solid fa-newspaper"></i>
                </li>
                <li class="card__menu--item-desktop">
                    <span>Contact</span>
                    <i class="fa-brands fa-discourse"></i>
                </li>
            </ul>

        </div>



        <!-- menu tablet -->
        <div class="card__menu-tablet">
            <div class="card__menu--icon-tablet">
                <i class="fa-solid fa-bars"></i>
            </div>
            <ul class="card__menu--list-tablet hide">
                <li class="card__menu--item-tablet">
                    <i class="fa-solid fa-house"></i>
                    <span>Home</span>
                </li>
                <li class="card__menu--item-tablet">
                    <i class="fa-solid fa-layer-group"></i>
                    <span>Category</span>
                </li>
                <li class="card__menu--item-tablet">
                    <i class="fa-solid fa-newspaper"></i>
                    <span > Blog </span>
                </li>
                <li class="card__menu--item-tablet">
                    <i class="fa-brands fa-discourse"></i>
                    <span>Contact</span>
                </li>
            </ul>
        </div>

        <!-- card user common -->
        <div class="card__user">
            <div class="card__user--icon">
                <c:if test="${sessionScope.user.image == null}">
                    <i class="fa-solid fa-user"></i>
                </c:if>
                <c:if test="${sessionScope.user.image != null}">
                    <img src="data:image/jpg;base64,${sessionScope.user.image}" alt="">
                </c:if>
            </div>

            <ul class="card__user--list hide">
                <c:if test="${sessionScope.account == null}">
                    <li class="card__user--item">
                        <a href="loginController">
                            <span>Login</span>
                            <i class="fa-solid fa-arrow-right-to-bracket"></i> 
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account.getRoleId() == 1}">
                    <%@include file="menuAdmin.jsp" %>
                </c:if>
                <c:if test="${sessionScope.account.getRoleId() == 2}">
                    <%@include file="menuMkt.jsp" %>
                </c:if>
                <c:if test="${sessionScope.account.getRoleId() == 3}">
                    <%@include file="menuSale.jsp" %>
                </c:if>
                <c:if test="${sessionScope.account.getRoleId() == 4}">
                    <%@include file="menuCustomer.jsp" %>
                </c:if>
                <c:if test="${sessionScope.account.getRoleId() == 5}">
                    <%@include file="menuMktManager.jsp" %>
                </c:if>    
            </ul>
        </div>

        <c:if test="${sessionScope.account == null || sessionScope.account.roleId == 4}">
                  
            <!-- cart -->
            <div class="card__cart">
                <a class="card__cart-icon" href="cart">
                    <i class="fa-solid fa-cart-shopping"></i>
                </a>
                <c:if test="${counterCartItem != null}">
                    <span class="card__cart-counter">${counterCartItem}</span>
                </c:if>

            </div>
        </c:if>

        <!-- search box -->
        <form class="card__search" action="home" method="get">
            <input type="text" name="search" title="search box" placeholder="Something..." />
            <button type="submit">
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>

        </form>

        <!-- mobile -->
        <div class="nav-mobile">
            <div class="card__menu-mobile">
                <i class="fa-solid fa-bars"></i>
            </div>
            <div class="card__user-mobile">
                <div class="card__cart-mobile">
                    <div class="card__cart-icon-mobile">
                        <i class="fa-solid fa-cart-shopping"></i>
                    </div>
                    <span class="card__cart-counter-mobile">1</span>
                </div>
                <c:if test="${sessionScope.user.image == null}">
                    <i class="fa-solid fa-user"></i>
                </c:if>
                <c:if test="${sessionScope.user.image != null}">
                    <img src="data:image/jpg;base64,${sessionScope.user.image}" alt="">
                </c:if>
            </div>
        </div>
        <script src="js/navbar.js"></script>
    </body>
</html>

