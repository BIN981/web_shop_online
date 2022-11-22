<%-- 
    Document   : reset-password
    Created on : May 28, 2022, 8:58:53 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Reset password</title>
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
      <form class="card" action="reset" method="post">
      <h1 class="card__title">Reset Password</h1>
      <input id="email" type="email" class="card__input" placeholder="E-mail" name="email"/>
      <div class="card__legal">
        <label for="remember">
          We will send a code to your mail
        </label>
      </div>
      <span style="color:red">${mess}</span>
      <button class="card__button" type="submit">
        <span>Send</span>
        <i class="fa-solid fa-paper-plane"></i>
      </button>
    </form>
    <script src="js/sign-in.js"></script>
    <script src="js/sign-up.js"></script>
  </body>
</html>

