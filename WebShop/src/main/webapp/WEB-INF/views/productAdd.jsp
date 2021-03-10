<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head lang="en">
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Product registration</title>

        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">

    </head>
    <body>

        <div class="card">
            <%@include file="header.jsp" %>
            <!-- END header -->

            <div class="card-body">
                <div class="container">
                    <div class="col-md-6 offset-md-3 ">
                        <h1>Product registration</h1>



                        <form:form method="POST" modelAttribute="product" >

                            <div class="form-row">
                                <div class="form-group col-md-12">

                                    <form:label path="name">Name</form:label>
                                    <form:input path="name" id="name" type="text" class="form-control"/>
                                    <form:errors path="name" class="text-danger"/>

                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-12">

                                    <form:label path="quantity">Quantity</form:label>
                                    <form:input path="quantity" id="quantity" type="text" title="0" class="form-control"/>
                                    <form:errors path="quantity" class="text-danger"/>

                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-12">

                                    <form:label path="price">Price</form:label>
                                    <form:input path="price" id="price" type="text" class="form-control"/>
                                    <form:errors path="price" class="text-danger"/>

                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-12">

                                    <form:label path="description">Description</form:label>
                                    <form:textarea path="description" id="description" type="text" class="form-control"/>
                                    <form:errors path="description" class="text-danger"/>

                                </div>  
                            </div>

                            <c:choose>
                                <c:when test="${edit}">
                                    <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/allproducts' />" class="text-decoration-none">Cancel</a>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/allproducts' />" class="text-decoration-none">Cancel</a>
                                </c:otherwise>
                            </c:choose>


                        </form:form>
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
