<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Blog</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .modal-header {
            background-color: #007bff; /* Màu xanh */
            color: white;
            border-bottom: none; /* Tuỳ chọn: bỏ đường viền dưới nếu muốn */
        }
        .close {
            color: white;
            text-shadow: none; /* Bỏ bóng */
            opacity: 1; /* Làm cho nó hoàn toàn trong suốt */
        }
        .close:hover {
            color: #fff;
        }
        .modal-footer {
            display: flex;
            justify-content: space-between;
        }
        .btn-success {
            background-color: #007bff; /* Nền màu xanh cho nút */
            border-color: #007bff; /* Màu viền khớp */
        }
        .btn-success:hover {
            background-color: #0056b3; /* Xanh nhạt hơn khi di chuột */
            border-color: #0056b3; /* Màu viền khớp khi di chuột */
        }
    </style>
</head>
<body>
    <div id="addBlogModal" class="modal fade show" style="display: block;">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="addBlog" method="post" enctype="multipart/form-data">
                    <div class="modal-header">
                        <h4 class="modal-title">Create Blog</h4>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="alert alert-success" role="alert">
                            ${message}
                        </div>
                    </c:if>
                    <p style="color: red; text-align: center;">${error}</p>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input type="text" name="title" class="form-control" value="${param.title != null ? param.title : ''}" required>
                        </div>
                        <div class="form-group">
                            <label for="author_id">Author ID</label>
                            <input type="text" name="author_id" class="form-control" value="${param.author_id != null ? param.author_id : ''}" required>
                        </div>
                        <div class="form-group">
                            <label for="content">Content</label>
                            <textarea name="content" class="form-control" rows="5" required>${param.content != null ? param.content : ''}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="image">Image</label>
                            <input type="file" name="image" class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" onclick="window.history.back();">Cancel</button>
                        <button type="submit" class="btn btn-success">Create</button>
                    </div>
                </form>
                <div>
                    
                </div>
            </div>
        </div>
    </div>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#addBlogModal').modal('show');
        });
    </script>
</body>
</html>
