<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>Access denied page</title>
        
        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
        
    </head>
    <body>
        <div class="card">
        <%@include file="header.jsp" %>
        <!-- END header -->

            <div class="card-body">
                <div class="container">
                   
                    <div class="row">
                        <div class="col-md-8 offset-md-2">
                           
                            <div class="alert alert-danger " role="alert">
                                <span>Dear <strong>${loggedinuser}</strong>, You are not authorized to access this page.</span>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
         <!-- END body -->

        <%@include file="footer.jsp" %>
        <!-- END footer -->
        </div>
        <script src="<c:url value='/static/js/jquery-3.5.1.slim.min.js' />"></script>
        <script src="<c:url value='/static/js/bootstrap.bundle.min.js' />"></script>
        
        
    </body>
</html>