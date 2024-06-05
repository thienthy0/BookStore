<!DOCTYPE html>
<html lang="en">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page import="java.text.Normalizer" %>
    <%@page import="java.util.regex.Pattern" %>
    <%@page import="dal.*" %>
    <%@page import="entity.*" %>
    <%@page import="java.util.*" %>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cartegories</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <%@include file="./Header.jsp" %>

        <section style="margin-bottom: 50px">
            <div class="container">
                <div class="py-5">
                    <h1 class="fw-semibold">Shopping Cart</h1>
                    <nav aria-label="breadcrumb mt-4">
                        <ol class="breadcrumb fs-4">
                            <li class="breadcrumb-item"><a class="text-dark" href="BookURL">Home</a></li>
                            <li class="breadcrumb-item"><a class="text-dark" href="#">Library</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Data</li>
                        </ol>
                    </nav>
                </div>
                <div class="row gx-5 border-top gx-5 ">
                    <div class="col-md-7 row border-top py-3">
<!--                        <div class="col-md-12 d-flex align-items-center ms-3">
                            <input type="checkbox" value="checkall" onclick="checkAll()"
                                   id="checkAllBtn" class="me-3"/> <span class="fs-4 text-danger">All</span>
                        </div>-->
                        <!--<div class="row">-->
                    <%
                        Account acc = null;
                        if(session.getAttribute("account") != null) {
                          acc = (Account)session.getAttribute("account");
                        }
                    int numberOrder = 0;
                    double subtotal = 0;
                    java.util.Enumeration em = session.getAttributeNames();
                    while(em.hasMoreElements()){
                    //id la cart-id
                    String id = em.nextElement().toString();
                    if (id.startsWith("cart")) {
                          numberOrder++;
                          Book pro = (Book)session.getAttribute(id);
                          subtotal+= pro.getPrice() * pro.getQuantity();
                    %>
                        <%--<c:forEach items="${cart.items}" var="item" >--%>
                            <div class="col-md-12 border-end pe-5">
                                <div class="row order-item py-5">
                                        <!--border-top--> 
                                       <div class="col-1 d-flex justify-content-center align-items-center">
                                            <input type="checkbox" name="orderCheck" id="" />
                                        </div>
                                        <div class="col-2">
                                            <a href="ProductURL?Service=ProductDetail&Pid=<%=pro.getId()%>" class="d-block h-100">
                                                <img src="<%=pro.getImage()%>" alt="" class="rounded-lg object-fit-cover">
                                            </a>
                                        </div>
                                        <div class="col-9 d-flex flex-fill flex-column justify-content-between ">
                                            <div class="h-50 d-flex align-items-center justify-content-between flex-fill">
                                                <div class="">
                                                    <h3 class="fw-bold"><%=pro.getName()%></h3>
                                                    <div class="d-flex align-items-center position-relative hover-change">
                                                        <div class="">
                                                            <i class="fa-solid fa-fill-drip me-3"></i>
                                                            <span class="text-black"><%=pro.getCategory()%></span>
                                                        </div>
                                                        <div class="border-line border-l mx-3"></div>
                                                        <div class="">
                                                            <i class="fa-solid fa-store me-3"></i>
                                                            <span class="text-black"><%=pro.getAuthor()%></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="">
                                                    <span class="fs-4">đ<%=pro.getPrice()%></span>
                                                </div>
                                                <div class="d-flex align-items-center">
                                                    <div class="box-input">
                                                        <button class="border-0 bg-white">
                                                            <a href="CartURL?Service=minusQuantity&product_id=<%=pro.getId()%>" class="p-3 text-black"><i class="fa-solid fa-minus fs-5"></i></a>
                                                        </button>
                                                        <input type="text" value="<%=pro.getQuantity()%>" class="input-number" id="numberValue" />
                                                        <button class="border-0 bg-white">
                                                            <a href="CartURL?Service=addQuantity&product_id=<%=pro.getId()%>" class="p-3 text-black"><i class="fa-solid fa-plus fs-5"></i></a>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="h-50 d-flex align-items-center justify-content-between">
    
                                                <div class="ms-auto">
                                                    <a href="CartURL?Service=deleteInCart&product_id=<%=pro.getId()%>" class="text-decoration-none fs-4 fw-bold text-danger">Remove</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                             <%}%>
                            <%}%>
                            <div class="text-dark">
                                <% if(numberOrder == 0) {%>
                                <h4 class="text-danger">Let's shopping now</h4>
                                <img src="./images/emptyCart.png" alt="emptycart" class="w-75 object-fit-cover"/>
                                <%}%>
                            </div>   
                            
                            
                    
                    </div>
                    <div class="col-md-5 text-order fs-4 fw-500 p-5">
                        <div class="w-80 m-auto">
                            <h2 class="fw-semibold text-dark">Order Summary</h2>
                            <div class="d-flex align-items-center justify-content-between border-bottom py-3">
                                <span>Subtotal</span>
                                <%if(subtotal != 0) {%>
                                <span class="fw-600 text-dark">đ <%=String.format("%,.0f",subtotal).replace(",", ".")%></span>
                                <%} else {%>
                                <span class="fw-600 text-dark">đ0</span>
                                <%}%>
                            </div>
<!--                            <div class="d-flex align-items-center justify-content-between border-bottom py-3">
                                <span>Shopping estimate</span>
                                <span class="fw-600 text-dark">đ 30.000</span>
                            </div>-->
                            <div class="d-flex align-items-center justify-content-between border-bottom py-3">
                                <span>Tax estimate</span>
                                <span class="fw-600 text-dark">đ 0</span>
                            </div>
                            <div class="d-flex align-items-center justify-content-between border-bottom py-3">
                                <span class="fw-bold fs-3 text-dark">Order total</span>
                                <%if(subtotal != 0) {%>
                                   <span class="fw-600 fs-3 text-dark">đ <%=String.format("%,.0f",subtotal+30000).replace(",", ".")%></span>
                                <%} else {%>
                                 <span class="fw-600 fs-3 text-dark">đ0</span>
                                <%}%>
                            </div>
                            <div class="mt-3 d-flex align-items-center justify-content-between">
                                <div class="">
                                    <span class="text-black">Address: </span>
                                    <%if(acc != null) {%>
                                      <p class="mt-4-3"><i class="fa-solid fa-location-dot text-danger fs-4 me-3"></i><%=acc.getAddress()%></p>
                                    <%}%>
                                </div> 
                                <div class="">
                                    <a href="" class="fs-4 text-info text-decoration-none">change</a>
                                </div>
                            </div>
                            <a href="CartURL?Service=checkout" class="mt-5 d-block
                               py-3 text-decoration-none fs-2 text-center
                               w-100 text-white bg-dark rounded-xl box-shadow1">
                                Checkout
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="./Footer.jsp" %>
        <script src="./js/app.js"></script>
    </body>
    <script>
        function checkAll() {
            const orderCheck = document.getElementsByName("orderCheck");
             for (var i = 0; i < orderCheck.length; i++) {
               if(orderCheck[i].checked == true) {
                   orderCheck[i].checked = false;
               } else {
                   orderCheck[i].checked = true
               }
              }
        }
    </script>

</html>