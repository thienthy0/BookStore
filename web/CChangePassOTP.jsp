<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Change Password</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

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
        <div class="form-gap"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="text-center">
                                <h3><i class="fa fa-lock fa-4x"></i></h3>
                                <h2 class="text-center">Change Password?</h2>
                                <p>You can reset your password here.</p>
                                <div class="panel-body">
                                    <form action="CChangePassOTP" method="post">
                                        <div class="form-group">
                                            <p style="color: red">${message}</p>
                                            <label>OTP Code</label>
                                            <input type="text" class="form-control" id="cOTP" name="cOTP" placeholder="Enter here" required>
                                            <label>Your new password</label>
                                            <div class="form-group pass_show"> 
                                                <input type="password" class="form-control" id="cNewPass" name="cNewPass" placeholder="Enter here" required>
                                            </div>             
                                            <label>Confirm password</label>
                                            <div class="form-group pass_show"> 
                                                <input type="password" class="form-control" id="cConfirmPass" name="cConfirmPass" placeholder="Enter here" required> 
                                            </div> 

                                        </div>
                                        <button type="submit" class="btn btn-primary">Save</button>
                                        <a href="Clogin.jsp" class="btn btn-primary">Back</a>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $('.pass_show').append('<span class="ptxt">Show</span>');
            });

            $(document).on('click', '.pass_show .ptxt', function () {
                $(this).text($(this).text() == "Show" ? "Hide" : "Show");
                $(this).prev().attr('type', function (index, attr) {
                    return attr == 'password' ? 'text' : 'password';
                });
            });
        </script>
    </body>
</html>