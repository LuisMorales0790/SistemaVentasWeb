
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="css/registroVentas.css" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">            

        <title>JSP Page</title>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-sm-5 parte01">
                <div class="card">
                    <form id="form-register" method="POST">
                        <div class="card-body">
                            <div class="form-group">
                                <label>Datos del Cliente</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" id="txt-dni" name="codigoCliente" class="form-control" placeholder="Codigo" style="margin-right: 5px" required>
                                    <input type="submit" name="accion" id="btn-buscarCliente" value="Buscar" class="btn btn-outline-info form-control">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" id="nombreCliente" name="nombreCliente" placeholder="Datos Cliente" class="form-control" readonly>
                                </div>
                            </div>
                            <!--DATOS DEL PRODUCTO -->
                            <div class="form-group">
                                <label>Datos Producto</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" id="txt-codigoProducto" name="codigoProducto" class="form-control" placeholder="Codigo" style="margin-right: 5px">
                                    <input type="submit" id="btn-buscarProducto" value="Buscar" class="btn btn-outline-info form-control">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" id="txt-nombre-producto" placeholder="Datos Producto" class="form-control" readonly>
                                </div>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" id="txt-precio" name="precio" class="form-control" placeholder="S/.0.00" style="margin-right: 5px" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <input type="number" min="1" value="1" id="cantidad" name="cantidad" placeholder="" class="form-control">
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" id="txt-stock" placeholder="Stock" name="stock" class="form-control" readonly> 
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" id="agregarProducto" value="agregar" class="btn btn-outline-info">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex col-sm-5 ml-auto">
                            <label>Num Serie: </label>
                            <input type="text" name="nSerie" id="txt-numSerie" class="form-control" readonly>
                        </div>
                        <br>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Nro</th>
                                    <th>Codigo</th>
                                    <th>Descripcion</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th>
                                    <th>SubTotal</th>
                                    <th class="accion">Acciones</th>
                                </tr>
                            </thead>
                            <tbody id="productos-tbody">

                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-6">
                            <input type="button" name="accion" id="generarVenta" value="Generar Venta" onclick="print()" class="btn btn-success">
                            <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                        </div>
                        <div class=" d-flex col-sm-3 ml-auto">
                            <label>Total: </label>
                            <input  type="text" id="txt-total" class="form-control" style="margin-left: 10px" readonly>
                            <label style="margin-left: 10px">  $</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://kit.fontawesome.com/608866481b.js" crossorigin="anonymous"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
        <!--Referenciar el archivo clientes JS -->
        <script src="js/registrarVenta.js"></script>
    </body>
</html>
