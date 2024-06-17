<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
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
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .add-blog-btn {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 20px;
            padding: 10px 20px;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .add-blog-btn:hover {
            background-color: #0056b3;
        }
        .search-bar {
            display: flex;
        }
        .search-bar .form-control {
            border-radius: 20px 0 0 20px;
            border-right: none;
            height: calc(2.5rem + 2px);
        }
        .search-bar .btn {
            border-radius: 0 20px 20px 0;
            background-color: #007bff;
            color: white;
            border: none;
            height: calc(2.5rem + 2px);
            font-size: 1rem;
            padding: 0 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: background-color 0.3s, box-shadow 0.3s;
        }
        .search-bar .btn:hover {
            background-color: #0056b3;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .blog-container {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
        }
        .blog-item {
            border: 1px solid #e0e0e0;
            border-radius: 10px;
            overflow: hidden;
            background-color: white;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }
        .blog-item:hover {
            transform: translateY(-5px);
        }
        .blog-item img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .blog-item p {
            margin: 10px;
            font-size: 14px;
            color: #555;
        }
        .blog-item p.author {
            font-weight: 500;
        }
        .pagination {
            margin-top: 20px;
        }
        .pagination .page-item .page-link {
            color: #5a5a5a;
        }
        .pagination .page-item.active .page-link {
            background-color: #007bff;
            border-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <section>
        <div class="container">
            <c:if test="${message != null}">
                <p style="color: #5cb85c">${message}</p>
            </c:if>

            <!-- Top Bar -->
            <div class="top-bar">
                <a href="addBlog.jsp" class="add-blog-btn">Add New Blog</a>
                <div class="search-bar">
                    <input type="text" class="form-control" placeholder="Search..."/>
                    <button class="btn" type="button">Search</button>
                </div>
            </div>

            <!-- Blog Container -->
            <div class="blog-container">
                <c:forEach var="blog" items="${listBlog}">
                    <a href="detailBlog?blogid=${blog.blog_id}" style="text-decoration: none;color: black;">
                        <div class="blog-item">
                            <img src="${blog.image != null ? blog.image : 'https://via.placeholder.com/300'}" alt="Blog Image"/>
                            <p class="author">Author: ${blog.author_id}</p>
                            <p>Created At: ${blog.created_at}</p>
                        </div>
                    </a>
                </c:forEach>
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
    </section>
    <%@ include file="./Footer.jsp" %>
</body>
</html>
