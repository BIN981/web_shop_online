<%-- 
    Document   : slider-detail
    Created on : Jul 9, 2022, 10:46:20 AM
    Author     : ChauDM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>${slider.title}</title>
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
            href="css/slider-detail.css"
            />

        <!-- font awesome -->
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

        <div class="slider">
            <div class="slider__sider">
                <ul class="slider__other-list">
                    <c:forEach items="${sliderOthers}" var="other">
                        <li class="slider__other-item">
                            <a href="slider?sliderId=${other.id}"> 
                                <h2 class="item-title">${other.title}</h2>
                                <img src="${other.image}" alt="">  
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="slider__detail">
                <img src="${slider.image}" alt="" />
                <h4 class="slider-title">${slider.title}

                </h4>
                <div class="slider-publish">${slider.publishAt} </div>
                <div class="slider-author">${slider.getAuthor().getFullName()}</div>
                <div class="slider-content">
                    ${slider.content}
                </div>
            </div>
        </div>


    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
