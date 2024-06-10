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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="./style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
    </head>
    <body>
        <%@include file="./Header.jsp" %>
        <section>
            <div class="w-60 m-auto">
                <h1 class="my-4">Profile</h1>
                <div class="mt-5">
                    <ul class="d-flex py-4 border-top border-bottom text-center px-3">
                        <c:forEach var="service" items="${listService}">
                            <li class="me-5">
                                <a href="profile?Service=${service}"
                                   <c:if test="${service == current}">
                                       style="border-bottom: 5px solid var(--pink-color); text-decoration: none;"
                                   </c:if>
                                   <c:if test="${service != current}">
                                       style="text-decoration: none;"
                                   </c:if>
                                   class="fs-3 text-dark">${service}</a>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
                <div class="">
                    <!-- Message of action -->
                    <c:if test="${isSuccess != null}">
                        <div class="fs-4 alert ${isSuccess ? 'alert-success' : 'alert-danger'}" role="alert">
                            ${mess}
                        </div>
                    </c:if>

                    <!-- Form for Account Info -->
                    <c:if test="${'Account info'.equals(current)}">
                        <form action="profile" method="post">
                            <input name="Service" value="updateInfo" type="hidden">
                            <div class="h-100vh mt-5">
                                <h1 class="fw-bold">Account information</h1>
                                <div class="row py-5">
                                    <div class="col-md-3 h-100">
                                        <div class="account-img position-relative">
                                            <c:choose>
                                                <c:when test="${account.account_image == null}">
                                                    <img src="./images/avata.jpg" alt="" id="boxImage">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="./images/${account.account_image}" alt="" id="boxImage">
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="change-userImg">
                                                <i class='bx bx-image-add fs-3'></i>
                                                <span>Change image</span>
                                            </div>
                                            <input type="file" 
                                                   onchange="inputImage(this)"
                                                   accept="image/gif, image/jpeg, image/png, image/jpg"
                                                   class="input-userImg"
                                                   name="accountImage">
                                            <input value="${account.account_image}" name="beforeImage" type="hidden"/>
                                        </div> 

                                        <script>
                                            function inputImage(input) {
                                                if (input.files && input.files[0]) {
                                                    const reader = new FileReader();
                                                    reader.onload = function (e) {
                                                        document.getElementById('boxImage').src = e.target.result;
                                                    }
                                                    reader.readAsDataURL(input.files[0]);
                                                }
                                            }
                                        </script>

                                    </div>
                                    <div class="col-md-9">
                                        <div class="user-info">
                                            <div class="mt-5">
                                                <h3 class="fw-medium">First name</h3>
                                                <div class="input-group flex-nowrap">
                                                    <input type="text" name="first_name"
                                                           value="${account.first_name}" class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="First name">
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3 class="fw-medium">Last name</h3>
                                                <div class="input-group flex-nowrap">
                                                    <input type="text" name="last_name"
                                                           value="${account.last_name}" class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="Last name">
                                                </div>
                                            </div>

                                            <div class="mt-5" style="color: black;">
                                                <h3>Email</h3>
                                                <div class="input-group mb-3 rounded-xl">
                                                    <span class="input-group-text p-4" id="basic-addon1">
                                                        <i class="fa-regular fa-envelope fs-3" style="color: #808080;"></i>
                                                    </span>
                                                    <input type="text" name="account_email" readonly value="${account.email}" class="form-control px-4 py-3 fs-3" placeholder="Email" aria-label="Email" aria-describedby="basic-addon1" style="color: #808080;">
                                                </div>
                                            </div>



                                            <div class="mt-5">
                                                <h3>Address</h3>
                                                <div class="input-group mb-3 rounded-xl">
                                                    <span class="input-group-text p-4" id="basic-addon1">
                                                        <i class="fa-solid fa-location-dot fs-3"></i>
                                                    </span>
                                                    <input type="text" name="account_address"
                                                           value="${account.address}" class="form-control px-4 py-3 fs-3" placeholder="Address" aria-label="Address" aria-describedby="basic-addon1">
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3>Phone</h3>
                                                <div class="input-group mb-3 rounded-xl">
                                                    <span class="input-group-text p-4" id="basic-addon1">
                                                        <i class="fa-solid fa-phone fs-3"></i>
                                                    </span>
                                                    <input type="text" name="account_phone"
                                                           value="${account.phone}"  class="form-control px-4 py-3 fs-3" placeholder="Phone" aria-label="Phone" aria-describedby="basic-addon1">
                                                </div>
                                            </div>

                                            <div class="mt-5">
                                                <h3>About you</h3>
                                                <div class="input-group mb-3">
                                                    <textarea name="account_description" id="account_description" cols="30" rows="10" class="w-100">${account.description}</textarea>
                                                </div>
                                            </div>    
                                          
                                        </div>
                                        <div class="mt-5">
                                            <button type="submit" class="border-0 px-5 py-4 fs-4 bg-dark text-white rounded-xl fw-bold">Update information</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:if>

                    <!-- Form for Change Password -->
                    <c:if test="${'Change password'.equals(current)}">
                        <div class="h-100vh mt-5">
                            <h1 class="fw-bold">Update your password</h1>
                            <div class="row py-5">
                                <div class="col-md-12">
                                    <form action="profile" method="post">
                                        <input name="Service" value="updatePassword" type="hidden" />
                                        <div class="change-password">
                                            <div class="mt-5">
                                                <h3 class="fw-medium">Current password</h3>
                                                <div class="input-group flex-nowrap">
                                                    <input type="password" name="currentPassword"
                                                           class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="Current password">
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3 class="fw-medium">New password</h3>
                                                <div class="input-group flex-nowrap">
                                                    <input type="password" name="newPassword"
                                                           class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="New password">
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3 class="fw-medium">Confirm password</h3>
                                                <div class="input-group flex-nowrap">
                                                    <input type="password" name="confirmPassword"
                                                           class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="Confirm password">
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

                    <!-- Form for Update Preferences -->
                    <c:if test="${'Preferences'.equals(current)}">
                        <div class="h-100vh mt-5">
                            <h1 class="fw-bold">Preferences</h1>
                            <div class="row py-5">
                                <div class="col-md-12">
                                    <form action="profile" method="post">
                                        <input name="Service" value="updatePreferences" type="hidden" />
                                        <div class="preferences">
                                            <div class="mt-5">
                                                <h3 class="fw-medium">Language</h3>
                                                <div class="input-group flex-nowrap">
                                                    <select class="form-control px-4 py-3 fs-3 rounded-xl" name="language">
                                                        <option value="en" ${preferences.language == 'en' ? 'selected' : ''}>English</option>
                                                        <option value="es" ${preferences.language == 'es' ? 'selected' : ''}>Spanish</option>
                                                        <option value="fr" ${preferences.language == 'fr' ? 'selected' : ''}>French</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3 class="fw-medium">Time Zone</h3>
                                                <div class="input-group flex-nowrap">
                                                    <select class="form-control px-4 py-3 fs-3 rounded-xl" name="timezone">
                                                        <c:forEach var="tz" items="${timezones}">
                                                            <option value="${tz}" ${preferences.timezone == tz ? 'selected' : ''}>${tz}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <h3 class="fw-medium">Notifications</h3>
                                                <div class="input-group flex-nowrap">
                                                    <div class="form-check form-switch">
                                                        <input class="form-check-input" type="checkbox" id="emailNotifications" name="emailNotifications" ${preferences.emailNotifications ? 'checked' : ''}>
                                                        <label class="form-check-label fs-3" for="emailNotifications">Email Notifications</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-5">
                                                <button type="submit" class="border-0 px-5 py-4 fs-4 bg-dark text-white rounded-xl fw-bold">Update Preferences</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </section>
        <%@include file="./Footer.jsp" %>
    </body>
</html>
