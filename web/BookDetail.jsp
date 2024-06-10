<%-- 
    Document   : ProductDetail
    Created on : 13 Oct, 2023, 11:50:54 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <%@include file="./Header.jsp" %>
        <c:set var="product" value="${requestScope.currentProduct}" />
        <section id="product-detail" class="mt-5">
            <div class="container h-100">
                <div class="row h-100 gx-5">
                    <div class="col-md-5">
                        <div class="">
                            <div class="main-image rounded-sm overflow-hidden">
                                <img src="${product.image}" alt="" id="main-image ">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <div class="">
                            <div class="product-title">
                                <h1 class="fw-bold">${product.name}</h1>
                            </div>
                            <div class="d-flex align-items-center mt-5">
                                <div class="px-4 py-1 border-green rounded-sm">
                                    <span class="text-green fs-3 fw-bold">${product.price}₫</span>
                                </div>
                                <c:if test="${product.discount>0}">
                                    <span class="text-white p-2 mx-2 fs-5 fw-bold bg-danger tag-sale">Giảm ${product.discount}%</span>
                                </c:if>
                                <div class="border-line border-l"></div>
                                <div class="d-flex align-items-center px- ">

                                </div>
                            </div>

                            <div class="w-75 mt-5">
                                <h3 class="fw-semibold">Number of Pages :</h3>
                                <div class="d-flex flex-wrap gap-4 fs-4">
                                    ${product.num_of_page}
                                </div>
                            </div>

                            <div class="w-75 mt-5">
                                <h3 class="fw-semibold ">Category name: </h3>
                                <div class="d-flex flex-wrap gap-4 fs-4">
                                    ${product.category}
                                </div>
                            </div>
                            <div class="w-75 mt-5">
                                <h3 class="fw-semibold">Author: </h3>
                                <div class="d-flex flex-wrap gap-4 fs-4">
                                    ${product.author}
                                </div>
                            </div>
                            <div class="w-75 mt-5">
                                <h3 class="fw-semibold">Publisher :</h3>
                                <div class="d-flex flex-wrap gap-4 fs-4">
                                    ${product.publisher}
                                </div>
                            </div>

                            <form action="CartURL" method="post">
                                <input type="text" name="Service" value="addToCart" hidden/>
                                <input value="${product.id}" name="id" hidden/>
                                <div class="w-75 mt-5">
                                    <h5 class="fw-semibold">Number:</h5>
                                    <div class="d-flex align-items-center">
                                        <div class="box-input">
                                            <button type="button" class="p-3 border-0 bg-white" id="minusNumberBtn">
                                                <i class="fa-solid fa-minus fs-5"></i>
                                            </button>
                                            <input type="text" value="1"name="quantity" class="input-number" id="numberValue" />
                                            <button type="button" class="p-3 border-0 bg-white" id="addNumberBtn">
                                                <i class="fa-solid fa-plus fs-5"></i>
                                            </button>
                                        </div>
                                        <div class="ms-4 fs-5">
                                            <span id="number-available">${product.quantity}</span> Products available
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-5">
                                    <div class="d-flex">
                                        <button type="submit" class="d-block w-fit-content px-4 py-4 border-0 rounded-sm min-w20 text-center me-3 bg-black text-white shadow-lg">
                                            <i class="bx bx-shopping-bag fs-3 me-2"></i>
                                            <span class="fs-3 fw-semibold">Add to bag</span>
                                        </button>
                                        <button class="d-block w-fit-content px-4 py-4 border-0 rounded-sm min-w20 text-center bg-danger text-white shadow-lg" id="minusNumberBtn">
                                            <span class="fs-3 fw-semibold">Buy now</span>
                                        </button>
                                    </div>
                                </div>
                            </form>    

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script>
            function addCart() {
                let url = "?Service=addCart&"
            }
        </script>
        <script src="./js/app.js"></script>
        <%@include file="./Footer.jsp" %>
    </body>
</html>
