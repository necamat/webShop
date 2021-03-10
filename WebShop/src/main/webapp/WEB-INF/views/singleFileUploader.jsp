
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Photo upload</title>

        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">


    </head>
    <body>
        <div class="card">
        <%@include file="header.jsp" %>
        <!-- END header -->
       
            <div class="card-body">
                <div class="container">
                   
                        <h1>Photo upload</h1>
                        <form:form modelAttribute="fileBucket" enctype="multipart/form-data" method="POST">

                            <div class="form-row">
                                <div class="form-group col-md-4"> 


                                    <form:input type="file" path="file" id="file" accept="image/x-png,image/gif,image/jpeg" class="form-control-file"/>   
                                    <form:errors path="file" class="text-danger"/>

                                </div>  
                            </div>
                            <input type="submit" value="Upload" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/allproducts' />" class="text-decoration-none">Cancel</a> 
                        </form:form>

                   
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
