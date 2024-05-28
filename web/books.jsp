<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.Normalizer" %>
<%@page import="java.util.regex.Pattern" %>
<%@page import="DAL.*" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
<body>
    <%@ include file="./Header.jsp" %>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <h3>Danh sách sách</h3>
                <div class="row g-5" id="list-book">
                    <c:forEach var="book" items="${booksForPage}">
                        <div class="col-sm-6 col-md-4 col-lg-3">
                            <a href="ProductURL?Service=ProductDetail&Pid=${book.id}" class="d-block h-100 product-item text-decoration-none position-relative">
                                <div class="border-radius-3 position-relative overflow-hidden">
                                    <img src="${book.image}" alt="" class="overflow-hidden" style="width: 270px; height: 380px">
                                    <div class="d-flex justify-content-center align-items-center px-3 button-products">
                                        <button class="button-product me-3">
                                            <i class='bx bx-shopping-bag'></i> 
                                            &ensp; Add to cart
                                        </button>
                                        <button class="button-product me-3">
                                            <i class="fa-solid fa-arrows-up-down-left-right"></i>
                                            &ensp; Quick view
                                        </button>
                                    </div>
                                </div>
                                <div class="p-4 d-flex flex-column">
                                    <h3 class="fw-bold text-black product-title">${book.name}</h3>
                                    <p class="fs-4 product-des">${book.category}</p>
                                    <div class="d-flex align-items-center justify-content-between mt-4">
                                        <span class="product-price">${book.price}₫</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>

                <!-- Pagination -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="books?page=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="books?page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="books?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <%@ include file="./Footer.jsp" %>
</body>
</html>
