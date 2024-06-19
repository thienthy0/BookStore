<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <h4>Search</h4>
                                <span id="clearAll">Clear all</span>
                            </div>
                            <div class="boder p-4">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h3 class="text-uppercase fs-5 fw-bold"> Category: </h3>
                                    <i class="fa-solid fa-magnifying-glass search-icon-filter pointer"></i>
                                </div>
                                <c:forEach var="category" items="${requestScope.listCategory}">
                                    <c:set var="isChecked" value="false" />
                                    <c:forEach var="category_select" items="${requestScope.category_select}">
                                        <c:if test="${category_select == category.category_name && !isChecked}">
                                            <c:set var="isChecked" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <div class="d-flex align-items-center mt-3">
                                        <input class="me-3 pointer category-item" type="checkbox" 
                                               id="category-${category.category_id}"
                                               class="custom-radio" name="category"
                                               ${isChecked ? "checked" : ""}
                                               onchange="filterProduct()"
                                               value="${category.category_name}" />
                                        <label class="fs-5 fw-bold pointer text-capitalize"
                                               for="category-${category.category_id}">${category.category_name}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="boder p-4">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h3 class="text-uppercase fs-5 fw-bold">Author: </h3>
                                    <i class="fa-solid fa-magnifying-glass search-icon-filter pointer"></i>
                                </div>
                                <c:forEach var="author" items="${requestScope.listAuthor}">
                                    <c:set var="isChecked" value="false" />
                                    <c:forEach var="author_select" items="${requestScope.author_select}">
                                        <c:if test="${author_select == author.author_name && !isChecked}">
                                            <c:set var="isChecked" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <div class="d-flex align-items-center mt-3">
                                        <input class="me-3 pointer custom-radio" type="checkbox"
                                               id="author-${author.author_id}"
                                               ${isChecked ? "checked" : ""}
                                               name="author" value="${author.author_name}"
                                               onchange="filterProduct()" />
                                        <label class="fs-5 fw-bold pointer text-capitalize"
                                               for="author-${author.author_id}">${author.author_name}</label>
                                    </div>
                                </c:forEach>
                            </div>
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
                            <!--search by discount-->
                            <div class="boder p-4">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h3 class="text-uppercase fs-5 fw-bold">Discount range</h3>
                                </div>
                                <c:forEach var="discount" items="${requestScope.ListDiscount}">
                                    <div class="d-flex align-items-center mt-3">
                                        <input class="me-3 pointer" type="radio" id="discount-${discount}"
                                               name="discount" value="${discount}"
                                               onclick="handleDiscountFilter('${discount}')"
                                               ${param.discount == discount ? 'checked' : ''} />
                                        <label class="fs-5 pointer" for="discount-${discount}">Sale ${discount}% and above</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9 col-lg-10 right-product h-100">
                        <!--sort by price-->
                        <div class="sort-search">
                            <div class="sort-box">
                                <c:set value="${requestScope.order}" var="order"/>
                                <select class="form-select" onchange="filterProduct('order')" id="orderOption">
                                    <option value="default" class="py-5" ${order.equals("default")?"selected":""}>Price: Default</option>
                                    <option value="asc" class="py-5" ${order.equals("asc")?"selected":""}>Price: Low To High</option>
                                    <option value="desc" class="py-5" ${order.equals("desc")?"selected":""}>Price: High To Low</option>
                                </select>

                                <span class="select-title">Sort by: </span>
                            </div>
                        </div>

                        <div class="row g-5" id="list-book">

                            <c:choose>
                                <c:when test="${empty requestScope.listBook}">
                                    <div class="col-12 text-center mt-5">
                                        <h2 class="no-books-found">No books found</h2>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${requestScope.listBook}" var="o">
                                        <c:if test="${param.discount == null || (param.discount != null && o.discount > 0)}">
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
                                                            <div class="">
                                                                <p class="text-decoration-line-through text-black fs-4">${o.getPrice()}$</p>
                                                            </div>
                                                            <div class="d-flex align-items-center justify-content-between mt-4">
                                                                <span class="product-price">${o.getPriceAfterDiscount()}$</span>
                                                                <div class="d-flex align-items-center">                                                                                                              
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="./Footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
        <!-- Initialize Swiper -->
        <script>
                                    var swiper = new Swiper(".mySwiper", {
                                        spaceBetween: 30,
                                        centeredSlides: true,
                                        loop: true,
                                        speed: 1500,
                                        autoplay: {
                                            delay: 5000,
                                            disableOnInteraction: false,
                                        },
                                        pagination: {
                                            el: ".swiper-pagination",
                                            clickable: true,
                                        },
                                        navigation: {
                                            nextEl: ".swiper-button-next",
                                            prevEl: ".swiper-button-prev",
                                        },
                                    });

                                    function actionHover() {
                                        let ProductList = document.querySelectorAll('.product-item');
                                        ProductList.forEach((product) => {
                                            product.addEventListener('mouseover', () => {
                                                product.querySelector('.button-products').classList.add('activeHover');
                                            });
                                            product.addEventListener('mouseout', () => {
                                                product.querySelector('.button-products').classList.remove('activeHover');
                                            });
                                        });
                                    }
                                    actionHover();

                                    function filterProduct() {
                                        let url = "?Service=filter"; // Base URL
                                        const listAuthor = document.getElementsByName("author");
                                        const listCategory = document.getElementsByName("category");
                                        const discount = document.getElementsByName("discount");
                                        const minPrice = document.getElementById("minPrice").value;
                                        const maxPrice = document.getElementById("maxPrice").value;
                                        const orderOption = document.getElementById("orderOption").value;

                                        // Add authors to URL
                                        for (let i = 0; i < listAuthor.length; i++) {
                                            if (listAuthor[i].checked) {
                                                url += "&author=" + encodeURIComponent(listAuthor[i].value);
                                            }
                                        }

                                        // Add categories to URL
                                        for (let i = 0; i < listCategory.length; i++) {
                                            if (listCategory[i].checked) {
                                                url += "&category=" + encodeURIComponent(listCategory[i].value);
                                            }
                                        }

                                        // Add discounts to URL
                                        for (let i = 0; i < discount.length; i++) {
                                            if (discount[i].checked) {
                                                url += "&discount=" + encodeURIComponent(discount[i].value);
                                            }
                                        }

                                        // Add price range to URL
                                        if (minPrice) {
                                            url += "&minPrice=" + encodeURIComponent(minPrice);
                                        }
                                        if (maxPrice) {
                                            url += "&maxPrice=" + encodeURIComponent(maxPrice);
                                        }

                                        // Add order to URL
                                        if (orderOption) {
                                            url += "&order=" + encodeURIComponent(orderOption);
                                        }

                                        // Redirect to the new URL
                                        window.location.href = url;
                                    }


                                    function handleSearch() {
                                        const headerInput = document.getElementById("header-search");
                                        window.location = "?Service=search&keyword=" + headerInput.value;
                                    }

                                    document.getElementById("clearAll").addEventListener("click", function () {
                                        var checkboxInput = document.querySelectorAll("input[type=checkbox]");
                                        checkboxInput.forEach(function (input) {
                                            input.checked = false;
                                        });

                                        var radioInputs = document.querySelectorAll("input[type=radio]");
                                        radioInputs.forEach(function (input) {
                                            input.checked = false;
                                        });

                                        window.location = "BookURL";
                                    });

                                    function handleDiscountFilter(discount) {
                                        let currentUrl = window.location.href;
                                        let cleanUrl = currentUrl.split('?')[0];

                                        let params = new URLSearchParams(window.location.search);

                                        // Remove all discount related params first
                                        params.delete('discount');

                                        // Add the selected discount param
                                        if (discount) {
                                            // Assuming you want to filter books with discount greater than or equal to the selected discount
                                            params.set('discount', discount);
                                        }

                                        // Construct the new URL with updated params
                                        let newUrl = cleanUrl + '?' + params.toString();
                                        window.location.href = newUrl;
                                    }
        </script>
    </body>
</html>
<style>
    .no-books-found {
        font-size: 20rem; /* Kích thước chữ lớn hơn */
        color: pink; /* Màu chữ hồng */
    }
</style>

