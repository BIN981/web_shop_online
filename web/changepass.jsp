<%-- 
    Document   : changepass
    Created on : 25-May-2022, 20:30:37
    Author     : BinhNH981
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Change Password</title>
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
        <form class="card" action="ChangepassController" method="post">
            <h1 class="card__title">Change Pass</h1>
            <input
                id="username"
                name="username"
                type="text"
                class="card__input"
                placeholder="Username"
                disabled="true"
                value="${sessionScope.account.username}"
                />
            <div class="card__password">
                <input
                    id="old-password"
                    type="password"
                    class="card__input"
                    placeholder="Old Password"
                    name="oldPassword" 
                    required="true"
                    />
                <i class="fa-solid fa-eye" id="toggleOldPassword"></i>
            </div>
            <div class="card__password">
                <input
                    id="password"
                    type="password"
                    class="card__input"
                    placeholder="New Password"
                    name="newpassword" 
                    required="true"
                    />
                <i class="fa-solid fa-eye" id="togglePassword"></i>
            </div>
            <div class="card__password">
                <input
                    id="re-password"
                    type="password"
                    class="card__input"
                    placeholder="Confirm Password"
                    name="re_password"
                    required="true"
                    />
                <i class="fa-solid fa-eye" id="toggleRePassword"></i>
            </div>
            <div class="card__legal">
                <!-- <label id="mess" style="color : " for="remember"> Password had recovery </label> -->
                <div style="text-align: center;"><a href="home">You Forget Password?</a></div>
            </div>
            <label style="color: ${mess_color}; font-size: 1.5rem">${mess}</label>
            <button class="card__button" type="submit">
                <span>Change</span>
            </button>
            <div class="card__description">
                <a href="home">Back to Homepage</a>
            </div>
        </form>
        <script src="js/sign-up.js"></script>
        <script src="js/sign-in.js"></script>
    </body>
</html>
