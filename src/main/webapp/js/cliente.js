var cliente;
$(document).ready(function () {
    console.log("Bienvenido a la seccion de Cliente");

    getClientes();

    $('#btn-agregar-cliente').attr('disabled', false);
    $('#btn-editar-cliente').attr('disabled', true);
    $("#form-register").submit(function (event) {
        if ($("#btn-editar-cliente").prop('disabled')) {
            event.preventDefault();
            registrarCliente();
        } else {
            event.preventDefault();
            actualizarCliente(cliente.idCliente);
        }
    });

});

function getClientes() {
    let accion = "listar";
    $.ajax({
        type: "GET",
        url: "./ControladorClientes",
        data: $.param({
            accion: accion
        }),
        success: function (result) {
            console.log("lista sin parsear: " + result);
            let parsedResult = JSON.parse(result);
            clientes = parsedResult;
            if (parsedResult != false) {
                mostrarClientes(parsedResult);
            } else {
                console.log("Error recuperando los datos de los clientes");
            }
        }
    });
}

function mostrarClientes(listaClientes) {
    let contenido = "";
    let estado = "";
    $.each(listaClientes, function (index, cliente) {
        cliente = JSON.parse(cliente);
        if (cliente.estado == 1) {
            estado = '<span class="badge badge-success">ACTIVO</span>';
        } else {
            estado = '<span class="badge badge-danger">INACTIVO</span>';
        }

        let botonEditar = '<button title="editar" id="btn-editar" onclick="obtenerCliente(' + cliente.idCliente + ');" class="btn btn-success btn-sm"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>';
        let botonEliminar = '<button title="eliminar" id="btn-eliminar" onclick="eliminarCliente(' + cliente.idCliente + ');" class="btn btn-danger btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></button>';

        contenido += '<tr><th scope="row">' + cliente.idCliente + '</th>' +
                '<td>' + cliente.dni + '</td>' +
                '<td>' + cliente.nombres + '</td>' +
                '<td>' + cliente.direccion + '</td>' +
                '<td>' + estado + '</td>'

        contenido += '></td>' +
                '<td>' + botonEditar + ' ' + botonEliminar + '</td>'

    });
    $("#clientes-tbody").html(contenido);
}

function registrarCliente() {
    console.log("Funcion para registrar Cliente");
    let dni = $("#txt-dni").val();
    let nombres = $("#txt-nombres").val();
    let direccion = $("#txt-direccion").val();
    let estado = $("#select-estado").val();
    let accion = "insertar";
    $.ajax({
        type: "POST",
        url: "./ControladorClientes",
        data: $.param({
            dni: dni,
            nombres: nombres,
            direccion: direccion,
            estado: estado,
            accion: accion
        }),
        success: function (result) {
            let parsedResult = result;
            console.log("parsedResult " + parsedResult);
            if (parsedResult == 1) {
                document.location.href = "Clientes.jsp";
            } else {
                console.log("Error al insertar el cliente");
            }
        }
    });
}

function obtenerCliente(idCliente) {
    let accion = "editar";
    $.ajax({
        type: "GET",
        url: "./ControladorClientes",
        data: $.param({
            idCliente: idCliente,
            accion: accion
        }),

        success: function (result) {
            console.log("editar " + result);
            let parsedResult = JSON.parse(result);


            if (parsedResult != false) {
                cliente = parsedResult;

                $("#txt-dni").val(parsedResult.dni);
                $("#txt-nombres").val(parsedResult.nombres);
                $("#txt-direccion").val(parsedResult.direccion);
                $("#select-estado").val(parsedResult.estado);
                $('#btn-agregar-cliente').attr('disabled', true);
                $('#btn-editar-cliente').attr('disabled', false);
            } else {
                console.log("Error recuperando datos del cliente");
            }
        }
    });
}

function actualizarCliente(idCliente) {
    let dni = $("#txt-dni").val();
    let nombre = $("#txt-nombres").val();
    let direccion = $("#txt-direccion").val();
    let estado = $("#select-estado").val();
    let accion = "editar";

    $.ajax({
        type: "POST",
        url: "./ControladorClientes",
        data: $.param({

            accion: accion,
            idCliente: idCliente,
            dni: dni,
            nombre: nombre,
            direccion: direccion,
            estado: estado
        }),

        success: function (result) {

            if (result != 1) {
                console.log("Datos actualizados correctamente");
            } else {
                console.log("Error al actualizar los datos");
            }
            location.reload();
        }
    });
}

function eliminarCliente(idCliente){
    $("#eliminarClienteModal").modal("show");
    $("#aceptar-eliminar-cliente-btn").click(function () {
        let accion = "eliminar";
        $.ajax({
            type: "GET",
            url: "./ControladorClientes",
            data: $.param({
                accion: accion,
                idCliente: idCliente
            }),
            success: function (result) {

                if (result != false) {
                    console.log("Cliente Eliminado");
                    location.reload();
                } else {
                    console.log("Error al eliminar el cliente");
                }
            }
        });
    });
}
