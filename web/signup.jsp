<%-- 
    Document   : signup
    Created on : 12-May-2022, 20:51:52
    Author     : BinhNH981
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Sign up</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="./css/styleSignup.css"
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
        <form class="card" action="signupController" method=post>
            <h1 class="card__title">Create account</h1>
            <div class="card__description">
                <span>Already have an account?</span>
                <a href="signin.jsp">Sign in</a>
            </div>
            <input
                id="username"
                name="username"
                type="text"
                class="card__input"
                placeholder="Username"
                required
                />
            <input id="email" type="email" class="card__input" placeholder="E-mail" name="email" required/>
            <input id="dob" type="date" class="card__input" placeholder="dob" name="dob"/>
            <input id="adress" type="text" class="card__input" placeholder="Address" name="address" />
            <div class="card__name">
                <input id="first_name" type="text" class="card__input" placeholder="First Name" name="firstname" />
                <input id="last_name" type="text" class="card__input" placeholder="Last Name" name="lastname" />
            </div>
            <div class="card__gender">
                <input type="radio" id="male" name="gender" value="male" checked/>
                <label for="male">
                    Male
                </label>
                <input type="radio" id="female" name="gender" value="female"/>
                <label for="female">
                    Female
                </label>
            </div>
            <input
                id="phone_number"
                type="number"
                class="card__input"
                placeholder="Phone Number"
                name="phone"
                />
            <div class="card__password">
                <input
                    id="password"
                    type="password"
                    value=""
                    class="card__input"
                    placeholder="Password"
                    name="password"
                    required
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
                    required
                    />
                <i class="fa-solid fa-eye" id="toggleRePassword"></i>
            </div>
            <label style="color: ${mess_color}; font-size: 1.5rem">${mess}</label>
            <button class="card__button" type="submit">
                <span>Sign up</span>
                <i class="fa-solid fa-right-to-bracket"></i>
            </button>
            <div class="card__description">
                <a href="home">Back to Homepage</a>
            </div>
        </form>
        <script src="js/sign-in.js"></script>
        <script src="js/sign-up.js"></script>
    </body>
</html>