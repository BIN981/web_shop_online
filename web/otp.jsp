<%-- 
    Document   : otp
    Created on : 24-May-2022, 16:35:30
    Author     : BinhNH981
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Confirm Register</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="./css/sign-in-up.css"
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
        <form class="card" action="otpController" method="post">
            <h1 class="card__title">Confirm OTP</h1>
            <span>Check your mail and get OTP!</span>
            <div class="card__password">
                <input
                    id="otp"
                    type="text"
                    class="card__input"
                    placeholder="OTP"
                    name="otpUser"
                    />
            </div>
            <label style="color: red; font-size: 1.5rem">${mess}</label>

            <div class="card__legal">
                <!-- <label id="mess" style="color : " for="remember"> Password had recovery </label> -->
                <div style="text-align: center;"><a href="signin.jsp">Sign in</a></div>
            </div>
            <button class="card__button" type="submit">
                <span>Confirm</span>
            </button>
        </form>
        <script src="js/sign-up.js"></script>
    </body>
</html>
