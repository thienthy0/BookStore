<%-- 
    Document   : Footer
    Created on : 13 Oct, 2023, 11:31:29 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <!--<link rel="stylesheet" href="./style.css">-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"
              />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <section id="footer" class="mt-5">
        <div class="container">
            <div class="row">
                <div class="col-md-4 position-relative">
                    <h4 class="fw-bold mb-4">Contact</h4>
                    <p class="fs-4"><strong>Address:</strong> 562 Wellington Road Street 32, San Fransica</p>
                    <p class="fs-4"><strong>Phone:</strong> +01 2222 365 /(+91) 01 2345 6329</p>
                    <p class="fs-4"><strong>Hours:</strong> 10:00 - 18:00, Mon - Sat</p>
                    <div class="follow">
                        <h4>Follow Us</h4>
                        <div class="icons mt-5">
                            <i class="bx bxl-facebook me-2 fs-3"></i>
                            <i class="bx bxl-twitter me-2 fs-3"></i>
                            <i class="bx bxl-instagram me-2 fs-3"></i>
                            <i class="bx bxl-pinterest-alt me-2 fs-3"></i>
                            <i class="bx bxl-youtube me-2 fs-3"></i>
                        </div>
                    </div>
                    <div class="footer-logo">
                        <img src="./images/Logo.jpg" class="" alt="">
                    </div>
                </div>
                <div class="col-md-2">
                    <h4 class="fw-bold mb-4">About</h4>
                    <ul>
                        <li><a href="#">About us</a></li>
                        <li><a href="#">Delivery Inforation</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                        <li><a href="#">Terms &amp; Conditions</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                </div>
                <div class="col-md-2">
                    <h4 class="fw-bold mb-4">My Account</h4>
                    <ul>
                        <li><a href="#">Sign In</a></li>
                        <li><a href="#">View Cart</a></li>
                        <li><a href="#">My Wishlist</a></li>
                        <li><a href="#">Track My Order</a></li>
                        <li><a href="#">Help</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h2 class="fw-bold mb-4">Install App</h2>
                    <p class="my-4 desc-app">From App Store or Google Play</p>
                    <div class="d-flex">
                        <div class="row">
                            <div class="col">
                                <a href="" class="footer-app">
                                    <img src="./images/appStore.png" alt="">
                                </a>
                            </div>
                            <div class="col">
                                <a href="" class="footer-app">
                                    <img src="./images/googlePlay.png" alt="">
                                </a>
                            </div>
                        </div>
                    </div>
                    <p class="my-4 desc-app">Secured Payment Gaeways</p>
                    <div>
                        <a href="" class="d-block">
                            <img src="./images/pay.png" alt="" class="h-auto">
                        </a>
                    </div>
                </div>
                <div class="col-12">
                    <div class="copyright text-center">
                        <hr>
                        <p class="copyright">@ 2021, Tech2 etc - HTML CSS Ecoomerce Template</p>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </section>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

        <!-- Initialize Swiper -->
        <script>
            var swiper = new Swiper(".mySwiper", {
                spaceBetween: 30,
                centeredSlides: true,
                loop: true,
                speed: 1500,

                autoplay: {
                    delay: 5000,
                    disableOnInteraction: false,
                },
                pagination: {
                    el: ".swiper-pagination",
                    clickable: true,
                },
                navigation: {
                    nextEl: ".swiper-button-next",
                    prevEl: ".swiper-button-prev",
                },
            });
        </script>
        
        <script>
        </script>
        <script src="./js/app.js"></script>
    </body>

</html>