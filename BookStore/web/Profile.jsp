<%-- 
    Document   : Profile
    Created on : 31 Oct, 2023, 8:05:34 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page import="java.text.Normalizer" %>
    <%@page import="java.util.regex.Pattern" %>
    <%@page import="DAL.*" %>
    <%@page import="Models.*" %>
    <%@page import="java.util.*" %>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="./style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
    </head>
</head>
<body>
    <%@include file="./Header.jsp" %>
    <section>
        <div class="w-60 m-auto">
            <h1 class="my-4">Account</h1>
            <div class="mt-5">
                <ul class="d-flex py-4 border-top border-bottom text-center px-3">
                    <c:forEach var="service" items="${requestScope.listService}">
                        <li class="me-5">
                            <a href="profile?Service=${service}"
                               <c:if test="${service==requestScope.current}">
                                   style="text-decoration: underline;
                                   text-decoration-color: var(--pink-color);
                                   "
                               </c:if>
                               <c:if test="${service!=requestScope.current}">
                                   style="text-decoration: none;"
                               </c:if>
                               class="fs-3 text-dark ">${service}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="">
                <!--mess of action-->
                <c:if test="${isSuccess!=null}">
                    <div class="fs-4 alert ${isSuccess==true?"alert-success":"alert-danger"}" role="alert">
                        ${mess}
                    </div>
                </c:if>
                <!--form if is account info-->
                <c:if test="${requestScope.current.equals('Account info')}">
                    <form action="profile" method="post">
                        <input name="Service" value="updateInfo" hidden>
                        <div class="h-100vh mt-5">
                            <h1 class="fw-bold">Account infomation</h1>
                            <div class="row py-5">
                                <div class="col-md-3 h-100">
                                    <div class="account-img position-relative">
                                        <%if(account.getAccount_image() == null) {%>
                                        <img src="./images/accountImg.png" alt="" id="boxImage">
                                        <%} else {%>
                                        <img src="./images/<%=account.getAccount_image()%>" alt="" id="boxImage">
                                        <%}%>
                                        <div class="change-userImg ">
                                            <i class='bx bx-image-add fs-3'></i>
                                            <span>Change image</span>
                                        </div>
                                        <input type="file" 
                                               onchange="inputImage(this)"
                                               accept="image/gif, image/jpeg, image/png"
                                               class="input-userImg"
                                               name="accountImage"
                                               >
                                        <input value="<%=account.getAccount_image()%>" name="beforeImage" type="hidden"/>
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="user-info">
                                        <div class="mt-5">
                                            <h3 class="fw-medium">First name</h3>
                                            <div class="input-group flex-nowrap">
                                                <input type="text" name="first_name"
                                                       value="<%=account.getFirst_name()%>" class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="Username">
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <h3 class="fw-medium">Last name</h3>
                                            <div class="input-group flex-nowrap">
                                                <input type="text" name="last_name"
                                                       value="<%=account.getLast_name()%>" class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="Username">
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <h3>Email</h3>
                                            <div class="input-group mb-3 rounded-xl">
                                                <span class="input-group-text p-4" id="basic-addon1">
                                                    <i class="fa-regular fa-envelope fs-3"></i>
                                                </span>
                                                <input type="text" name="account_email"
                                                       readonly
                                                       value="<%=account.getEmail()%>" class="form-control px-4 py-3 fs-3" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <h3>Addess</h3>
                                            <div class="input-group mb-3 rounded-xl">
                                                <span class="input-group-text p-4" id="basic-addon1">
                                                    <i class="fa-solid fa-location-dot fs-3"></i>
                                                </span>
                                                <input type="text" name="account_address"
                                                       value="<%=account.getAddress()%>" class="form-control px-4 py-3 fs-3" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <h3>Phone</h3>
                                            <div class="input-group mb-3 rounded-xl">
                                                <span class="input-group-text p-4" id="basic-addon1">
                                                    <i class="fa-solid fa-phone fs-3"></i>
                                                </span>
                                                <input type="text" name="account_phone"
                                                       value="<%=account.getPhone()%>"  class="form-control px-4 py-3 fs-3" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <h3>About you</h3>
                                            <div class="input-group mb-3">
                                                <textarea name="account_desc" id="" cols="30" rows="10" class="w-100"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <button type="submit" class="border-0 px-5 py-4 fs-4 bg-dark text-white rounded-xl fw-bold">Update infomation</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:if>
                <!--form if is Changepassword-->
                <c:if test="${requestScope.current.equals('Change password')}">
                    <div class="h-100vh mt-5">
                        <h1 class="fw-bold">Update your password</h1>
                        <div class="row py-5">
                            <div class="col-md-12">

                                <form action="profile" method="post">
                                    <input name="Service" value="updatePassword" hidden />
                                    <div class="change-password">
                                        <div class="mt-5">
                                            <h3 class="fw-medium">Current password</h3>
                                            <div class="input-group flex-nowrap">
                                                <input type="password" name="currentPassword"
                                                       class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="password">
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <h3 class="fw-medium">New password</h3>
                                            <div class="input-group flex-nowrap">
                                                <input type="password" name="newPassword"
                                                       class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="password">
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <h3 class="fw-medium">Confirm password</h3>
                                            <div class="input-group flex-nowrap">
                                                <input type="password" name="confirmPassword"
                                                       class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="password">
                                            </div>
                                        </div>
                                        <div class="mt-5">
                                            <button type="submit" class="border-0 px-5 py-4 fs-4 bg-dark text-white rounded-xl fw-bold">Update password</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${requestScope.current.equals('My order')}">
                    <c:forEach var="order" items="${myOrder}">
                        <div class="box-shadow1 py-2 px-3 rounded-sm mt-5 d-flex justify-content-between align-items-center">
                            <c:choose>
                                <c:when test="${order.status eq 'wait'}">
                                    <div><span class="fs-3 fw-bold">Status: </span> <span class="text-danger fs-4">${order.status}</span></div>
                                    </c:when>
                                    <c:when test="${order.status eq 'process'}">
                                    <div><span class="fs-3 fw-bold">Status: </span> <span class="text-warning  fs-4">${order.status}</span></div>
                                    </c:when>
                                    <c:when test="${order.status eq 'done'}">
                                    <div><span class="fs-3 fw-bold">Status: </span> <span class="text-success fs-4">${order.status}</span></div>
                                    </c:when>
                                    <c:otherwise>
                                    <div><span class="fs-3">Status: ${order.status}</span></div>
                                </c:otherwise>
                            </c:choose>
                            <span class="ms-auto fs-4 fw-medium text-black">${order.order_date}</span>
                        </div>
                        <c:forEach var="orderItem" items="${myOrderItem}">
                            <c:if test="${order.order_id == orderItem.order_id}">
                                <div class="h-100vh mt-5">
                                    <div class="col-md-12 border-end border-start pe-5">
                                        <div class="row order-item pb-5">
                                            <div class="col-2">
                                                <a href="ProductURL?Service=ProductDetail&Pid=${orderItem.getProduct().product_id}" class="d-block h-100">
                                                    <img src="./images/${orderItem.getProduct().product_img}" alt="" class="rounded-lg object-fit-cover">
                                                </a>
                                            </div>
                                            <div class="col-9 d-flex flex-fill flex-column justify-content-between ">
                                                <div class="h-50 d-flex align-items-center justify-content-between flex-fill">
                                                    <div class="">
                                                        <h3 class="fw-bold">${orderItem.getProduct().product_name}</h3>
                                                        <div class="d-flex align-items-center position-relative hover-change">
                                                            <!--                                                        <div class="">
                                                                                                                        <i class="fa-solid fa-fill-drip me-3"></i>
                                                                                                                        <span class="text-black"> a </span>
                                                                                                                    </div>
                                                                                                                    <div class="border-line border-l mx-3"></div>
                                                                                                                    <div class="">
                                                                                                                        <i class="fa-solid fa-store me-3"></i>
                                                                                                                        <span class="text-black"> a </span>
                                                                                                                    </div>-->
                                                        </div>
                                                    </div>
                                                    <div class="">
                                                        <span class="fs-4">đ${orderItem.list_price}</span>
                                                    </div>
                                                    <div class="d-flex align-items-center">
                                                        <div class="box-input">
                                                            <button class="border-0 bg-white">
                                                                <a class="p-3 text-black"><i class="fa-solid fa-minus fs-5"></i></a>
                                                            </button>
                                                            <input type="text" value="${orderItem.quantity}" class="input-number" id="numberValue" />
                                                            <button class="border-0 bg-white">
                                                                <a class="p-3 text-black"><i class="fa-solid fa-plus fs-5"></i></a>
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <div class="fs-4">
                                                        <!--//gia * sl-->
                                                        <span class="text-danger">đ${orderItem.orderItemPrice()}</span>
                                                    </div>
                                                </div>
                                                <div class="h-50 d-flex align-items-center justify-content-between">
                                                    <c:if test="${orderItem.getDiscount() > 0}">
                                                        <span class="text-white p-1 mx-2 fs-5 fw-bold bg-danger tag-sale">Giảm ${orderItem.getDiscount()}%</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </c:if>
            </div>
    </section>
    <%@include file="./Footer.jsp" %>
    <script src="./js/app.js"></script>
    <script>
                                                   function inputImage(input) {
                                                       var filePath = input.value;
                                                       var fileName = filePath.split('\\').pop();

                                                       var imagePreview = document.getElementById('boxImage');
                                                       imagePreview.src = "./images/" + fileName;
                                                       console.log(fileName);
                                                   }
    </script>
</body>

</html>