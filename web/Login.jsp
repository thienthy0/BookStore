<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./style.css">
</head>
<body>
    <section id="header" class="header-login">
            <div class="container">
                <div class="row align-items-center h-78">
                    <div class="col-md-1">
                        <div class="header-logo">
                            <a href="BookURL"><img src="./images/Logo.jpg" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    <section class="signup-body">
        <form action="login" method="post" class="mx-auto">
            <div class="bg-white p-5 form-login">
                <h1 class="text-center fw-bold">Login</h1>
                <div class="border-bottom">
                    <span class="fs-5 fw-bold text-black-weak">Username</span>
                    <div class="d-flex align-items-center fs-4 mt-3">
                        <i class='bx bx-user'></i>
                        <input type="text" name="username" placeholder="Username" class="w-100 py-4 outline-0 border-0 ms-3" required />
                    </div>
                </div>
                <div class="border-bottom mt-5">
                    <span class="fs-5 fw-bold text-black-weak">Password</span>
                    <div class="d-flex align-items-center fs-4 mt-3">
                        <i class='bx bx-lock'></i>
                        <input type="password" name="password" placeholder="Password" class="w-100 py-4 outline-0 border-0 ms-3" required />
                    </div>
                </div>
                <div class="text-center mt-3 fs-5">
                    Do you want to create an account? <a href="signup" class="text-black-weak text-info">Sign up</a>
                </div>
                <button type="submit" class="d-flex align-items-center justify-content-center mt-5 fw-bold py-2 fs-4 px-5 btn bg-danger text-white mx-auto">Submit</button>
            </div>
        </form>
        <div class="error-message">${mess!=null?mess:""}</div>
    </section>
</body>
</html>
