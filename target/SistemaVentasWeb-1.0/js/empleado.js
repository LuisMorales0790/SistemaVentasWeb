//var idsesion = new URL(location.href).searchParams.get("idempleado");
//console.log("ID EMP " + idsesion);

var empleado;
$(document).ready(function () {
    getEmpleados();

    $('#btn-agregar-empleado').attr('disabled', false);
    $('#btn-editar-empleado').attr('disabled', true);
    $("#form-register").submit(function (event) {
        if ($("#btn-editar-empleado").prop('disabled')) {
            event.preventDefault();
            registrarEmpleado();
        } else {
            event.preventDefault();
            actualizarEmpleado(empleado.idEmpleado);
        }
    });
});

function getEmpleados() {
    let accion = "listar";
    $.ajax({
        type: "GET",
        url: "./Controlador",
        data: $.param({
            accion: accion
        }),
        success: function (result) {
            console.log("lista sin parsear: " + result);
            let parsedResult = JSON.parse(result);
            empleados = parsedResult;
            console.log("Empleados desde getEmpleados" + empleados);
            console.log("lista parseada: " + parsedResult);
            if (parsedResult != false) {
                mostrarEmpleados(parsedResult);
            } else {
                console.log("Error recuperando los datos del cliente");
            }
        }
    });
}

function mostrarEmpleados(listaEmpleados) {
    let contenido = "";
    let estado = "";
    $.each(listaEmpleados, function (index, empleado) {
        empleado = JSON.parse(empleado);
        if (empleado.estado == 1) {
            estado = '<span class="badge badge-success">ACTIVO</span>';
        } else {
            estado = '<span class="badge badge-danger">INACTIVO</span>';
        }

        let botonEditar = '<button title="editar" id="btn-editar" onclick="obtenerEmpleado(' + empleado.idEmpleado + ');" class="btn btn-success btn-sm"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>';
        let botonEliminar = '<button title="eliminar" id="btn-eliminar" onclick="eliminarEmpleado(' + empleado.idEmpleado + ');" class="btn btn-danger btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></button>';

        contenido += '<tr><th scope="row">' + empleado.idEmpleado + '</th>' +
                '<td>' + empleado.dni + '</td>' +
                '<td>' + empleado.nombre + '</td>' +
                '<td>' + empleado.telefono + '</td>' +
                '<td>' + estado + '</td>' +
                '<td>' + empleado.user + '</td>'

        contenido += '></td>' +
                '<td>' + botonEditar + ' ' + botonEliminar + '</td>'

    });
    $("#empleados-tbody").html(contenido);
}

function eliminarEmpleado(idempleado) {
    // alert("Eliminar empleado");
    $("#eliminarEmpleadoModal").modal("show");
    $("#aceptar-eliminar-empleado-btn").click(function () {
        let accion = "eliminar";
        $.ajax({
            type: "GET",
            url: "./Controlador",
            data: $.param({
                accion: accion,
                idEmpleado: idempleado
            }),
            success: function (result) {

                if (result != false) {
                    console.log("Empleado Eliminado");
                    location.reload();
                } else {
                    console.log("Error al eliminar el empleado")
                }
            }
        });
    });
}

function registrarEmpleado() {
    console.log("Funcion para registrar Empleado");
    let dni = $("#txt-dni").val();
    let nombres = $("#txt-nombres").val();
    let telefono = $("#txt-telefono").val();
    let estado = $("#select-estado").val();
    let usuario = $("#txt-usuario").val();
    let accion = "insertar";
    $.ajax({
        type: "POST",
        url: "./Controlador",
        data: $.param({
            dni: dni,
            nombres: nombres,
            telefono: telefono,
            estado: estado,
            usuario: usuario,
            accion: accion
        }),
        success: function (result) {
            let parsedResult = result;
            console.log("parsedResult " + parsedResult);
            if (parsedResult == 1) {
                document.location.href = "Empleado.jsp";
            } else {
                console.log("Error en el response de ajax");
            }
        }
    });
}

function obtenerEmpleado(idEmpleado) {
    let accion = "editar";
    $.ajax({
        type: "GET",
        url: "./Controlador",
        data: $.param({
            idEmpleado: idEmpleado,
            accion: accion
        }),

        success: function (result) {
            console.log("editar " + result);
            let parsedResult = JSON.parse(result);


            if (parsedResult != false) {
                empleado = parsedResult;

                $("#txt-dni").val(parsedResult.dni);
                $("#txt-nombres").val(parsedResult.nombre);
                $("#txt-telefono").val(parsedResult.telefono);
                $("#select-estado").val(parsedResult.estado);
                $("#txt-usuario").val(parsedResult.user);
                $('#btn-agregar-empleado').attr('disabled', true);
                $('#btn-editar-empleado').attr('disabled', false);
            } else {
                console.log("Error recuperando datos del empleado");
            }
        }
    });
}

function actualizarEmpleado(id) {
    let dni = $("#txt-dni").val();
    let nombre = $("#txt-nombres").val();
    let telefono = $("#txt-telefono").val();
    let estado = $("#select-estado").val();
    let usuario = $("#txt-usuario").val();
    let accion = "editar";
    // let idempleado = idempleado;

    $.ajax({
        type: "POST",
        url: "./Controlador",
        data: $.param({

            accion: accion,
            idEmpleado: id,
            dni: dni,
            nombre: nombre,
            telefono: telefono,
            estado: estado,
            usuario: usuario
        }),

        success: function (result) {

            if (result != 1) {
                console.log("Datos actualizados correctamente");
            } else {
                console.log("Error al actualizar los datos")
            }
            location.reload();
        }
    });
}

