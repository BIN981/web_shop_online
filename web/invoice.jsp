<%-- 
    Document   : invoice
    Created on : Jun 23, 2022, 8:52:46 PM
    Author     : ChauDM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Invoice</title>
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
            href="css/invoice.css"
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
    </head>
    <body>
        <!-- navbar -->
        <%@ include file="navbar.jsp" %>

        <div class="invoice">
            <h2 class="title">Order Information</h2>
            <h4 class="message">
                Thanks for shopping, your order has processing...
                <br/>
                You will receive the item in about 5 to 7 days
            </h4>
            <div class="invoice__detail">
                <div class="user-info">
                    <h3>Customer Information</h3>
                    <div>Customer: ${orderInfo.fullName}</div>
                    <div>Phone number: ${orderInfo.phone}</div>
                    <div>Email: ${sessionScope.user.email}</div>
                    <div>Address: ${orderInfo.address}</div>
                    <div>Order at: ${orderAt}</div>
                    <div>Note: ${orderInfo.note}</div>
                    <div>Status: <span class="status">${orderInfo.status}</span></div>

                </div>
                <hr>
                <div class="products">
                    <h3>Products</h3>
                    <c:if test="${orderInfo.products != null}">
                        <c:forEach items="${orderInfo.products}" var="product">
                            <div class="product">
                                <div class="product-image">
                                    <img src="${product.img}" alt="image">
                                </div>
                                <div class="product-detail">
                                    <div class="product-name">${product.name}</div>
                                    <div class="product-size">Size: ${product.size}</div>
                                    <div class="product-price">Price: <span>$ ${product.price}</span></div>
                                </div>
                                <div class="product-quantity">
                                    <i class="fa-solid fa-xmark"></i>
                                    <span>${product.quantity}</span>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <hr>
                <div class="total-cost">Total Cost: <span class="status">$ ${orderInfo.totalPrice}</span></div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>

