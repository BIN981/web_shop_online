<%-- 
    Document   : manage-products
    Created on : Jun 28, 2022, 3:09:41 PM
    Author     : ChauDM
--%>

<%@page import="modal.ProductCategory"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Manage Products</title>
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
        <link
            rel="stylesheet"
            type="text/css"
            media="screen"
            href="css/notification.css"
            />
        <!-- font awesome -->
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

        <div id="notification-box"></div>

        <div class="wrapper">
            <h2 class="title">Manage Products</h2>
            <form action="manageProducts" method="get" class="filter">
                <div class="order-by">
                    <span>Product Name:</span>
                    <input type="text" name="productName" id="product_name">
                </div>

                <div class="order-total-cost">
                    <span>Price:</span>
                    <input type="number" name="priceFrom" id="price_from">
                    -
                    <input type="number" name="priceTo" id="price_to">
                </div>

                <div class="status">
                    <span>Status:</span>
                    <select name="productStatus" id="product_status">
                        <option value="ALL" selected>ALL</option>
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="DEACTIVE">DEACTIVE</option>
                    </select>
                </div>
                <div class="category">
                    <span>Category</span>
                    <select name="productCategory" id="product_category">
                        <option value="ALL" selected>ALL</option>
                        <c:forEach items="${product_categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>

                    </select>
                </div>


                <div class="filter-action">
                    <button type="submit"> Show </button>
                    <button type="reset">Reset</button>
                </div>
            </form>
            <table class="user__list">
                <tr class="header">
                    <th class="header-col">Image</th>
                    <th class="header-col">Name</th>
                    <th class="header-col">Code</th>
                    <th class="header-col">Category</th>
                    <th class="header-col">Detail</th>
                    <th class="header-col">Publish At</th>
                    <th class="header-col">Original Price</th>
                    <th class="header-col action">Action</th>
                </tr>
                <c:forEach items="${products}" var="product">
                    <tr class="user__item">
                        <td class="user-avatar">
                            <img src="${product.image}" alt="" />
                        </td>
                        <td class="blog-publish">${product.name}</td>
                        <td class="post-title">${product.code}</td>
                        <td class="post-title">${product.getProductCategoryName()}</td>
                        <td class="post-description">${product.shortDesc()} ...</td>
                        <td class="blog-publish">${product.publishAt}</td>
                        <td class="blog-publish">$${product.price}</td>
                        <td class="user-action">
                            <c:if test="${!product.isActive}">
                                <span class="btn-show" onclick="UpdateProductStatus(${product.id}, 1)">
                                    <i class="fa-solid fa-eye"></i>Show
                                </span>
                            </c:if>
                            <c:if test="${product.isActive}">
                                <span class="btn-hide" onclick="UpdateProductStatus(${product.id}, 0)">
                                    <i class="fa-solid fa-eye"></i>Hide
                                </span>
                            </c:if>
                            <span class="btn-edit" onclick="ClickEdit(${product.id})">
                                <i class="fa-solid fa-pen"></i>Edit</span>
                            </span>
                            <span class="btn-preview" onclick="ClickSize(${product.id})">
                                Size</span>
                            <span class="btn-preview sale" onclick="ClickSale(${product.id})">
                                Sale</span>
                        </td>
                    </tr>
                </c:forEach>


            </table>
            <span class="btn-add" onclick="AddProduct()">
                <i class="fa-solid fa-plus"></i>
                Add Product</span
            >
        </div>


        <div class="modal hide"></div>

        <%@include file="footer.jsp" %>
        <script src="js/manage-products.js"></script>
        <script>
                function AddProduct() {
                    document.querySelector('.modal').classList.remove("hide");
                    $('.modal').html(`
       <form action="addProduct" method="post" class="edit-modal">
                        <i class="fa-solid fa-xmark modal__close"></i>
                        <img id='previewThumbnail' src="image/logo.png" alt="image">
                        <span>Image</span>
                        <input class="modal__blog-image" type="file" accept='.jpg, .jpge, .png'>
                        <input id="image-base64" type="text" name="image" hidden>
                        <span>Category</span>
                        <select name="category">
                <% List<ProductCategory> categories = (List<ProductCategory>) request.getAttribute("product_categories");
                for (ProductCategory item : categories){%><option value='<%=item.getId()%>'><%=item.getName()%></option>
                <%} %>
                        </select>
                        <span>Name</span>
                        <input type="text" class="modal__blog-title" name="name" title="Product Name">
                        <span>Code</span>
                        <input type="text" class="modal__blog-title" name="code" title="Product Code">
                        <span>Original Price</span>
                        <input type="number" min="0.00" max="any" step="0.5" class="modal__blog-title" name="price" title="Original price">
                        <span>Detail</span>
                        <textarea class="modal__blog-description" name="detail" 
                        title="Product Detail" rows="15"></textarea>
                        <button type="submit">Save</button>
                        </form>
`);
                    const closeIcon = document.querySelector(".modal__close");
                    const modal = document.querySelector(".modal");


                    closeIcon.addEventListener("click", e => {
                        modal.classList.add("hide");
                        modal.innerHTML = "";
                    });

                    const thumbnailInput = document.querySelector(".modal__blog-image");
                    const thumbnail = document.querySelector("#previewThumbnail");

                    thumbnailInput.addEventListener('change', (e) => {
                        var reader = new FileReader();
                        reader.onload = function () {
                            thumbnail.src = reader.result;
                            document.querySelector("#image-base64").value = reader.result;
                        }
                        reader.readAsDataURL(e.target.files[0]);
                    });
                }
        </script>
    </body>
</html>

