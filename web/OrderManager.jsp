<%-- 
    Document   : Admin
    Created on : 13 Oct, 2023, 12:03:22 PM
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
        <title>Admin</title>
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
                            <a href="ManageOrder"  class="fs-3 text-white text-decoration-none">
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
                <h1 class="fw-bold my-4">Order</h1>
                <div class="w-50 bg-white d-flex align-items-center overflow-hidden">
                     <form action="orderManager"
                                class="w-50 bg-white border d-flex align-items-center rounded-md overflow-hidden">
                                <button type="submit" class="p-3 border-0 text-black-weak" name="Service"
                                        value="searchOrder"
                                        />
                                      <i class="fa-solid fa-magnifying-glass fs-3"></i>
                                </button>
                                <input type="text" placeholder="Search order by id" 
                                       value="${valueSearch}"
                                       class="border-0 w-100 py-4 px-3 fs-4"
                                       name="searchId"
                                />
                    </form>
                </div>
                <div class="row mt-5 ">
                    <div class="col-md-6">
                        <ul class="d-flex flex-wrap align-items-center">
                            <li class="px-4 py-2 rounded-xl border fs-4 me-3 display-order ${statusSearch==null?"choose":""}">
                                <a href="orderManager" class="text-black-weak text-decoration-none">
                                    All order
                                </a>
                            </li>
                            <li class="px-4 py-2 rounded-xl border fs-4 me-3 display-order ${statusSearch.equals('process')?"choose":""}">
                                <a href="orderManager?Service=searchByStatus&status=process" class="text-black-weak text-decoration-none">
                                    Process
                                </a>
                            </li>
                            <li class="px-4 py-2 rounded-xl border fs-4 me-3 display-order ${statusSearch.equals('wait')?"choose":""}">
                                <a href="orderManager?Service=searchByStatus&status=wait" class="text-black-weak text-decoration-none">
                                    Wait
                                </a>
                            </li>
                            <li class="px-4 py-2 rounded-xl border fs-4 display-order ${statusSearch.equals('done')?"choose":""}">
                                <a href="orderManager?Service=searchByStatus&status=done"  class="text-black-weak text-decoration-none">
                                    Done
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-6">
                        <select class="form-select w-50 ms-auto fs-3" aria-label="Default select example">
                            <option value="1">Order list by: Id</option>
                            <option value="2">Order list by: Date</option>
                            <option value="3">Order list by: Total</option>
                          </select>
                    </div>
                </div>
                <div class="">
                    <h3 class="text-success">${mess}</h3>
                    <div class="mt-5 table-header bg-weak rounded-xl ">
                        <div class="row fs-4 fw-bold py-4 px-5 d-flex align-items-center justify-content-between">
                            <div class="col">Bill ID</div>
                            <div class="col">Customer Name</div>
                            <div class="col">Date</div>
                            <div class="col">Total</div>
                            <div class="col">Status</div>
                            <div class="col">View</div>
                        </div>
                    </div>
                    <c:forEach items="${listOrder}" var="order">
                        <div class="mt-5 border-top bg-white rounded-lg border">
                        <div class="row fs-4 py-4 px-5 d-flex align-items-center justify-content-between">
                            <div class="col fw-bold">#${order.getOrder_id()}</div>
                            <div class="col d-flex align-items-center">
                                <div class="rounded-fill me-3">
                                    <c:if test="${order.getAccountImg() != null}">
                                        <img src="./images/${order.getAccountImg()}" alt="" 
                                         style="width: 50px; height: 50px; border-radius: 50%; object-fit: cover" %>
                                    </c:if>
                                    <c:if test="${order.getAccountImg() == null}">
                                        <img src="./images/accountImg.png" alt="" 
                                         style="width: 50px; height: 50px; border-radius: 50%; object-fit: cover" %>
                                    </c:if>
                                </div>
                                <span class="fs-4">${order.getAccountFullName()}</span>
                            </div>
                            <div class="col">${order.getOrder_date()}</div>
                            <div class="col">${order.getOrderTotal()} <span class="text-success">vnd</span></div>
                            <c:if test="${order.getStatus().equals('done')}">
                                <div class="col text-success">${order.getStatus()}</div>
                            </c:if>
                            <c:if test="${order.getStatus().equals('wait')}">
                                <div class="col text-danger">${order.getStatus()}</div>
                            </c:if >
                            <c:if test="${order.getStatus().equals('process')}">
                                <div class="col text-warning">${order.getStatus()}</div>
                            </c:if>
                            <div class="col">
                                <div class="d-flex align-items-center position-relative box-detail">
                                    <div class="">
                                        <span class="me-3">
                                            <a class="text-decoration-none text-black" href="orderManager?Service=detailOrder&OId=${order.getOrder_id()}">Detail</a>
                                        </span>
                                    </div>
                                    <div class="">
                                    <c:if test="${order.getStatus().equals('wait')}">
                                                        <select onchange="changeStatus([this, ${order.getOrder_id()}])">
                                                            <option value="process" ${order.getStatus().equals('process')?"selected":""}>process</option>
                                                            <option value="done" ${order.getStatus().equals('done')?"selected":""}>done</option>
                                                            <option value="wait" ${order.getStatus().equals('wait')?"selected":""}>wait</option>
                                                        </select>
                                    </c:if>
                                    <c:if test="${order.getStatus().equals('process')}">
                                             <select onchange="changeStatus([this, ${order.getOrder_id()}])">
                                                    <option value="wait"  ${order.getStatus().equals('wait')?"selected":""}>wait</option>
                                                    <option value="done" ${order.getStatus().equals('done')?"selected":""}>done</option>
                                                    <option value="process" ${order.getStatus().equals('process')?"selected":""}>process</option>
                                             </select>
                                    </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
          
                </div>
            </div>
                
        </section>
        <%--<%@include file="./Footer.jsp" %>--%>
        <script src="../js/app.js"></script>
        <script>
           const changeStatus = (param) => {
               let url = 'orderManager?Service=updateStatus&oId='+param[1]+"&status="+param[0].value
//               console.log(url);
               window.location = url
           }
        </script>
    </body>
</html>
