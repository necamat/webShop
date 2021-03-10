<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Users List</title>

        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">

    </head>
    <body>

        <div class="card">
            <%@include file="header.jsp" %>
            <!-- END header -->

            <div class="card-body">
                <div class="container">

                    <h1>List of Users </h1>

                    <table class="table table-striped table-responsive-md table-hover ">

                        <thead>

                        <th scope="col">Username</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Email</th>
                            <sec:authorize access="hasRole('ADMIN') or hasRole('EMPMENAGER')">
                            <th scope="col">User state</th>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                            <th width="100" scope="col"></th>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                            <th width="100" scope="col"></th>
                            </sec:authorize>

                        </thead>    

                        <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <th scope="row">${user.userName}</th>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.email}</td>

                                    <sec:authorize access="hasRole('ADMIN') or hasRole('EMPMENAGER')">
                                        <td> <a href="<c:url value='/change-state-${user.userName}' />" class=" text-decoration-none color-changer "  >${user.state}</a></td>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ADMIN')">
                                        <td><a href="<c:url value='/edit-user-${user.userName}' />" class="btn btn-warning btn-sm active" role="button" aria-pressed="true">Edit</a></td>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('ADMIN')">
                                        <td><button type="button" class="btn btn-danger btn-sm active " data-toggle="modal" data-target="#deleteModal_${user.userName}">Delete</button></td>
                                    </sec:authorize>
                                </tr>
                                
                                        <!-- Modal alert -->
                                        <div class="modal fade" id="deleteModal_${user.userName}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                          <div class="modal-dialog modal-sm">
                                            <div class="modal-content">
                                              <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                  <span aria-hidden="true">&times;</span>
                                                </button>
                                              </div>
                                              <div class="modal-body">
                                                  <span>Are you sure they want to delete the user: <strong>${user.firstName} ${user.lastName}</strong>?</span>
                                              </div>
                                              <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
                                                <a href="<c:url value='/delete-user-${user.userName}' />" class="btn btn-danger btn-sm active " role="button"  aria-pressed="true">Delete</a>
                                              </div>
                                            </div>
                                          </div>
                                        </div> 
                                        <!-- END Modal alert -->
                                        
                            </c:forEach>
                        </tbody>
                    </table>

                    <sec:authorize access="hasRole('ADMIN')">

                        <a href="<c:url value='/newuser' />" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add New User</a>

                    </sec:authorize>

                    <a href="<c:url value='/allorders' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Order</a>
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
                    if ($(this).text() == "Inactive") {
                        $(this).addClass('text-danger');
                    }
                    if ($(this).text() == "Active") {
                        $(this).addClass('text-success');
                    }
                });
            });

            function aa() {
                window.alert("da li hocete da obrisete korisnika")
            }

        </script>


    </body>
</html>