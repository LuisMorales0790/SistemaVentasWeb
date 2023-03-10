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
        <div class="container py-3">
            <div class="card border-primary mb-3 my-5">
                <div class="card-body">
                    <div class="row m-3">
                        <div class="col-md-6 bg-login-image"></div>
                        <div class="col-md-6">
                            <h1 class="h4 text-gray-900 my-3">Login Only Shop</h1>
                            <form id="form-login">
                                <div class="form-group">
                                    <input type="text" class="form-control border-primary" id="usuario" placeholder="Usuario" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control border-primary" id="contrasena" placeholder="Contraseña" required>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block" id="btnEntar">Ingresar</button>
                                <hr>
                            </form>
                        </div>
                    </div>
                    <a href="register.html">¿Aún no tienes cuenta? Registrarme</a><br>
                    <div id="login-error" class="alert alert-danger d-none" role="alert">Usuario o Contraseña incorrectos</div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>        
        <!-- jQuery library -->
        <!--  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script> -->
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
        <!--Referenciar el archivo clientes JS -->
        <script src="js/index.js"></script>
    </body>
</html>
