<%-- 
    Document   : product-details
    Created on : Jun 13, 2022, 9:52:46 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>${detail.name}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">

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
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/feedback.css"
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

    </head><!--/head-->

    <body>     
        <%@ include file="navbar.jsp" %>
        <div id="notification-box"></div>

        <section style="margin-top: 150px">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <!--category-products--> 
                            <h2>Category - Brands</h2>
                            <div class="panel-group category-products" id="accordian">
                                <c:forEach items="${product_categories}" var="pc">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title"><a href="home?productCategoryId=${pc.id}">${pc.name}</a></h4>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>                
                        </div>
                    </div>


                    <div class="col-sm-9 padding-right">
                        <div class="product-details"><!--product-details-->
                            <div class="col-sm-5">
                                <div class="view-product" href='#'>
                                    <img src="${detail.image}" alt="product_image" />

                                </div>
                            </div>

                            <div class="col-sm-7">
                                <div class="product-information">
                                    <h2>${detail.name}</h2>
                                    <p>Code: ${detail.code}</p>

                                    <div class='product_price'>
                                        <c:if test="${detail.salePrice != 0.00}">
                                            <span>${detail.price}</span>
                                            <span>${detail.salePrice}</span>   
                                        </c:if> 
                                        <c:if test="${detail.salePrice == 0.00}">
                                            <span>$</span>
                                            <span>${detail.price}</span>
                                        </c:if>                                     
                                        <div class="btn btn-fefault cart" onclick="AddToCart('AY700')">
                                            Add to cart
                                        </div>

                                    </div>
                                    <div>Size:</div>
                                    <div class="modal__product-size">

                                        <input class="size" type="radio" name="productSize" value="38" checked="">38
                                        <input class="size" type="radio" name="productSize" value="39">39
                                        <input class="size" type="radio" name="productSize" value="40">40
                                        <input class="size" type="radio" name="productSize" value="41">41
                                        <input class="size" type="radio" name="productSize" value="42">42
                                        <input class="size" type="radio" name="productSize" value="43">43
                                    </div>
                                    <p><b>Condition:</b> New</p> 

                                    <div class='descsription-product'>
                                        Description:
                                        <p>${detail.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>                                         
                    </div>
                </div>
        </section>

        <c:if test="${!feedbacks.isEmpty()}">
            <div class="feedback">
                <h2 class="feedback__title">Feedback</h2>
                <ul class="feedback__list">
                    <c:forEach items="${feedbacks}" var="feedback">
                        <li class="feedback__item">
                            <c:if test="${feedback.publishBy == sessionScope.user.id}">
                                <i class="fa-solid fa-trash" title="Remove my feedback" onclick="RemoveFeedback(${feedback.id}, ${detail.id})"></i>
                            </c:if>
                            <img src="data:image/jpg;base64,${feedback.avatar}" alt="avatar">
                            <div class="feedback__item-detail">
                                <div class="detail-name">${feedback.fullName}</div>
                                <div class="detail-date">${feedback.publishAt}</div>
                                <div class="detail-star">
                                    <c:forEach begin="1" end="${feedback.rateStar}">
                                        <i class="fa-solid fa-star"></i>
                                    </c:forEach>
                                </div>
                                <div class="detail-content">
                                    <div class="content-image">
                                        <c:forEach items="${feedback.images}" var="image">
                                            <img src="${image}" alt="">
                                        </c:forEach>
                                    </div>
                                    ${feedback.content} </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <!-- Pagination -->
                <div class="pagination">
                    <c:if test="${totalPage > 1}">
                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <a class="page <c:if test="${current_page == i}">active</c:if>" href="productDetail?productId=${detail.id}&feebackPage=${page}">${i}</a>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </c:if>


        <%@include file="footer.jsp" %>
        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>        
        <script src="js/main.js"></script>
        <script src="js/modal-product.js"></script>
    </body>
</html>
