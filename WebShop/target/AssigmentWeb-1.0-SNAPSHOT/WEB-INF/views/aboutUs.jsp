<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>About us</title>

        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">

    </head> 
    <body>
        <div class="card">
            <div class="card-header">
                <nav class="navbar navbar-expand-sm navbar-light bg-light">


                    <a class="navbar-brand" href="#">
                        <img src="<c:url value='/static/photo/logo.png' />" width="40" height="40" alt="">
                    </a>
                    <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarMenu">
                        <ul class="navbar-nav nav-pills  ">
                            <li class="nav-item ">
                                <a class="nav-link" href="<c:url value="/home" />">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link " href="<c:url value="/aboutus" />">About us</a>
                            </li>
                        </ul>

                        <ul class="navbar-nav nav-pills  ml-auto">
                            <li class="nav-item">
                                <a href="<c:url value="/login" />" class="nav-link">Login</a>
                            </li>
                        </ul>

                    </div>        
                </nav>
            </div>
            <!-- END header -->

            <div class="card-body">
                <div class="container">
                    <div class="row justify-content-sm-center">
                        <div class="col-sm-3 ">
                            <img src="<c:url value='/static/photo/logo.png' />" alt="proudwolf" class="img-fluid ">
                        </div>
                    </div>

                    <div class="row justify-content-sm-center">
                        <div class="col-md-6 ">

                            <div><h1 class="text-sm-center text-justify">Zbog ??ega izabrati nas !</h1></div>

                            <div> <p class="text-sm-center text-justify">Dr??e??i korak sa sportskim I modnim trendovima, napravili smo modni brend koji ??e zadovoljiti zahteve modernog mu??karca i ??ene od stila. "ProudWolf" jeste urbana, sportska moda namenjena mu??karcima i ??enama koji ??ele jedinstven stil. Kolekcija nudi sportsku i luksuznu ode??u kreiranu na jedinstven na??in: kombinacija koja je i za sport, ali i za modernu ulicu. U tom cilju, izra??ujemo proizvode koji se odlikuju vrhunskim dizajnom, odli??nim kvalitetom i pristupa??nim cenama. Istakli bismo kvalitet: u tom pogledu, te??ili smo savr??enstvu, ??to je, moramo priznati, ??esto izazivalo nervozu kod na??ih dobavlja??a materijala. Rezultat je individualna moda koja demonstrira kreativnost i prakti??nu raznovrsnost sportske kolekcije "ProudWolf".</p></div>
                        </div>

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
