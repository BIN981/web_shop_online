<%-- 
    Document   : order
    Created on : May 31, 2022, 9:49:59 PM
    Author     : ChauDM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Order Information - SALE</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/order.css"
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
        <!-- navbar -->
        <%@ include file="navbar.jsp" %>
        <div class="wrapper">
            <div class="card__order">
                <h5 class="card__title">Order Information</h5>
                <ul class="card__list">
                    <h3 class="list-title">Products</h3>
                    <c:if test="${orderInfo.products != null}">
                        <c:forEach items="${orderInfo.products}" var="p">
                            <li class="cart__item">
                                <img
                                    src="${p.img}"
                                    class="item__image"
                                    />
                                <div class="item__detail">
                                    <h2 class="item__name">${p.name}</h2>
                                    <h3 class="item__size">Size: ${p.size}</h3>
                                    <span class="item__price">$ ${p.price}</span>
                                    <div class="item__quantity">Quantity: ${p.quantity}</div>
                                </div>
                            </li>
                        </c:forEach>

                    </c:if>

                </ul>
                <form class="user-info" action="confirmOrder?orderId=${orderId}" method="post">
                    <h3 class="form-title">Delivery Information
                        <i class="fa-solid fa-truck"></i>
                    </h3>
                    <div class="form-input">
                        <span class="input-name">Full Name</span>
                        <input type="text" name="fullName" value="${orderInfo.fullName}" required readonly="true"/>
                    </div>
                    <div class="form-input">
                        <span class="input-name">Phone Number</span>
                        <input type="text" name="phone" value="${orderInfo.phone}" required readonly="true"/>
                    </div>
                    <div class="form-input">
                        <span class="input-name">Address</span>
                        <input id="input-address" type="text" name="address" value="${orderInfo.address}" readonly="true"/>
                    </div>
                    <div class="form-input">
                        <span class="input-name">Payment Method</span>
                        <div class="payment">
                            <span style="text-align: center">
                                COD
                            </span>
                        </div>
                    </div>
                    <div class="form-input diactive">
                        <span class="input-name">Delivery fee ($)</span>
                        <input type="text" name="deliveryFee" value="2" readonly/>
                    </div>
                    <div class="form-input diactive">
                        <span class="input-name">Total Cost ($)</span>
                        <input type="text" name="totalPrice" value="${orderInfo.totalPrice}" readonly/>
                    </div>
                    <div class="form-input">
                        <span class="input-name">Note</span>
                        <input type="text" name="note" value="${orderInfo.note}" readonly="true"/>
                    </div>
                    <div class="card__action">
                        <c:if test="${orderInfo.status == 'SUBMITTED'}">
                            <span id="btn-save" class="button-buy" onclick="Submit()">Confirm</span>
                            <a href="cancelOrder?id=${orderId}" class="button-cancel" title="Cancel this order?">Cancel</a>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <script src="js/order.js"></script>
    </body>
</html>
