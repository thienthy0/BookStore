<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.text.Normalizer" %>
<%@page import="java.util.regex.Pattern" %>
<%@page import="DAL.*" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="./Header.jsp" %>    
        <section id="body" class="mt-5">
            <div class="fashion-container h-100 mt-5">
                <div class="row box-fashion d-flex">
                    <div class="col-md-3 col-lg-2 left-nav bg-white h-100">
                        <div class="list-filter">

                            <div class="filter-header">
                                <h4 style="font-size: larger;" class="filter-title">Filters</h4>
                                <a href="BookURL" id="clearAll" style="color: #ff00a6; text-decoration: none; font-weight: bold; font-size: larger;">Clear all</a>
                            </div> 





                            <!--search by category-->
                            <div class="boder p-4">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h3 class="text-uppercase fs-5 fw-bold">Category: </h3>
                                    <i class="fa-solid fa-magnifying-glass search-icon-filter pointer"></i>
                                </div>
                                <form action="BookURL" method="GET">
                                    <c:forEach items="${requestScope.category}" var="c">
                                        <div class="d-flex align-items-center mt-3">
                                            <input class="me-3 pointer category-item" type="checkbox"
                                                   id="${c.category_id}"
                                                   name="category_id"
                                                   value="${c.category_id}" 
                                                   onchange="this.form.submit()" />
                                            <label class="fs-5 fw-bold pointer text-capitalize"
                                                   for="${c.category_id}">${c.category_name}</label>
                                        </div>
                                    </c:forEach>
                                </form>
                            </div>

                            <!--search by Author-->
                            <div class="boder p-4 mt-5">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h3 class="text-uppercase fs-5 fw-bold">Author: </h3>
                                    <i class="fa-solid fa-magnifying-glass search-icon-filter pointer"></i>
                                </div>
                                <form action="BookURL" method="GET">
                                    <c:forEach items="${requestScope.authors}" var="author">
                                        <div class="d-flex align-items-center mt-3">
                                            <input class="me-3 pointer category-item" type="checkbox"
                                                   id="${author.author_id}"
                                                   name="author_id"
                                                   value="${author.author_id}" 
                                                   onchange="this.form.submit()" />
                                            <label class="fs-5 fw-bold pointer text-capitalize"
                                                   for="${author.author_id}">${author.author_name}</label>
                                        </div>
                                    </c:forEach>
                                </form>
                            </div>

                            <!--search by price-->
                            <div class="boder p-4 text-center">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h3 class="text-uppercase fs-5 fw-bold">Price</h3>
                                </div>
                                <c:set var="min_input" value="${requestScope.min_price}" />
                                <c:set var="max_input" value="${requestScope.max_price}" />
                                <div class="d-flex mt-3 align-items-center flex-column ">
                                    <div class="d-flex align-items-center">
                                        <input class="me-3 pointer py-4" type="number" 
                                               value="${min_input != null ? min_input : ''}"
                                               placeholder="Min price" min="1" name="minPrice" id="minPrice" />
                                        <span class="text-danger fs-5">$</span>
                                    </div>
                                    <div class="d-flex align-items-center mt-3">
                                        <input class="me-3 pointer py-4" type="number"
                                               value="${max_input != null ? max_input : ''}"
                                               placeholder="Max price" min="1" name="maxPrice" id="maxPrice" />
                                        <span class="text-danger fs-5">$</span>
                                    </div>
                                    <div class="text-center mt-3">
                                        <button onclick="filterProduct('price')" type="button" class="border-0 text-white bg-danger py-2 px-4 rounded fs-5 fw-bold">Apply</button>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                    <div class="col-md-9 col-lg-10 right-product h-100">
                        
                        <!--sort by price-->
                        <div class="sort-search">
                            <div class="sort-box">
                                <c:set value="${requestScope.order}" var="order"/>
                                <select class="form-select" onchange="filterProduct('order')" id="orderOption">
                                    <option value="asc" class="py-5" ${order.equals("asc")?"selected":""}>Price: Low To High</option>
                                    <option value="desc" class="py-5" ${order.equals("desc")?"selected":""}>Price: High To Low</option>
                                </select>
                                <span class="select-title">Sort by: </span>
                            </div>
                        </div>
                                
                        <div class="row g-5" id="list-book">
                            <c:forEach items="${requestScope.listBook}" var="o">
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <a href="BookDetail?Pid=${o.id}" class="d-block h-100 product-item text-decoration-none position-relative">
                                        <div class="boder-radius-3 position-relative overflow-hidden">
                                            <img src="${o.image}" alt="" class="overflow-hidden" style="width: 270px; height: 380px">
                                            <div class="d-flex justify-content-center align-items-center px-3 button-products">
                                                <button class="button-product me-3">
                                                    <i class='bx bx-shopping-bag'></i> 
                                                    &ensp; Add to cart
                                                </button>
                                                <button class="button-product me-3">
                                                    <i class="fa-solid fa-arrows-up-down-left-right"></i>
                                                    &ensp; View Detail
                                                </button>
                                                
                                            </div>
                                                <c:if test="${o.discount > 0}">
                                                <div class="product-sale">
                                                    <span class="product-sale-percent">${o.discount}%</span>
                                                    <span class="product-sale-label">Sale</span>
                                                </div>
                                            </c:if>
                                            
                                        </div>
                                        <div class="p-4 d-flex flex-column">
                                            <h3 class="fw-bold text-black product-title">${o.name}</h3>
                                            <div class="flex-fill mt-auto">
                                                <p class="fs-4 product-des">${o.category}</p>
                                                <div class="d-flex align-items-center justify-content-between mt-4">
                                                    <span class="product-price">${o.price}$</span>
                                                    <div class="d-flex align-items-center">                                                                                                              
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="./Footer.jsp" %>
    </body>
</html>
