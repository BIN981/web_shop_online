<%-- 
    Document   : my-order
    Created on : Jun 13, 2022, 10:09:37 PM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>My Order</title>
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
            href="css/my-order.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/footer.css"
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
        <div id="navbar"></div>
        <%@ include file="navbar.jsp" %>

        <div class="wrapper">
            <h2 class="wrapper-title">My Order</h2>

            <table class="order__list">
                <tr class="header">
                    <th>ID</th>
                    <th>Order Date</th>
                    <th>Products</th>
                    <th>Total cost</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${listInvoices}" var="Ivc">
                    <tr class="order__item">
                        <td class="order-id">
                            ${Ivc.orderId}
                        </td>
                        <td class="order-date">${Ivc.orderAt}</td>
                        <td class="order-product">
                            <c:if test="${Ivc.products != null}">
                                <div>
                                    ${Ivc.getFirstProduct().name} x ${Ivc.getFirstProduct().quantity}
                                </div>
                                <c:if test="${Ivc.getQuantityMore() > 0}">
                                    <div>
                                        and ${Ivc.getQuantityMore()} more products
                                    </div>
                                </c:if>

                            </c:if>
                        </td>
                        <td class="order-product">
                            <div>
                                ${Ivc.totalPrice}
                            </div>
                        </td>
                        <td class="order-status">
                            <a href="orderInfo?id=${Ivc.orderId}">
                                <span class="${Ivc.getOrderStatusStyleClass()}">
                                    ${Ivc.status}
                                </span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <!-- Pagination -->
            <div class="pagination">
                <c:if test="${page > 1}">
                    <c:forEach begin="1" end="${page}" var="i">
                        <a class="page <c:if test="${current_page == i}">active</c:if>" href="myOrder?page=${i}<c:if test="${param != null}">&${param}</c:if>">
                            ${i}
                        </a>
                    </c:forEach>
                </c:if>
            </div>
        </div>

    </div>

    <%@include file="footer.jsp" %>
    <script src="js/cart.js"></script>
</body>
</html>

