<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

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

                    </div>        
                </nav>
            </div>
            
        <div class="card-body">
        <div class="container-fluid">
            <div class="row">
                <div class="offset-md-4 col-md-4">
                    <h2>Login</h2>

                    <div class="login-form">
                        <c:url var="loginUrl" value="/login" />
                        <form action="${loginUrl}" method="post" >

                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>

                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label  for="userName"><h1><i class="bi bi-person-fill"></i></h1></label>
                                <input type="text" class="form-control" id="userName" name="userName" placeholder="Enter Username"  required>
                            </div>
                            <div class="form-group">
                                <label for="password"><h1><i class="bi bi-lock-fill"></i></h1></label> 
                                <input type="password" class="form-control"  id="password" name="password" placeholder="Enter Password" required>
                            </div>
                            <div class="form-group form-check">
                                <label><input type="checkbox" class="form-check-input" id="rememberme" name="remember-me"> Remember Me</label>  
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />

                            <div>
                                <input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
                            </div>
                        </form>
                    </div>

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
