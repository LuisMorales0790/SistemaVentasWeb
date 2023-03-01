var producto;
$(document).ready(function () {
    console.log("Bienvenido a la seccion de productos");

    getProductos();
    $('#btn-agregar-producto').attr('disabled', false);
    $('#btn-editar-producto').attr('disabled', true);
    $("#form-register").submit(function (event) {
        if ($("#btn-editar-producto").prop('disabled')) {
            event.preventDefault();
            registrarProducto();
        } else {
            event.preventDefault();
            actualizarProducto(producto.idProducto);
        }
    });
});

function getProductos() {
    let accion = "listar";
    $.ajax({
        type: "GET",
        url: "./ControladorProductos",
        data: $.param({
            accion: accion
        }),
        success: function (result) {
            console.log("lista sin parsear: " + result);
            let parsedResult = JSON.parse(result);
            productos = parsedResult;
            console.log("Productos desde getProductos" + productos);
            console.log("lista parseada: " + parsedResult);
            if (parsedResult != false) {
                mostrarProductos(parsedResult);
            } else {
                console.log("Error recuperando los datos de productos");
            }
        }
    });
}

function mostrarProductos(listaProductos) {
    let contenido = "";
    let estado = "";
    $.each(listaProductos, function (index, producto) {
        producto = JSON.parse(producto);
        if (producto.estado == 1) {
            estado = '<span class="badge badge-success">ACTIVO</span>';
        } else {
            estado = '<span class="badge badge-danger">INACTIVO</span>';
        }

        let botonEditar = '<button title="editar" id="btn-editar" onclick="obtenerProducto(' + producto.idProducto + ');" class="btn btn-success btn-sm"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>';
        let botonEliminar = '<button title="eliminar" id="btn-eliminar" onclick="eliminarProducto(' + producto.idProducto + ');" class="btn btn-danger btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></button>';

        contenido += '<tr><th scope="row">' + producto.idProducto + '</th>' +
                '<td>' + producto.nombres + '</td>' +
                '<td>' + producto.precio +' $' + '</td>' +
                '<td>' + producto.stock + '</td>' +
                '<td>' + estado + '</td>'

        contenido += '></td>' +
                '<td>' + botonEditar + ' ' + botonEliminar + '</td>'

    });
    $("#productos-tbody").html(contenido);
}

function registrarProducto() {
    
    let nombres = $("#txt-nombres").val();
    let precio = $("#txt-precio").val();
    let stock = $("#txt-stock").val();
    let estado = $("#select-estado").val();
    let accion = "insertar";
    $.ajax({
        type: "POST",
        url: "./ControladorProductos",
        data: $.param({
            nombres: nombres,
            precio: precio,
            stock: stock,
            estado: estado,
            accion: accion
        }),
        success: function (result) {
            let parsedResult = result;
            console.log("parsedResult " + parsedResult);
            if (parsedResult == 1) {
                document.location.href = "Producto.jsp";
            } else {
                console.log("Error al insertar el producto");
            }
        }
    });
}

function obtenerProducto(idProducto) {
    let accion = "editar";
    $.ajax({
        type: "GET",
        url: "./ControladorProductos",
        data: $.param({
            idProducto: idProducto,
            accion: accion
        }),

        success: function (result) {
            console.log("editar " + result);
            let parsedResult = JSON.parse(result);


            if (parsedResult != false) {
                producto = parsedResult;

                $("#txt-nombres").val(parsedResult.nombres);
                $("#txt-precio").val(parsedResult.precio);
                $("#txt-stock").val(parsedResult.stock);
                $("#select-estado").val(parsedResult.estado);
                $('#btn-agregar-producto').attr('disabled', true);
                $('#btn-editar-producto').attr('disabled', false);
            } else {
                console.log("Error recuperando datos del producto");
            }
        }
    });
}

function actualizarProducto(idProducto) {
    let nombre = $("#txt-nombres").val();
    let precio = $("#txt-precio").val();
    let stock = $("#txt-stock").val();
    let estado = $("#select-estado").val();
    let accion = "editar";

    $.ajax({
        type: "POST",
        url: "./ControladorProductos",
        data: $.param({
            accion: accion,
            idProducto: idProducto,
            nombre: nombre,
            precio: precio,
            stock: stock,
            estado: estado
        }),

        success: function (result) {

            if (result != 1) {
                console.log("Producto actualizados correctamente");
            } else {
                console.log("Error al actualizar el producto");
            }
            location.reload();
        }
    });
}

function eliminarProducto(idProducto) {
    $("#eliminarProductoModal").modal("show");
    $("#aceptar-eliminar-producto-btn").click(function () {
        let accion = "eliminar";
        $.ajax({
            type: "GET",
            url: "./ControladorProductos",
            data: $.param({
                accion: accion,
                idProducto: idProducto
            }),
            success: function (result) {

                if (result != false) {
                    console.log("Producto Eliminado");
                    location.reload();
                } else {
                    console.log("Error al eliminar el producto");
                }
            }
        });
    });
}


