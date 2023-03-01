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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">            

        <title>Producto</title>
    </head>
    <!-- header -->
    <header id="main-header" class="py-2 bg-info text-white">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h1>
                        <i class='fas fa-box-open'></i>
                        Control de Productos
                    </h1>
                </div>
            </div>
        </div>
    </header>

    <body>
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form id="form-register" method="POST" class="was-validated">
                        <!-- modal -->
                        <div class="modal fade" id="eliminarProductoModal">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">
                                    <div class="modal-header bg-danger text-white">
                                        <h5 class="modal-title">Eliminar Producto</h5>
                                        <button class="close" data-dismiss="modal">
                                            <span>&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Eliminar a un producto es una acción que no se puede restaurar. ¿Está seguro?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                        <button type="button" class="btn btn-danger" id="aceptar-eliminar-producto-btn">Eliminar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Nombre del producto</label>
                            <input type="text" id="txt-nombres" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>precio</label>
                            <input type="number" id="txt-precio" class="form-control" step="any" required>
                        </div>
                        <div class="form-group">
                            <label>stock</label>
                            <input type="number" id="txt-stock" class="form-control" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="select">Estado</label>
                            <select name="select" id="select-estado" class="custom-select">
                                <option value="1">Activo</option>
                                <option value="0">Inactivo</option>
                            </select>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <input type="submit" id="btn-agregar-producto" value="Registrar Producto" class="btn btn-info btn-block">
                            </div>
                            <div class="col-md-6">
                                <input type="submit" id="btn-editar-producto" value="Actualizar Producto" class="btn btn-success btn-block">
                            </div>
                        </div>                       
                    </form>
                </div>
            </div>
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">NOMBRE PRODUCTO</th>
                            <th scope="col">PRECIO</th>
                            <th scope="col">STOCK</th>
                            <th scope="col">ESTADO</th>
                            <th scope="col">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody id="productos-tbody">

                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://kit.fontawesome.com/608866481b.js" crossorigin="anonymous"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
        <!--Referenciar el archivo clientes JS -->
        <script src="js/producto.js"></script>     
    </body>
</html>
