<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>       
    <head lang="en">
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>User registration</title>

        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

    </head>
    <body>
            <div class="card">
            <%@include file="header.jsp" %>
            <!-- END header -->

            <div class="card-body">
                <div class="container">
                    <div class="col-md-6 offset-md-3 ">
                    <h1>User registration</h1>


                    <form:form method="POST" modelAttribute="user"  >

                        <form:input type="hidden" path="id" id="id"/>

                        <div class="form-row">
                            <div class="form-group col-md-12">

                                <form:label path="firstName">First Name</form:label>                
                                <form:input path="firstName" id="firstName" type="text" class="form-control" />                  
                                <form:errors path="firstName" class="text-danger"/>

                            </div>
                        </div> 

                        <div class="form-row">
                            <div class="form-group col-md-12">

                                <form:label path="lastName">Last Name</form:label>
                                <form:input path="lastName" id="lastName" type="text" class="form-control"/>
                                <form:errors path="lastName" class="text-danger"/>

                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-12">

                                <form:label path="userName">User Name</form:label>                             
                                <form:input path="userName" id="userName" type="text" class="form-control"/>
                                <form:errors path="userName" class="text-danger"/>
                                
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-12">

                                <form:label path="password">Password</form:label>
                                <form:input path="password" id="password" type="password" class="form-control"/>
                                <form:errors path="password" class="text-danger"/>

                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-12">

                                <form:label path="email">Email</form:label>
                                <form:input path="email" id="email" type="text" class="form-control"/>
                                <form:errors path="email" class="text-danger"/>

                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-12">

                                <form:label path="userProfiles">Roles</form:label>
                                <form:select path="userProfiles" id="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control" />
                                <form:errors path="userProfiles" class="text-danger"/>

                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${edit}">
                                <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/listusers' />" class="text-decoration-none">Cancel</a>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/listusers' />" class="text-decoration-none">Cancel</a>
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
