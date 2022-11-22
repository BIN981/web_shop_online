<%-- 
    Document   : manage-user
    Created on : Jun 13, 2022, 9:33:50 PM
    Author     : ChauDM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Manage User</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/manage-user.css"
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
            <h2 class="title">Manage User</h2>
            <form action="manageUsers" method="get" class="filter">
                <div class="">
                    <span>User Fullname:</span>
                    <input type="text" name="name" title="Filter by FullName " id="name"/>
                </div>
                <div class="role">
                    <span>Role:</span>
                    <select name="role" title="Filter by Role" id="role">
                        <option value="0" selected>ALL</option>
                        <option value="2">Marketing</option>
                        <option value="3">Sale</option>
                        <option value="4">Customer</option>
                        <option value="5">Marketing Manager</option>
                    </select>
                </div>


                <div class="filter-action">
                    <button type="submit"> Show </button>
                    <button type="reset">Reset</button>
                </div>
            </form>
            <table class="user__list">
                <tr class="header">
                    <th class="header-col">Avatar</th>
                    <th class="header-col">Full Name</th>
                    <th class="header-col">Gender</th>
                    <th class="header-col">DOB</th>
                    <th class="header-col">Role</th>
                    <th class="header-col">Action</th>
                </tr>
                <c:forEach items="${users}" var="user">

                    <tr class="user__item">
                        <td class="user-avatar">
                            <img src="data:image/jpg;base64,${user.image}" alt="" />
                        </td>
                        <td class="user-fullname">${user.getFullName()}</td>
                        <td class="user-role">
                            <c:if test="${user.gender}">Male</c:if>
                            <c:if test="${!user.gender}">Female</c:if>
                            </td>
                            <td class="user-role">${user.dob}</td>
                        <td class="user-role">${user.getAccount().getRole()}</td>
                        <td class="user-action">
                            <span class="btn-edit" onclick="EditUser(${user.id})">
                                <i class="fa-solid fa-pen"></i>Edit</span>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <span class="btn-add" onclick="AddUser()">
                <i class="fa-solid fa-plus"></i>
                Add user</span
            >
        </div>
        <div class="modal hide">
        </div>

        <%@include file="footer.jsp" %>
        <script src="js/manage-user.js"></script>
    </body>
</html>

