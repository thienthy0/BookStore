<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .modal-header {
            background-color: #ff69b4; /* Changed to pink */
            color: white;
        }
        .modal-footer {
            display: flex;
            justify-content: space-between;
        }
        .btn-success {
            background-color: #ff69b4; /* Pink background for the button */
            border-color: #ff69b4; /* Matching border color */
        }
        .btn-success:hover {
            background-color: #ff85c0; /* Lighter pink on hover */
            border-color: #ff85c0; /* Matching border color on hover */
        }
    </style>
</head>
<body>
    <div id="addEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="edit" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Update Employee</h4>
                        <button type="button" class="close" onclick="$('#addEmployeeModal').modal('hide'); window.history.back();" aria-hidden="true">&times;</button>
                    </div>
                    <!-- Display error message if any -->
                    <p style="color: red; text-align: center;">${error}</p>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="e_id">ID</label>
                            <input type="text" value="${ed.e_id}" name="e_id" class="form-control" required readonly>
                        </div>
                        <div class="form-group">
                            <label for="e_name">Name</label>
                            <input type="text" value="${ed.e_name}" name="e_name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="position">Position</label>
                            <select name="position" class="form-control" required >
                                <option value="1" ${ed.position == 1 ? 'selected' : ''}>Marketing</option>
                                <option value="2" ${ed.position == 2 ? 'selected' : ''}>Sales</option>
                                <option value="3" ${ed.position == 3 ? 'selected' : ''}>Shipper</option>
                               
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="e_email">Email</label>
                            <input type="email" value="${ed.e_email}" name="e_email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="e_phone">Phone</label>
                            <input type="text" value="${ed.e_phone}" name="e_phone" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="e_address">Address</label>
                            <input type="text" value="${ed.e_address}" name="e_address" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select name="gender" class="form-control" required>
                                <option value="true" ${ed.gender ? 'selected' : ''}>Male</option>
                                <option value="false" ${!ed.gender ? 'selected' : ''}>Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="DOB">Date of Birth</label>
                            <input type="date" value="${ed.DOB}" name="DOB" class="form-control" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" onclick="window.history.back();">Cancel</button>
                        <button type="submit" class="btn btn-success">Update</button>
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
