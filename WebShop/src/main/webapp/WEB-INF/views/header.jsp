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
                                <a class="nav-link  " href="<c:url value="/home" />">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link " href="<c:url value="/aboutus" />">About us</a>
                            </li>
                        </ul>

                        <ul class="navbar-nav nav-pills  ml-auto">
                            <li class="navbar-text">
                                <span class="text-dark">Dear <strong >${loggedinuser}</strong>, Welcome to ProudWolf.</span>
                            </li>
                            
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/logout" />">Logout</a>
                            </li>
                        </ul>

                    </div>        
                </nav>
            </div>