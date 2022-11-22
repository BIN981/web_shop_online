<%-- 
    Document   : user-profile
    Created on : May 17, 2022, 8:53:51 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>${sessionScope.user.getFullName()}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/user-profile.css"
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
        <%@ include file="navbar.jsp" %>

        <div class="profile">
            <span class="profile__verified active">
                <i class="fa-solid fa-circle-check"></i>
            </span>
            <form class="profile__detail" action="updateProfile" method="post">

                <div class="profile__image">
                    <c:if test="${sessionScope.user.image == null}">
                        <img id="avatar" src="image/BaseAvatar.jpg" />
                    </c:if>
                    <c:if test="${sessionScope.user.image != null}">
                        <img id="avatar"
                            src="data:image/jpg;base64,${sessionScope.user.image}"
                            />
                    </c:if>

                        <input type="file" name="avatar" hidden="true" id="avatar-choose"/>
                        <input type="text" name="avatarBase64String" hidden="true" id="avatar-base64"/>
                    <label for="avatar-choose"
                           ><i class="fa-solid fa-pen-to-square"></i
                        ></label>
                </div>
                <a class="btn-change-password" href="changepass.jsp">
                    Change password
                    <i class="fa-solid fa-arrows-spin"></i>
                </a>
                <div class="profile__detail-firstname">
                    <span>First Name</span>
                    <input type="text" id="first-name" name="first-name" value="${sessionScope.user.firstName}" />
                </div>
                <div class="profile__detail-lastname">
                    <span>Last Name</span>
                    <input
                        type="text"
                        id="last-name"
                        name="last-name"
                        value="${sessionScope.user.lastName}"
                        />
                </div>
                <div class="profile__detail-gender">
                    <span>Gender</span>
                    <input type="radio" id="male" value="male" name="gender" ${sessionScope.user.gender == true ? "checked=\"true\"" : ""} />
                    <strong> Male </strong>
                    <input type="radio" id="female" value="female" name="gender" ${sessionScope.user.gender == true ? "" : "checked=\"true\""}/>
                    <strong> Female </strong>
                </div>
                <div class="profile__detail-email">
                    <span>Email</span>
                    <input type="text" id="email" value="${sessionScope.user.email}" disabled />
                </div>
                <div class="profile__detail-email">
                    <span>Date of birth</span>
                    <input type="date" name="dob" value="${sessionScope.user.dob}" />
                </div>
                <div class="profile__detail-phone">
                    <span>Phone Number</span>
                    <input type="text" id="phone" name="phone" value="${sessionScope.user.phoneNumber}" />
                </div>
                <div class="profile__detail-address">
                    <span>Address</span>
                    <input
                        type="text"
                        id="address"
                        name="address"
                        value="${sessionScope.user.address}"
                        />
                </div>
                <span style="color: ${colorMess}">${mess}</span>
                <button class="btn-save" type="submit">
                    Save
                    <i class="fa-solid fa-cloud"></i>
                </button>
                
            </form>
        </div>

        <%@include file="footer.jsp" %>
        <script src="js/profile.js"></script>
    </body>
</html>

