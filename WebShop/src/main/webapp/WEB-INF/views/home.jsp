<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Home</title>

        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">




    </head>
    <body>

        <div class="card">
            <div class="card-header">
                <nav class="navbar navbar-expand-sm navbar-light bg-light">


                    <a class="navbar-brand" href="#">
                        <img src="<c:url value='/static/photo/logo.png' />" width="40" height="40" alt="">
                    </a>
                    <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarMenu">
                        <ul class="navbar-nav nav-pills  ">
                            <li class="nav-item ">
                                <a class="nav-link  " href="<c:url value="/home" />">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link " href="<c:url value="/aboutus" />">About us</a>
                            </li>
                        </ul>

                        <ul class="navbar-nav nav-pills  ml-auto">
                            <li class="nav-item">
                                <a href="<c:url value="/login" />" class="nav-link">Login</a>
                            </li>
                        </ul>

                    </div>        
                </nav>
            </div>
            <!-- END header -->

            <div class="card-body">
                <div class="container">
                    <div class="row justify-content-md-center">
                        <div class="col-md-12 ">
                            <img src="<c:url value='/static/photo/cover.jpg' />" class="img-fluid img-thumbnail">
                        </div>
                    </div>
                </div>
            </div>
            <!-- END body -->

            <%@include file="footer.jsp" %>
            <!-- END footer -->
        </div>
                        
        <!--JavaScript -->
        <script src="<c:url value='/static/js/jquery-3.5.1.slim.min.js' />"></script>
        <script src="<c:url value='/static/js/bootstrap.bundle.min.js' />"></script>



    </body>
</html>

