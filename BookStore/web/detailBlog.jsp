<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
    body {
        font-family: 'Roboto', sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
    }
    .container {
        max-width: 900px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    header h3 {
        font-family: 'Varela Round', sans-serif;
        font-size: 24px;
        color: #333;
        margin-bottom: 20px;
    }
    .card-img-top {
        width: 100%;
        height: auto;
        border-radius: 8px;
        object-fit: cover;
    }
    .cart-title {
        font-size: 28px;
        font-weight: bold;
        margin-bottom: 10px;
        color: #007bff;
    }
    .single_blog_desc {
        margin-top: 20px;
    }
    .blog-meta-data {
        margin-bottom: 20px;
    }
    .blog-date, .author {
        font-size: 14px;
        color: #6c757d;
        margin-bottom: 10px;
    }
    .line {
        width: 100%;
        height: 2px;
        background-color: #007bff;
        margin-bottom: 10px;
    }
    .content p {
        font-size: 16px;
        color: #333;
        line-height: 1.6;
    }
    .add-comment h6 {
        font-size: 18px;
        margin-bottom: 10px;
        color: #333;
    }
    .form-control {
        width: 100%;
        padding: 10px;
        border-radius: 4px;
        border: 1px solid #ced4da;
    }
    .form-control:focus {
        border-color: #007bff;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }
    .back-button {
        display: inline-block;
        padding: 10px 20px;
        margin-top: 20px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 4px;
        text-align: center;
    }
    .back-button:hover {
        background-color: #0056b3;
    }
</style>

<div class="container my-5">
    <header class="mb-4">
        <h3>Blog Detail</h3>
    </header>
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
        <div style="flex: 1 1 300px;">
            <img src="${blog.image}" class="card-img-top" alt="${blog.title}" />
        </div>
        <div style="flex: 2 1 300px; padding-left: 20px;">
            <h5 class="cart-title">${blog.title}</h5>
            <div class="single_blog_desc">
                <!-- Blog Meta Data -->
                <div class="blog-meta-data">
                    <div class="line"></div>
                    <p class="blog-date">${blog.created_at}</p>
                    <!-- Author -->
                    <p class="author">By: <c:out value="${blog.author_id}"/></p>
                    <div class="content">
                        <p>${blog.content}</p>
                    </div>
                </div>
                <!-- Add Comment Form -->
                <!-- Back Button -->
                <a href="listBlog" class="back-button">Back </a>
            </div>
        </div>
    </div>
</div>
