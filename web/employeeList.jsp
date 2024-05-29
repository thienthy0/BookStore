
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
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management</title>
     <link
      href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;1,100;1,300;1,400&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
      integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        img {
            width: 200px;
            height: 120px;
        }
        .thong-ke {
            border: 1px solid black;
            border-radius: 10px;
            text-align: center;
        }
    </style>
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
    
    
    <div class="page-wrapper">
        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container">
                    <div class="row m-t-25" style="margin-top: 30px">
                        <!-- NỘI DUNG CHÍNH -->
                    </div>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT-->

        <!-- Employee List -->
        <div class="container p-5">
   
      <div class="card">
        <div style="background-color: palevioletred">
          <div
            class="card-title d-flex justify-content-between p-5 pb-2 pt-3 text-light"
          >
            <h3>Manage Employee</h3>
            <a
              href="createEmployee.jsp"
              class="btn btn-success"
              data-toggle="modal"
            >
              <button class="btn btn-success fs-6">
                <i class="fas fa-plus-circle"></i>
                <span class="ps-2">Add New Employee</span>
              </button>
            </a>
          </div>
        </div>
        <!-- Message -->
        <c:if test="${message != null}">
          <p style="color: #5cb85c">${message}</p>
        </c:if>
        <div class="card-body bg-light">
          <div class="row">
            <div class="col-sm-12">
              <table class="table table-hover text-center">
                <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Position</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Address</th>
                    <th scope="col">Gender</th>
                    
                    <th scope="col">Date Of Birth</th>
                    <th scope="col">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="e" items="${listEmployee}">
                    <tr>
                      <td>${e.e_id}</td>
                      <td>${e.e_name}</td>
                      <td>${e.position}</td>
                      <td>${e.e_email}</td>
                      <td>${e.e_phone}</td>
                      <td>${e.e_address}</td>
                      <td>${e.gender ? 'Male' : 'Female'}</td>
                      
                      <td>${e.DOB}</td>
                      <td>
                        <a
                          href="edit?e_id=${e.e_id}"
                          class="edit"
                          data-toggle="modal"
                          ><i class="fas fa-pen p-2 text-warning"></i
                        ></a>
                        <a
                          href="#"
                          onclick="showMess(${e.e_id})"
                          class="delete"
                          data-toggle="modalde"
                          ><i class="fas fa-trash p-2 text-danger"></i
                        ></a>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
              <script>
                function showMess(id){
                    var op = confirm("Do you wan to delete?");
                    if(op === true){
                        window.window.location.href='delete?e_id='+id;
                    }
                }
            </script>
            </div>
          </div>
        </div>
      </div>
    </div>
        <!-- Add Employee Modal -->
        <div id="addEmployeeModal" class="modal fade">
            <!-- Add Employee Modal Content -->
        </div>
        <!-- Edit Employee Modal -->
        <div id="editEmployeeModal" class="modal fade">
            <!-- Edit Employee Modal Content -->
        </div>
    </div>
    <script src="./assets/js/manager.js" type="text/javascript"></script>
   <%@include file="./Footer.jsp" %> 
</body>
</html>