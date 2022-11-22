<%-- 
    Document   : products
    Created on : May 24, 2022, 10:00:05 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Home page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/product.css"
            />

        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/home-page.css"
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
        <%@include file="navbar.jsp" %>
        <div class="product__wrapper">
            <!-- title -->
            <span class="products__title">All Products</span>

            <div class="product__sider">
                <h2>Category - Brand</h2>
                <ul class="product__category">
                    <li class="product__category-item">
                        <a href="#">
                            Category Item
                        </a> 
                    </li>
                    <li class="product__category-item">
                        <a href="#">
                            Category Item
                        </a> 
                    </li>
                    <li class="product__category-item">
                        <a href="#">
                            Category Item
                        </a> 
                    </li>
                    <li class="product__category-item">
                        <a href="#">
                            Category Item
                        </a> 
                    </li>

                </ul>
            </div>

            <div class="product__list">

                <div class="product__item">
                    <a class="product__heart"><i class="fa-solid fa-heart"></i></a>
                    <a class="product__add-to-cart">
                        <i class="fa-solid fa-cart-plus"></i>
                    </a>
                    <img
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjesbnxyev3cRpf96DeDaWFrrPL2vabMiYiA&usqp=CAU"
                        alt="product_image"
                        />
                    <div class="product__detail">
                        <div class="product__name">
                            <a href=""> Product name </a>
                        </div>
                        <div class="product__code">
                            <span>Code:</span>
                            12345678
                        </div>
                        <div class="product__price">
                            <div class="product__price-origin">
                                <span>$</span>
                                <span>100</span>
                            </div>
                            <div class="product__price-sale">
                                <span>$</span>
                                <span>90</span>
                            </div>
                        </div>
                        <div class="product__count-buyed">
                            <span class="counter">123</span>
                            <span>Sold</span>
                        </div>
                        <button class="buy-now" type="submit">
                            <i class="fa-solid fa-cart-plus"></i>
                            Buy now
                        </button>
                    </div>
                </div>
                <div class="product__item">
                    <a class="product__heart"><i class="fa-solid fa-heart"></i></a>
                    <a class="product__add-to-cart">
                        <i class="fa-solid fa-cart-plus"></i>
                    </a>
                    <img
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjesbnxyev3cRpf96DeDaWFrrPL2vabMiYiA&usqp=CAU"
                        alt="product_image"
                        />
                    <div class="product__detail">
                        <div class="product__name">
                            <a href=""> Product name </a>
                        </div>
                        <div class="product__code">
                            <span>Code:</span>
                            12345678
                        </div>
                        <div class="product__price">
                            <div class="product__price-origin">
                                <span>$</span>
                                <span>100</span>
                            </div>
                            <div class="product__price-sale">
                                <span>$</span>
                                <span>90</span>
                            </div>
                        </div>
                        <div class="product__count-buyed">
                            <span class="counter">123</span>
                            <span>Sold</span>
                        </div>
                        <button class="buy-now" type="submit">
                            <i class="fa-solid fa-cart-plus"></i>
                            Buy now
                        </button>
                    </div>
                </div>
                <div class="product__item">
                    <a class="product__heart"><i class="fa-solid fa-heart"></i></a>
                    <a class="product__add-to-cart">
                        <i class="fa-solid fa-cart-plus"></i>
                    </a>
                    <img
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjesbnxyev3cRpf96DeDaWFrrPL2vabMiYiA&usqp=CAU"
                        alt="product_image"
                        />
                    <div class="product__detail">
                        <div class="product__name">
                            <a href=""> Product name </a>
                        </div>
                        <div class="product__code">
                            <span>Code:</span>
                            12345678
                        </div>
                        <div class="product__price">
                            <div class="product__price-origin">
                                <span>$</span>
                                <span>100</span>
                            </div>
                            <div class="product__price-sale">
                                <span>$</span>
                                <span>90</span>
                            </div>
                        </div>
                        <div class="product__count-buyed">
                            <span class="counter">123</span>
                            <span>Sold</span>
                        </div>
                        <button class="buy-now" type="submit">
                            <i class="fa-solid fa-cart-plus"></i>
                            Buy now
                        </button>
                    </div>
                </div>
                <div class="product__item">
                    <a class="product__heart"><i class="fa-solid fa-heart"></i></a>
                    <a class="product__add-to-cart">
                        <i class="fa-solid fa-cart-plus"></i>
                    </a>
                    <img
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjesbnxyev3cRpf96DeDaWFrrPL2vabMiYiA&usqp=CAU"
                        alt="product_image"
                        />
                    <div class="product__detail">
                        <div class="product__name">
                            <a href=""> Product name </a>
                        </div>
                        <div class="product__code">
                            <span>Code:</span>
                            12345678
                        </div>
                        <div class="product__price">
                            <div class="product__price-origin">
                                <span>$</span>
                                <span>100</span>
                            </div>
                            <div class="product__price-sale">
                                <span>$</span>
                                <span>90</span>
                            </div>
                        </div>
                        <div class="product__count-buyed">
                            <span class="counter">123</span>
                            <span>Sold</span>
                        </div>
                        <button class="buy-now" type="submit">
                            <i class="fa-solid fa-cart-plus"></i>
                            Buy now
                        </button>
                    </div>
                </div>
                <div class="product__item">
                    <a class="product__heart"><i class="fa-solid fa-heart"></i></a>
                    <a class="product__add-to-cart">
                        <i class="fa-solid fa-cart-plus"></i>
                    </a>
                    <img
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjesbnxyev3cRpf96DeDaWFrrPL2vabMiYiA&usqp=CAU"
                        alt="product_image"
                        />
                    <div class="product__detail">
                        <div class="product__name">
                            <a href=""> Product name </a>
                        </div>
                        <div class="product__code">
                            <span>Code:</span>
                            12345678
                        </div>
                        <div class="product__price">
                            <div class="product__price-origin">
                                <span>$</span>
                                <span>100</span>
                            </div>
                            <div class="product__price-sale">
                                <span>$</span>
                                <span>90</span>
                            </div>
                        </div>
                        <div class="product__count-buyed">
                            <span class="counter">123</span>
                            <span>Sold</span>
                        </div>
                        <button class="buy-now" type="submit">
                            <i class="fa-solid fa-cart-plus"></i>
                            Buy now
                        </button>
                    </div>
                </div>
                <div class="product__item">
                    <a class="product__heart"><i class="fa-solid fa-heart"></i></a>
                    <a class="product__add-to-cart">
                        <i class="fa-solid fa-cart-plus"></i>
                    </a>
                    <img
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPkYsufIDZPLR3KvQJVI4wQEtVl5BD5pJlVA&usqp=CAU"
                        alt="product_image"
                        />
                    <div class="product__detail">
                        <div class="product__name">
                            <a href=""> Product name </a>
                        </div>
                        <div class="product__code">
                            <span>Code:</span>
                            12345678
                        </div>
                        <div class="product__price">
                            <div class="product__price-origin">
                                <span>$</span>
                                <span>100</span>
                            </div>
                            <div class="product__price-sale">
                                <span>$</span>
                                <span>90</span>
                            </div>
                        </div>
                        <div class="product__count-buyed">
                            <span class="counter">123</span>
                            <span>Sold</span>
                        </div>
                        <button class="buy-now" type="submit">
                            <i class="fa-solid fa-cart-plus"></i>
                            Buy now
                        </button>
                    </div>
                </div>
                <div class="product__item">
                    <a class="product__heart"><i class="fa-solid fa-heart"></i></a>
                    <a class="product__add-to-cart">
                        <i class="fa-solid fa-cart-plus"></i>
                    </a>
                    <img
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMFqR-KA2rd8QyaOgYMt8-kUyGFX3ApISeVw&usqp=CAU"
                        alt="product_image"
                        />
                    <div class="product__detail">
                        <div class="product__name">
                            <a href=""> Product name </a>
                        </div>
                        <div class="product__code">
                            <span>Code:</span>
                            12345678
                        </div>
                        <div class="product__price">
                            <div class="product__price-origin">
                                <span>$</span>
                                <span>100</span>
                            </div>
                            <div class="product__price-sale">
                                <span>$</span>
                                <span>90</span>
                            </div>
                        </div>
                        <div class="product__count-buyed">
                            <span class="counter">123</span>
                            <span>Sold</span>
                        </div>
                        <button class="buy-now" type="submit">
                            <i class="fa-solid fa-cart-plus"></i>
                            Buy now
                        </button>
                    </div>
                </div>


                <!-- Pagination -->
                <div class="pagination">
                    <a class="page active" href="#">1</a>
                    <a class="page" href="#">2</a>
                    <a class="page" href="#">3</a>
                    <a class="page" href="#">4</a>
                </div>
            </div>



        </div>
        <!--<div id="footer">footer</div>-->
    </body>
</html>
