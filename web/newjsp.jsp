<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        .pass_show {
            position: relative;
        }

        .pass_show .ptxt {
            position: absolute;
            top: 50%;
            right: 10px;
            z-index: 1;
            color: #f36c01;
            margin-top: -10px;
            cursor: pointer;
            transition: .3s ease all;
        }

        .pass_show .ptxt:hover {
            color: #333333;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <label>Current Password</label>
                <div class="form-group pass_show"> 
                    <input type="password" value="faisalkhan@123" class="form-control" placeholder="Current Password"> 
                </div> 
                <label>New Password</label>
                <div class="form-group pass_show"> 
                    <input type="password" value="faisal.khan@123" class="form-control" placeholder="New Password"> 
                </div> 
                <label>Confirm Password</label>
                <div class="form-group pass_show"> 
                    <input type="password" value="faisal.khan@123" class="form-control" placeholder="Confirm Password"> 
                </div> 
            </div>  
        </div>
    </div>

    <script>
        $(document).ready(function(){
            $('.pass_show').append('<span class="ptxt">Show</span>');  
        });

        $(document).on('click', '.pass_show .ptxt', function(){ 
            $(this).text($(this).text() == "Show" ? "Hide" : "Show"); 
            $(this).prev().attr('type', function(index, attr){ return attr == 'password' ? 'text' : 'password'; }); 
        });
    </script>
</body>
</html>