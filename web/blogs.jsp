<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Blogs</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/blog-page.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/blog-detail.css"
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

        <div class="posts">
            <span class="posts_title">All Posts</span>
            <div class="post__sider">
                <form class="post__search" method="get" action="blogListController">
                    <input type="text" name="search_post" placeholder="Post title">
                    <button type="submit">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </form>
                <ul class="post__category">
                    <c:forEach var="c" items="${blogCategory}">
                        <c:if test="${blogCategoryId != 0 && blogCategoryId == c.id}">
                            <li class="post__category-item active">
                                <a href="blogListController?blogCategoryId=${c.id}">
                                    ${c.name}
                                </a> 
                            </li>
                        </c:if>
                        <c:if test="${blogCategoryId != 0 && blogCategoryId != c.id}">
                            <li class="post__category-item">
                                <a href="blogListController?blogCategoryId=${c.id}">
                                    ${c.name}
                                </a> 
                            </li>
                        </c:if>
                        <c:if test="${blogCategoryId == 0}">
                            <li class="post__category-item">
                                <a href="blogListController?blogCategoryId=${c.id}">
                                    ${c.name}
                                </a> 
                            </li>
                        </c:if>

                    </c:forEach>
                </ul>
            </div>
            <div class="post__list">
                <c:forEach var="b" items="${blogs}">
                    <div class="post">
                        <img src="${b.image}" alt="" />

                        <h2 class="post__title" onclick="GetDetail(${b.id})">${b.title}</h2>

                        <div class="post__date">${b.publishAt}</div>
                        <div class="post__description">
                            ${b.shortDes()}...
                        </div>
                    </div>
                </c:forEach>


                <!-- Pagination -->
                <div class="pagination">
                    <c:if test="${page != 1}">
                        <c:forEach begin="1" end="${page}" var="i">
                            <a class="page <c:if test="${current_page == i}">active</c:if>" href="blogListController?page=${i}<c:if test="${blogCategoryId != 0}">&blogCategoryId=${blogCategoryId}</c:if><c:if test="${search != null}">&search=${search}</c:if>">${i}</a>
                        </c:forEach>

                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
        <script src="js/blog-detail.js"></script>
    </body>
</html>
