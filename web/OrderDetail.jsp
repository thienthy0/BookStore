<%-- 
    Document   : OrderDetail
    Created on : 6 Nov, 2023, 11:37:40 PM
    Author     : HP
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.Normalizer" %>
<%@page import="java.util.regex.Pattern" %>
<%@page import="DAL.*" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail order</title>
        <link rel="stylesheet" href="./style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
    </head>
    <body>
        <%@include file="./Header.jsp" %>
        <h3 class="text-danger">${requestScope.mess?requestScope.mess:""}</h3>
        <section>
            <div class="row h-100">
                <div class="col-md-2 h-100 left-nav-admin p-0">
                    <div class="p-5 pe-0 vh-100">
                        <ul>
                            <li class="py-4 ps-3 mb-3">
                                <a href="" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                                    <i class='bx bxs-dashboard me-3'></i>
                                    <span>Dashboard</span>
                                </a>
                            </li>
                            <li class="py-4 ps-3 mb-3 active">
                                <a href="OrderManager"  class="fs-3 text-white text-decoration-none">
                                    <i class='bx bx-cart me-3'></i>
                                    <span>Order</span>
                                </a>
                            </li>
                            <li class="py-4 ps-3 mb-3">
                                <a href="" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                                    <i class='bx bx-circle-three-quarter me-3'></i>
                                    <span>Statistic</span>
                                </a>
                            </li> 
                            <li class="py-4 ps-3 mb-3">
                                <a href="ManageProduct" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                                    <i class='bx bxs-data me-3'></i>
                                    <span>Products</span>
                                </a>
                            </li> 
                            <li class="py-4 ps-3 mb-3">
                                <a href="" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                                    <i class='bx bx-line-chart me-3'></i>
                                    <span>Stock</span>
                                </a>
                            </li>
                            <li class="py-4 ps-3 mb-3">
                                <a href="" class="fs-2 text-white d-flex align-items-center text-decoration-none">
                                    <i class='bx bx-purchase-tag-alt me-3'></i>
                                    <span>Offer</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-10 h-100">
                    <h1 class="fw-bold my-4">Detail Order #${O_Id}</h1>
                    <div class="">
                        <h3 class="text-success">${mess}</h3>
                        <div class="mt-5 table-header bg-weak rounded-xl ">
                            <div class="row fs-4 fw-bold py-4 px-5 d-flex align-items-center justify-content-between">
                                <div class="col-1">Item id</div>
                                <div class="col-1">Image</div>
                                <div class="col-3">Name</div>
                                <div class="col-1">Discount</div>                           
                                <div class="col-2">List price</div>
                                <div class="col-2">Quantity</div>
                                <div class="col-2">Subtotal</div>
                            </div>
                        </div>

                        <c:forEach var="orderItem" items="${orderDetails}">
                            <div class="mt-5 border-top bg-white rounded-lg border px-5 ">
                                <div class="row position-relative fs-4 px-3 py-4 d-flex align-items-center justify-content-between">
                                    <div class="col-1">${orderItem.getDetail_id()}</div>
                                    <div class="col-1">
                                        <img src="${orderItem.getBook().getImage()}" alt="alt" />
                                    </div>
                                    <div class="col-3">${orderItem.getBook().getName()}</div>
                                    <div class="col-1">${orderItem.getDiscount()}%</div>
                                    <div class="col-2">${orderItem.getPrice()}</div>
                                    <div class="col-2">${orderItem.getQuantity()}</div>
                                    <div class="col-2">${orderItem.orderItemPrice()}</div>
                                </div>
                            </div>
                        </c:forEach>  

                    </div>
                </div>

        </section>
        <script src="../js/app.js"></script>
    </body>
</html>

