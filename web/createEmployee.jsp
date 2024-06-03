<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .modal-header {
            background-color: #ff69b4; /* Màu hồng */
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
            background-color: #ff69b4; /* Nền màu hồng cho nút */
            border-color: #ff69b4; /* Màu viền khớp */
        }
        .btn-success:hover {
            background-color: #ff85c0; /* Hồng nhạt hơn khi di chuột */
            border-color: #ff85c0; /* Màu viền khớp khi di chuột */
        }
    </style>
</head>
<body>
    <div id="addEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="add" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Create Employee</h4>
                        <button type="button" class="close" onclick="window.history.back(); $('#addEmployeeModal').modal('hide');" aria-hidden="true">&times;</button>
                    </div>
                    <p style="color: red; text-align: center;">${error}</p>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="e_name">Name</label>
                            <input type="text" name="e_name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="position">Position</label>
                            <select name="position" class="form-control" required>
                                <option value="1" ${ed != null && ed.position == 1 ? 'selected' : ''}>Marketing</option>
                                <option value="2" ${ed != null && ed.position == 2 ? 'selected' : ''}>Sales</option>
                                <option value="3" ${ed != null && ed.position == 3 ? 'selected' : ''}>Shipper</option>
                                
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="e_email">Email</label>
                            <input type="email" name="e_email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="e_phone">Phone</label>
                            <input type="text" name="e_phone" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="e_address">Address</label>
                            <input type="text" name="e_address" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select name="gender" class="form-control" required>
                                <option value="true" ${ed != null && ed.gender ? 'selected' : ''}>Male</option>
                                <option value="false" ${ed != null && !ed.gender ? 'selected' : ''}>Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="DOB">Date of Birth</label>
                            <input type="date" name="DOB" class="form-control" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" onclick="window.history.back(); $('#addEmployeeModal').modal('hide');">Cancel</button>
                        <button type="submit" class="btn btn-success">Create</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#addEmployeeModal').modal('show');
        });
    </script>
</body>
</html>
