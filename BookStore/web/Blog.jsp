<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Management</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;1,100;1,300;1,400&display=swap" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="./style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
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
        .search-bar {
            display: flex;
            max-width: 300px;
        }
        .search-bar .form-control {
            border-radius: 20px 0 0 20px;
            border-right: none;
        }
        .search-bar .btn {
            border-radius: 0 20px 20px 0;
            background-color: palevioletred;
            color: white;
            border: none;
        }
        .search-bar .btn:hover {
            background-color: #d46a89;
        }
        .header {
            background-color: white;
        }
    </style>
</head>
<body>
    <style>
        .header {
            background-color: white;
        }
    </style>
    <%@include file="./Header.jsp" %>
    <h3 class="text-danger">${requestScope.mess?requestScope.mess:""}</h3>
    <section>
        <div class="row h-100">
            <div style="background-color: #ff00a6" class="col-md-2 h-100 p-0">
                <div class="p-5 pe-0 vh-100">
                    <ul>
                        <li class="py-4 ps-3 mb-3">
                            <a href="" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                                <i class='bx bxs-dashboard me-3'></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="py-4 ps-3 mb-3">
                            <a href="blog.jsp" class="fs-3 text-white d-flex align-items-center text-decoration-none">
                                <i class='bx bxs-book-content me-3'></i>
                                <span>Manage Blogs</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-10 p-4">
                <div class="container p-5">
                    <div class="card">
                        <div style="background-color: palevioletred">
                            <div class="card-title d-flex justify-content-between p-5 pb-2 pt-3 text-light">
                                <h3>Manage Blog</h3>
                            </div>
                        </div>
                        <!-- Search Form -->
                        <form action="blog" method="get" class="p-3">
                            <div class="search-bar">
                                <input type="text" class="form-control" name="searchText" placeholder="Search by title" value="${param.searchText}">
                                <button class="btn btn-primary" type="submit">Search</button>
                            </div>
                        </form>
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
                                                <th scope="col">Image</th>
                                                <th scope="col">Title</th>
                                                <th scope="col">Content</th>
                                                <th scope="col">Author ID</th>
                                                <th scope="col">Created At</th>
                                                <th scope="col">Approved</th>
                                                <th scope="col">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="blog" items="${blog}">
                                                <tr>
                                                    <td>${blog.blog_id}</td>
                                                    <td><img src="${blog.image}" alt="Blog Image"/></td>
                                                    <td>${blog.title}</td>
                                                    <td>${blog.content}</td>
                                                    <td>${blog.author_id}</td>
                                                    <td>${blog.created_at}</td>
                                                    <td>${approvedBlogs.contains(blog.blog_id) ? "Yes" : "No"}</td>
                                                    <td>
                                                        <a href="blog?action=approve&id=${blog.blog_id}" class="approve">
                                                            <i class="fas fa-check p-2 text-success"></i>
                                                        </a>
                                                        <a href="#" onclick="confirmDelete(${blog.blog_id})" class="delete" data-toggle="modal">
                                                            <i class="fas fa-trash p-2 text-danger"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <script>
                                        function confirmDelete(id) {
                                            var op = confirm("Do you want to delete?");
                                            if (op === true) {
                                                window.location.href = 'deleteBlog?id=' + id;
                                            }
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pagination -->
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:if test="${pageIndex > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="BlogServlet?pageIndex=${pageIndex - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${totalPage}">
                                <li class="page-item ${i == pageIndex ? 'active' : ''}">
                                    <a class="page-link" href="BlogServlet?pageIndex=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${pageIndex < totalPage}">
                                <li class="page-item">
                                    <a class="page-link" href="BlogServlet?pageIndex=${pageIndex + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <%@include file="./Footer.jsp" %>
</body>
</html>
