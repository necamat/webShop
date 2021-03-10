<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Cart</title>

        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"> 

        <style>
            

            .number-input input[type="number"] {
                -webkit-appearance: textfield;
                -moz-appearance: textfield;
                appearance: textfield;
            }

            .number-input input[type=number]::-webkit-inner-spin-button,
            .number-input input[type=number]::-webkit-outer-spin-button {
                -webkit-appearance: none;
            }

            .number-input {
                display: flex;
                justify-content: space-around;
                align-items: center;
            }

            .number-input button {
                -webkit-appearance: none;
                background-color: transparent;
                border: none;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                margin: 0;
                position: relative;
            }

            .number-input button:before,
            .number-input button:after {
                display: inline-block;
                position: absolute;
                content: '';
                height: 2px;
                transform: translate(-50%, -50%);
            }

            .number-input button.plus:after {
                transform: translate(-50%, -50%) rotate(90deg);
            }

            .number-input input[type=number] {
                text-align: center;
            }

            .number-input.number-input {
                border: 1px solid #ced4da;
                width: 10rem;
                border-radius: .25rem;
            }

            .number-input.number-input button {
                width: 2.6rem;
                height: .7rem;
            }

            .number-input.number-input button.minus {
                padding-left: 10px;
            }

            .number-input.number-input button:before,
            .number-input.number-input button:after {
                width: .7rem;
                background-color: #495057;
            }

            .number-input.number-input input[type=number] {
                max-width: 4rem;
                padding: .5rem;
                border: 1px solid #ced4da;
                border-width: 0 1px;
                font-size: 1rem;
                height: 2rem;
                color: #495057;
            }

            @media not all and (min-resolution:.001dpcm) {
                @supports (-webkit-appearance: none) and (stroke-color:transparent) {

                    .number-input.def-number-input.safari_only button:before,
                        .number-input.def-number-input.safari_only button:after {
                        margin-top: -.3rem;
                    }
                }
            }
        </style>
    </head>
    <body>
        <div class="card">
            <%@include file="header.jsp" %>
            <!-- END header -->

            <div class="card-body">
                <div class="container">
                    <h1>Shopping Cart</h1>

                    <table class="table table-striped table-responsive-md table-hover ">

                        <thead>
                            <tr>
                                <th scope="col" class="align-middle">Options</th>
                                <th scope="col" class="align-middle">Product number</th>
                                <th scope="col" class="align-middle">Photo</th>
                                <th scope="col" class="align-middle">Name</th>
                                <th scope="col" class="align-middle">Price</th>
                                <th scope="col" class="align-middle">Quantity</th>
                                <th scope="col" class="align-middle">Sub Total</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:set var="s" value="0"></c:set>
                            <c:forEach var="pr" items="${sessionScope.cart}">
                                <c:set var="s" value="${s+ pr.product.price * pr.quantity}"></c:set>

                                    <tr>
                                        <td class="align-middle">
                                            <a href="<c:url value='/deletefromcart-${pr.product.id}' />" class="btn btn-danger btn-sm active " role="button"  aria-pressed="true" >Remove</a>                                   <a href="#" class="btn btn-primary btn-sm active " role="button"  aria-pressed="true" onclick="update($('#quantity_${pr.product.id}').val(), '${pr.product.id}')">Update</a>
                                    </td>
                                    <th scope="row" class="align-middle">${pr.product.id}</th>
                                    <td><img src="static/photo/${pr.product.photoName}" width="100" height="100"/> </td>
                                    <td class="align-middle">${pr.product.name }</td>
                                    <td class="align-middle">${pr.product.price }</td>
                                    <td class="align-middle"> 
                                        <div class="def-number-input number-input safari_only">
                                            <button onclick="this.parentNode.querySelector('input[type=number]').stepDown()" class="minus"></button>
                                            <input id="quantity_${pr.product.id}" class="quantity" min="0" max="${pr.product.quantity}" name="quantity" value="${pr.quantity}" type="number">
                                            <button onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="plus"></button>
                                        </div>
                                    </td>                 
                                    <th class="align-middle">${pr.product.price * pr.quantity}</th>
                                </tr>
                            </c:forEach>



                            <tr>
                                <td colspan="5"></td>
                                <th class="align-middle text-center">Sum :</th>
                                <th class="align-middle">${s}</th>
                            </tr>

                        </tbody>
                    </table>

                    <a href="<c:url value='/saveorder' />" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Save order</a>
                    <a href="<c:url value='/allproducts' />" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Shopping</a><br>

                    <p class="text-danger">${param.quantityemmpty}</p>


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

        function update(quantity, id) {

        window.location = "<c:url value="/updatequantity-"/>" + id + "-" + quantity;

        }
        </script>
    </body>
</html>