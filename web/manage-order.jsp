<%-- 
    Document   : manage-order
    Created on : Jun 8, 2022, 11:46:26 AM
    Author     : ChauDM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Manage Orders</title>
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

        <div class="wrapper">
            <h2 class="wrapper-title">Manage Orders</h2>
            <form action="manageOrder" method="get" class="filter">
                <div class="order-date">
                    <span>Order at:</span>
                    <input type="date" name="order_at_from" id="order_at_from" />
                    -
                    <input type="date" name="order_at_to" id="order_at_to" />
                </div>
                <div class="order-by">
                    <span>Customer Name:</span>
                    <input type="text" name="customer_name" id="customer_name">
                </div>

                <div class="order-total-cost">
                    <span>Total Cost:</span>
                    <input type="number" name="total_cost_from" id="total_cost_from">
                    -
                    <input type="number" name="total_cost_to" id="total_cost_to">
                </div>

                <div class="status">
                    <span>Status:</span>
                    <select name="order_status" id="order_status">
                        <option value="ALL" selected>ALL</option>
                        <option value="SUBMITTED">SUBMITTED</option>
                        <option value="CONFIRMED">CONFIRMED</option>
                        <option value="COMPLETED">COMPLETED</option>
                        <option value="CANCELED">CANCELED</option>
                    </select>
                </div>


                <div class="filter-action">
                    <button type="submit"> Show </button>
                    <button type="reset">Reset</button>
                </div>
            </form>
            <!--<div>Filtered by:</div>-->
            <table class="order__list">
                <tr class="header">
                    <th>ID</th>
                    <th>Customer</th>
                    <th>Order Date</th>
                    <th>Products</th>
                    <th>Total cost</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${orders}" var="o">
                    <tr class="order__item">
                        <td class="order-id">
                            <a href="orderInfoSale?id=${o.orderId}">
                                ${o.orderId}
                            </a>
                        </td>
                        <td class="order-id">
                            <a href="orderInfoSale?id=${o.orderId}">
                                ${o.fullName}
                            </a>
                        </td>
                        <td class="order-date">${o.orderAt}</td>
                        <td class="order-product">
                            <c:if test="${o.products != null}">
                                <div>
                                    ${o.getFirstProduct().name} x ${o.getFirstProduct().quantity}
                                </div>
                                <c:if test="${o.getQuantityMore() > 0}">
                                    <div>
                                        and ${o.getQuantityMore()} more products
                                    </div>
                                </c:if>
                                
                            </c:if>
                        </td>
                        <td class="order-product">
                            <div>
                                ${o.totalPrice}
                            </div>
                        </td>
                        <td class="order-status">
                            <span class="${o.getOrderStatusStyleClass()}">
                                ${o.status}
                            </span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <!-- Pagination -->
            <div class="pagination">
                <c:if test="${page > 1}">
                    <c:forEach begin="1" end="${page}" var="i">
                        <a class="page <c:if test="${current_page == i}">active</c:if>" href="manageOrder?page=${i}<c:if test="${param != null}">&${param}</c:if>">
                            ${i}
                        </a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <%@include file="footer.jsp" %>
        <script src="js/manage-order.js"></script>
    </body>
</html>
