<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>Products list</title>
        
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

                <h1>List of products</h1>
                <table class="table table-striped table-responsive-md table-hover ">

                    <thead> 
                        
                    <th scope="col" class="align-middle">Product number</th>
                    <th scope="col" class="align-middle">Name</th>
                    <th scope="col" class="align-middle">Quantity</th>
                    <th scope="col" class="align-middle">Price</th>
                    <th scope="col" class="align-middle">Description</th>
                    <th scope="col" class="align-middle">Photo</th>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                    <th width="100"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                    <th width="100"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                    <th width="100"></th>
                    </sec:authorize>
                    
                    </thead>    

                    <tbody>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <th scope="row" class="align-middle">${product.id}</th>
                                <td class="align-middle">${product.name}</td>
                                <td class="align-middle">${product.quantity}</td>
                                <td class="align-middle">${product.price}</td>
                                <td class="align-middle">${product.description}</td>
                                <td><img src="<c:url value='/static/photo/${product.photoName}' />" width="100" height="100"/> </td>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                                <td class="align-middle"><a href="<c:url value='/edit-product-${product.id}' />" class="btn btn-warning btn-sm active" role="button" aria-pressed="true">Edit</a></td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
                                <td class="align-middle"> <button type="button" class="btn btn-danger btn-sm active " data-toggle="modal" data-target="#deleteModal_${product.id}">Delete</button></td>
                                </sec:authorize>                               
                                <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                                    <td class="align-middle"><a  href="<c:url value='/addtocart-${product.id}' />" class="btn btn-primary btn-md active" role="button" aria-pressed="true"><span><i class="bi bi-bag-plus"></i></span></a></td>
                                </sec:authorize>
                                
                            </tr>
                            
                            <!-- Modal alert -->
                                        <div class="modal fade" id="deleteModal_${product.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                          <div class="modal-dialog modal-sm">
                                            <div class="modal-content">
                                              <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                  <span aria-hidden="true">&times;</span>
                                                </button>
                                              </div>
                                              <div class="modal-body">
                                                  <span>Are you sure they want to delete product: <strong>${product.id} ${product.name}</strong>?</span>
                                              </div>
                                              <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
                                                <a href="<c:url value='/delete-product-${product.id}' />" class="btn btn-danger btn-sm active " role="button"  aria-pressed="true">Delete</a>
                                              </div>
                                            </div>
                                          </div>
                                        </div> 
                             <!-- END Modal alert -->
                        </c:forEach>
                    </tbody>
                </table>
            
            <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">
            <a href="<c:url value='/newproduct' />" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add new product</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN') or hasRole('WARMENAGER')">   
            <a href="<c:url value='/allorders' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Order</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
            <a href="<c:url value='/allordersuser' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Order</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN') or hasRole('SHOPMENAGER')">
                <a href="<c:url value='/cart' />" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true"><i class="bi bi-bag"></i></a>
                <c:if test="${param.edit}">
                <span class="text-danger" ><fmt:message  key="cart.message"></fmt:message></span>
                </c:if>
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
       
    </body>
</html>

