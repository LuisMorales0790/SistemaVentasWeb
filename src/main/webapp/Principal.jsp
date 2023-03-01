<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/608866481b.js" crossorigin="anonymous"></script>
        <!--Estilos de CSS -->
        <link href="css/index.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <!--NAVBAR -->
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <a class="navbar-brand" href="#">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a onclick="iframePage('Producto.jsp')" style="margin-left: 10px; border: none" class="btn btn-outline-light" href="#">Producto</a>
                    </li>
                    <li class="nav-item">
                        <a onclick="iframePage('Empleado.jsp')" style="margin-left: 10px; border: none" class="btn btn-outline-light" href="#">Empleado</a>
                    </li>
                    <li class="nav-item">
                        <a onclick="iframePage('Clientes.jsp')" style="margin-left: 10px; border: none" class="btn btn-outline-light" href="#">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a onclick="iframePage('RegistrarVenta.jsp')" style="margin-left: 10px; border: none" class="btn btn-outline-light" href="#">Nueva Venta</a>
                    </li>
                </ul>
            </div>
            <!-- DROPDOWN -->
            <div class="dropdown">
                <button style="border: none" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <a id="nombres">usuario</a>
                </button>
                <div class="dropdown-menu text-center" id="main-menu">
                    <a class="dropdown-item" href="#">
                        <img src="img/usuarios.png" alt="60" width="60"/>
                    </a>
                    <a class="dropdown-item" href="#" id="usuario">usuario</a>
                    <a class="dropdown-item" href="#" id="email">usuario@gmail.com</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" id="salir">Salir</a>
                </div>
            </div>        

        </nav>
        <!-- INFRAME -->
        <div class="m-4" style="height: 550px;">
            <iframe id="myFrame" style="height: 100%; width: 100%; border: none"></iframe>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://kit.fontawesome.com/608866481b.js" crossorigin="anonymous"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
        <!--Referenciar el archivo clientes JS -->
        <script src="js/principal.js"></script>        
    </body>
</html>
