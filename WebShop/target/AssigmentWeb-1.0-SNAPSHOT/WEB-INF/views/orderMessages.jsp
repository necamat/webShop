<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
         <title>Order messages</title>
         
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
                        
                            <c:if test="${messagessucces != null}">
                                <div class="alert alert-success  " role="alert" >
                                    Order <strong>${ordernumber}</strong> has been deleted.
                                </div>

                                <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                                    <a href="<c:url value='/allorders' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">All orders</a>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                                    <a href="<c:url value='/allordersuser' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Orders</a>
                                </sec:authorize>

                            </c:if>

                            <c:if test="${message != null}">

                                <div class="alert alert-info" role="alert">
                                    The order <strong>${ordernumber}</strong> has already been processed.
                                </div>

                                <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                                    <a href="<c:url value='/allorders' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">All orders</a>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                                    <a href="<c:url value='/allordersuser' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Orders</a>
                                </sec:authorize>

                             </c:if>

                             <c:if test="${emptyorders != null}">

                                <div class="alert alert-info " role="alert">
                                    ${emptyorders}
                                </div>

                                <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')or hasRole('WARMENAGER')">
                                    <a href="<c:url value='/allproducts' />" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add New</a>
                                </sec:authorize>

                             </c:if>
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
