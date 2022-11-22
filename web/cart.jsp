<%-- 
    Document   : cart
    Created on : May 22, 2022, 12:42:03 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>My Cart Detail</title>
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

        <div class="cart">
            <h1 class="cart__title">My Cart</h1>
            <ul class="cart__list">
                <c:forEach items="${cartProducts}" var="item">
                    <li class="cart__item">
                        <span class="item__remove">
                            <a href="#" onclick="Remove(${item.id})">
                                <i class="fa-solid fa-circle-xmark"></i> 
                            </a>
                        </span>
                        <img
                            src="${item.product.image}"
                            class="item__image"
                            />
                        <div class="item__detail">
                            <h2 class="item__name">${item.product.name}</h2>
                            <h3 class="item__size">Size: ${item.size}</h3>
                            <span class="item__price">$
                                <c:if test="${item.product.salePrice != 0}">${item.product.salePrice}</c:if>
                                <c:if test="${item.product.salePrice == 0}">${item.product.price}</c:if>
                                </span>
                            </div>
                            <div class="item__quantity">
                                <button class="item__quantity-action decrease" onclick="Decrease(${item.id})">-</button>
                            <input type="number" class="quantity" value="${item.quantity}" min="0" />
                            <button class="item__quantity-action increase" onclick="Increase(${item.id})">+</button>
                        </div>
                        <div class="item__select">
                            <input type="checkbox" name="select_to_buy" id="select" class="select-item" 
                                   onclick="SelectItem(${item.productSizeId}, ${item.size}, ${item.quantity},
                                   ${item.product.salePrice == 0} ? ${item.product.price} : ${item.product.salePrice},
                                                   this.checked ? true : false)" />
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <span class="cart__buy" onclick="BuyNow()">
                Check out
            </span>
        </div>

        <%@include file="footer.jsp" %>
        <script src="js/cart.js"></script>
    </body>
</html>
