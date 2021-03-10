<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Orders list</title>

        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">


    </head>

    <body>
        <div class="card">
            <%@include file="header.jsp" %>
            <!-- END header -->

            <div class="card-body">
                <div class="container">

                    <h1>List of Orders</h1>

                    <table class="table table-striped table-responsive-md table-hover">

                        <thead> 

                        <th scope="col" class="align-middle">Order number</th>
                        <th scope="col" class="align-middle">Name creator</th>
                        <th scope="col" class="align-middle">Create</th>
                        <th scope="col" class="align-middle">Update</th>
                        <th scope="col" class="align-middle">Status order</th>
                        <th scope="col" class="align-middle">Description</th>
                            <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER') or hasRole('SHOPMENAGER')">
                            <th width="100" scope="col"></th>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                            <th width="100" scope="col"></th>
                            </sec:authorize>
                        </thead>    

                        <tbody>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <th scope="row">${order.id}</th>
                                    <td>${order.user.userName}</td>
                                    <td>${order.dateTimeCreate}</td>
                                    <td>${order.dateTimeUpdate}</td>
                                    <td class="color-changer">${order.state}</td>
                                    <td>${order.description}</td>
                                    <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER') or hasRole('SHOPMENAGER')">
                                        <td><a href="<c:url value='/alldetailsorders-${order.id}' />" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">Details</a></td>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                                        <td><button type="button" class="btn btn-danger btn-sm active " data-toggle="modal" data-target="#deleteModal_${order.id}">Delete</button></td>
                                    </sec:authorize>
                                </tr>
                                
                                <!-- Modal alert -->
                                        <div class="modal fade" id="deleteModal_${order.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                          <div class="modal-dialog modal-sm">
                                            <div class="modal-content">
                                              <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                  <span aria-hidden="true">&times;</span>
                                                </button>
                                              </div>
                                              <div class="modal-body">
                                                  <span>Are you sure they want to delete order: <strong>${order.id}</strong>?</span>
                                              </div>
                                              <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
                                                <a href="<c:url value='/deleteorder-${order.id}' />" class="btn btn-danger btn-sm active " role="button"  aria-pressed="true">Delete</a>
                                              </div>
                                            </div>
                                          </div>
                                        </div> 
                                        <!-- END Modal alert -->
                                
                            </c:forEach>
                        </tbody>
                    </table>

                    <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                        <a href="<c:url value='/allproducts' />" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add New</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('EMPMENAGER')">
                        <a href="<c:url value='/listusers' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Users</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                        <a href="<c:url value='/allproducts' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Warehouse</a>
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