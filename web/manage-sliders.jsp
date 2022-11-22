

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Manage Slider</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/manage-posts.css"
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
            <h2 class="title">Manage Slider</h2>
            <table class="user__list">
                <tr class="header">
                    <th class="header-col">Thumbnail</th>
                    <th class="header-col">Title</th>
                    <th class="header-col">Description</th>                  
                    <th class="header-col">Published By</th>
                    <th class="header-col action">Action</th>
                </tr>
            
                <c:forEach items="${sliders}" var="s">
                    <tr class="user__item">
                        <td class="user-avatar">
                            <img src="${s.image}" alt="" />
                        </td>
                        <td class="post-title">${s.title}</td>
                        <td class="post-description">${s.shortDes()} ...</td>                      
                        <td class="post-publish">${s.getAuthor().getFullName()}</td>
                        <td class="user-action">
                            <c:if test="${s.isActive}">
                                <span class="btn-hide" onclick="UpdateStatusSlider(${s.id}, ${s.isActive})">
                                    <i class="fa-solid fa-eye-slash"></i>Hide
                                </span>
                            </c:if>
                            <c:if test="${!s.isActive}">
                                <span class="btn-show" onclick="UpdateStatusSlider(${s.id}, ${s.isActive})">
                                    <i class="fa-solid fa-eye"></i>Show
                                </span>
                            </c:if>
                            <span class="btn-edit" onclick="EditSlider(${s.id})">
                                <i class="fa-solid fa-pen"></i>Edit</span>                                                    
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <span class="btn-add" onclick="AddSlider()">
                <i class="fa-solid fa-plus"></i>
                Add Slider</span>
            <!-- Pagination -->
            <div class="pagination">
                <c:if test="${page != 1}">
                    <c:forEach begin="1" end="${page}" var="i">
                        <a class="page <c:if test="${current_page == i}">active</c:if>" href="manageSliders?page=${i}">${i}</a>
                    </c:forEach>
                </c:if>
            </div>
        </div>

<div class="modal hide"></div>


<%@include file="footer.jsp" %>
<script src="js/manage-sliders.js"></script>
</body>
</html>

