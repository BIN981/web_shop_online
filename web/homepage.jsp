<%-- 
    Document   : homepage
    Created on : May 16, 2022, 9:46:57 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Sneaker Zone</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!--logo-->
        <link rel="icon" href="image/logo.png">

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
            href="css/post.css"
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
            href="css/product.css"
            />

        <!-- font awesomw -->
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

        <!-- slider carousel -->
        <div class="card__slider">
            <c:forEach items="${sliders}" var="slider">
                <div class="slider__item">
                    <a class="slider__image" href="slider?sliderId=${slider.id}">
                        <img
                            src="${slider.image}"
                            alt=""
                            />
                    </a>
                </div>
            </c:forEach>
        </div>

        <div id="modal"></div>

        <div class="product__wrapper">
            <!-- title -->
            <span class="products__title">All Products</span>


            <c:forEach items="${products}" var="p">
                <div class="product__item">
                    <c:if test="${p.percentage != 0}">
                        <span class="product_sale">${p.percentage}%</span>
                    </c:if>
                    <c:if test="${sessionScope.account == null || sessionScope.account.roleId == 4}">
                        <a class="product__heart" onclick="AddToFavorite(${p.id})"><i class="fa-solid fa-heart"></i></a>
                        <span class="product__add-to-cart" onclick="SelectSize(${p.id})">
                            <i class="fa-solid fa-cart-plus"></i>
                        </span>
                    </c:if>
                    <img
                        src="${p.image}"
                        alt="product_image"
                        />
                    <div class="product__detail">
                        <div class="product__name">
                            <a href="productDetail?productId=${p.id}" title='view-product'> ${p.name} </a>
                        </div>
                        <div class="product__code">
                            <span>Code:</span>
                            ${p.code}
                        </div>
                        <div class="product__price">
                            <c:if test="${p.salePrice != 0.00}">
                                <div class="product__price-origin">
                                    <span>$</span>
                                    <span>${p.price}</span>
                                </div>
                                <div class="product__price-sale">
                                    <span>$</span>
                                    <span>${p.salePrice}</span>
                                </div>
                            </c:if>
                            <c:if test="${p.salePrice == 0.00}">
                                <div class="product__price-sale">
                                    <span>$</span>
                                    <span>${p.price}</span>
                                </div>
                            </c:if>

                        </div>
                        <div class="product__count-buyed">
                            <span class="counter">123</span>
                            <span>Sold</span>
                        </div>
                        <c:if test="${sessionScope.account == null || sessionScope.account.roleId == 4}">
                            <button class="buy-now" type="submit">
                                <i class="fa-solid fa-cart-plus"></i>
                                Buy now
                            </button>                    
                        </c:if>
                    </div>
                </div>
            </c:forEach>

            <!-- Pagination -->
            <div class="pagination">
                <c:if test="${page > 1}">
                    <c:forEach begin="1" end="${page}" var="i">
                        <a class="page <c:if test="${current_page == i}">active</c:if>" href="home?page=${i}<c:if test="${search != null}">&search=${search}</c:if>">${i}</a>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div class="posts">
            <span class="posts_title">News Posts</span>

            <c:forEach items="${newestBlogs}" var="blog">
                <div class="post">
                    <img src="${blog.image}" alt="">
                    <a href="blogDetail?blogId=${blog.id}">
                        <h2 class="post__title">${blog.title}</h2>
                    </a>
                    <div class="post__date">${blog.publishAt}</div>
                    <div class="post__description">
                        ${blog.shortDes()}
                    </div>
                </div>
            </c:forEach>

        </div>

        <%@include file="footer.jsp" %>
        <!-- import slick slider -->
        <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-1.11.0.min.js"
        ></script>
        <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"
        ></script>
        <script
            type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"
        ></script>

        <script src="js/slick-slider.js"></script>
        <script src="js/modal-product.js"></script>
        <script src="js/product-detail.js"></script>
    </body>
</html>
