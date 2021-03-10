<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

   <head lang="en">
       
    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Orders details list</title>
    
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">


    </head>

    <body>
         <div class="card">
            <%@include file="header.jsp" %>
            <!-- END header -->

            <div class="card-body">
                <div class="container">
    
                <h1> List of orders details </h1>
                <div><span>Order number: <strong>${order.id}</strong>; </span> <span>Order creator: <strong>${order.user.userName}</strong>; </span> <span>Order status: <strong class="color-changer">${order.state}</strong>;</span></div>
                <table class="table table-striped table-responsive-md table-hover">

                    <thead> 
                        
                    <th scope="col" class="align-middle">Product Number</th>
                    <th scope="col" class="align-middle">Product Name</th>
                    <th scope="col" class="align-middle">Quantity</th>

                    </thead>    

                    <tbody>
                        <c:forEach items="${orderDetails}" var="ord">
                            <tr>
                                <td class="align-middle">${ord.product.id}</td>
                                <td class="align-middle">${ord.product.name}</td>
                                <td class="align-middle">${ord.quantity}</td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            
                <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                <a href="<c:url value='/allorders' />" class="btn btn-secondary btn-md active " role="button"  aria-pressed="true">All Orders</a> 
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                <a href="<c:url value='/allordersuser' />" class="btn btn-secondary btn-md active " role="button"  aria-pressed="true">Orders</a> 
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                <a href="<c:url value='/orderreject-${order.id}' />" class="btn btn-danger btn-md active " role="button"  aria-pressed="true">Reject</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                <a href="<c:url value='/orderasccept-${order.id}' />" class="btn btn-success btn-md active " role="button"  aria-pressed="true">Accept</a>
                </sec:authorize>
                
            </div>
            </div>
           <!-- END body -->

            <%@include file="footer.jsp" %>
            <!-- END footer -->
        </div>

        <!--JavaScript -->
        <script src="<c:url value='/static/js/jquery-3.5.1.slim.min.js' />"></script>
        <script src="<c:url value='/static/js/bootstrap.bundle.min.js' />"></script>
       <script>

            $(document).ready(function () {
                $('.color-changer').each(function () {
                    if ($(this).text() == "Rejected") {
                        $(this).addClass('text-danger');
                    }
                    if ($(this).text() == "Delivered") {
                        $(this).addClass('text-success');
                    }
                    if ($(this).text() == "Process") {
                        $(this).addClass('text-warning');
                    }
                });
            });


        </script>
    </body>
</html>