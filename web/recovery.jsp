<%-- 
    Document   : recovery
    Created on : May 28, 2022, 9:15:53 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Password Recovery</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      type="text/css"
      media="screen"
      href="css/sign-in-up.css"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
      integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body>
      <form class="card" action="recovery" method="post">
      <h1 class="card__title">Recovery</h1>
      <div class="card__password">
        <input
          id="otp"
          type="text"
          class="card__input"
          placeholder="OTP"
          name="otp"
        />
      </div>
      <div class="card__password">
        <input
          id="password"
          type="password"
          class="card__input"
          placeholder="Password"
          name="newPassword"
        />
        <i class="fa-solid fa-eye" id="togglePassword"></i>
      </div>
      <div class="card__password">
        <input
          id="re-password"
          type="password"
          class="card__input"
          placeholder="Confirm Password"
          name="rePassword"
        />
        <i class="fa-solid fa-eye" id="toggleRePassword"></i>
      </div>
      <div class="card__legal">
        <!-- <label id="mess" style="color : " for="remember"> Password had recovery </label> -->
        <div style="text-align: center;"><a href="signin.jsp">Sign in</a></div>
      </div>
      <span style="color:${mess_color}">${mess}</span>
      <button class="card__button" type="submit">
        <span>Recovery</span>
      </button>
    </form>
    <script src="js/sign-up.js"></script>
    <script src="js/sign-in.js"></script>
  </body>
</html>
