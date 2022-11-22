<%-- 
    Document   : signin
    Created on : 12-May-2022, 14:59:02
    Author     : BinhNH981
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Sign in</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" media="screen" href="./css/sign-in-up.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
            integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
    </head>
    <body>
        <form class="card"  action="loginController" method="post">
            <h1 class="card__title">Sign in</h1>

            <div class="card__description">
                <span>Don't have account?</span>
                <a href="signup.jsp">Sign up</a>
                <span> or </span>
                <a href="reset">Reset password</a>
            </div>
            <input
                id="username"
                name="username"
                type="text"
                class="card__input"
                placeholder="Username"
                />
            <div class="card__password">
                <input
                    id="password"
                    name="password"
                    type="password"
                    class="card__input"
                    placeholder="Password"
                    />
                <i class="fa-solid fa-eye" id="togglePassword"></i>
            </div>
            <span class="text-danger">${mess}</span>
            <div class="card__remember">
                <input type="checkbox" id="remember" name="remember"/>
                <label for="remember"> Remember me </label>
            </div>
            <button class="card__button" type="submit">
                <span>Sign in</span>
                <i class="fa-solid fa-right-to-bracket"></i>
            </button>
        <div class="card__description">
            <a href="home">Back to Homepage</a>
        </div>
        </form>
        <script src="./js/sign-in.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
