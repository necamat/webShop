<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>User messages</title>
        
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
                            
                              <c:if test="${failure != null}">
                                <div class="alert alert-info " role="alert">
                                   ${failure}
                                </div>
                            </c:if>
                                
                             <c:if test="${success != null}">
                                <div class="alert alert-success " role="alert">
                                   ${success}
                                </div>                                
                            </c:if> 
                         
        
        
        
                   <a href="<c:url value='/listusers' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Users</a>
        
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
