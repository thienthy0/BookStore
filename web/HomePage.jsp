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
        <%@include file="./Header.jsp" %>    
        <section id="banner" >
            <div class="swiper mySwiper swiper-initialized swiper-horizontal swiper-backface-hidden">
                <div class="swiper-wrapper" id="swiper-wrapper-9f6c7920a6102d84" aria-live="off" style="transition-duration: 0ms; transform: translate3d(-1728px, 0px, 0px); transition-delay: 0ms; max-height: 850px;">



                    <div class="swiper-slide swiper-slide-next" role="group" aria-label="1 / 3" data-swiper-slide-index="0" style="width: 734px; margin-right: 30px;">
                        <div class="slider-img">
                            <img src="./images/B1.jpg" alt="">
                        </div>
                        <div class="slider-info">
                            <h2 class="slider-title">Exclusive collection for everyone</h2>
                            <p class="slider-desc">Lorem ipsum dolor sit amet coaaansectetur adipisicing elit. Aperiam tenetur molestiae magnam.</p>
                            <a href="#" class="slider-button">
                                Order now
                                <i class="fa-solid fa-arrow-right"></i>
                            </a>
                        </div>
                    </div><div class="swiper-slide swiper-slide-prev" role="group" aria-label="2 / 3" data-swiper-slide-index="1" style="width: 834px; margin-right: 30px;">
                        <div class="slider-img">
                            <img src="./images/B2.jpg" alt="">
                        </div>
                        <div class="slider-info">
                            <h2 class="slider-title">Exclusive collection for everyone</h2>
                            <p class="slider-desc">Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam tenetur molestiae magnam.</p>
                            <a href="#" class="slider-button">
                                Order now
                                <i class="fa-solid fa-arrow-right"></i>
                            </a>
                        </div>
                    </div><div class="swiper-slide swiper-slide-active" role="group" aria-label="3 / 3" data-swiper-slide-index="2" style="width: 834px; margin-right: 30px;">
                        <div class="slider-img">
                            <img src="./images/B3.jpg" alt="">
                        </div>
                        <div class="slider-info">
                            <h2 class="slider-title">Exclusive collection for everyone</h2>
                            <p class="slider-desc">Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam tenetur molestiae magnam.</p>
                            <a href="#" class="slider-button">
                                Order now
                                <i class="fa-solid fa-arrow-right"></i>
                            </a>
                        </div>
                    </div></div>
                <div class="swiper-button-next" tabindex="0" role="button" aria-label="Next slide" aria-controls="swiper-wrapper-9f6c7920a6102d84" fdprocessedid="bgn3t">

                </div>
                <div class="swiper-button-prev" tabindex="0" role="button" aria-label="Previous slide" aria-controls="swiper-wrapper-9f6c7920a6102d84" fdprocessedid="eqytz">

                </div>
                <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets swiper-pagination-horizontal"><span class="swiper-pagination-bullet" tabindex="0" role="button" aria-label="Go to slide 1"></span><span class="swiper-pagination-bullet" tabindex="0" role="button" aria-label="Go to slide 2"></span><span class="swiper-pagination-bullet swiper-pagination-bullet-active" tabindex="0" role="button" aria-label="Go to slide 3" aria-current="true"></span></div>
                <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span><span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
        </section>

        <section id="body" class="mt-5">


            <div class="fashion-container h-100 mt-5">
                <div class="row box-fashion d-flex">
                    <div class="col-md-3 col-lg-2 left-nav bg-white h-100">


                        <!--<form action="ProductURL" method="get">-->
                        <div class="boder p-4">
                            <div class="d-flex align-items-center justify-content-between">
                                <h3 class="text-uppercase fs-5 fw-bold">Thể loại: </h3>
                                <i class="fa-solid fa-magnifying-glass search-icon-filter pointer"></i>
                            </div>
                            <form action="category" method="GET">
                                <c:forEach items="${requestScope.listC}" var="c">
                                    <div class="d-flex align-items-center mt-3">
                                        <input class="me-3 pointer category-item" type="radio"
                                               id="${c.category_id}"
                                               name="category_id"
                                               value="${c.category_id}" 
                                               onchange="this.form.submit()"
                                               <c:if test="${param.category_id != null}">
                                                   <c:forEach items="${param.category_id}" var="selectedId">
                                                       <c:if test="${selectedId == c.category_id}">
                                                           checked
                                                       </c:if>
                                                   </c:forEach>
                                               </c:if> />
                                        <label class="fs-5 fw-bold pointer text-capitalize"
                                               for="${c.category_id}">${c.category_name}</label>
                                    </div>
                                </c:forEach>
                            </form>
                        </div>


                    </div>
                    <div class="col-md-9 col-lg-10 right-product h-100">

                        <div class="row g-5" id="list-book">
                            <c:forEach items="${requestScope.listBook}" 
                                       var="o">
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <a href="ProductURL?Service=ProductDetail&Pid=${o.id}" class="d-block h-100 product-item text-decoration-none position-relative">
                                        <div class="boder-radius-3 position-relative overflow-hidden">

                                            <img src="${o.image}" alt="" class="overflow-hidden" style="width: 270px; height: 380px">
                                            <div class="d-flex justify-content-center align-items-center px-3 button-products">
                                                <button class="button-product me-3">
                                                    <i class='bx bx-shopping-bag'></i> 
                                                    &ensp; Add to cart
                                                </button>
                                                <button class="button-product me-3">
                                                    <i class="fa-solid fa-arrows-up-down-left-right"></i>
                                                    &ensp; Qick view
                                                </button>
                                            </div>

                                        </div>
                                        <div class="p-4 d-flex flex-column">
                                            <h3 class="fw-bold text-black product-title">${o.name}</h3>
                                            <div class="flex-fill mt-auto">
                                                <p class="fs-4 product-des">${o.category}</p>

                                                <div class="d-flex align-items-center justify-content-between mt-4">
                                                    <span class="product-price">${o.price}₫</span>
                                                    <div class="d-flex align-items-center">                                                                                                              
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        </section>
                        <%@include file="./Footer.jsp" %>
                        </body>
                        </html>
