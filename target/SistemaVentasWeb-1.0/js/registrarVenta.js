var producto;
var id;
var monto;
$(document).ready(function () {
    console.log("Registraremos una venta");
    obtenerNumSerie();
    mostrarProductos();
    totales();
    
    $("#btn-buscarCliente").click(function (event) {
        event.preventDefault();
        buscarCliente();
    });

    $("#btn-buscarProducto").click(function (event) {
        event.preventDefault();
        buscarProducto();
    });

    $("#form-register").submit(function (event) {
        let value = ($("#agregarProducto").attr("value"));
        if(value == 'agregar'){
            event.preventDefault();
            agregarProducto();
        }{
            event.preventDefault();
            actualizarProducto(id);
        }     
    });
    
    $("#generarVenta").click(function (event) {
        event.preventDefault();
        generarVenta();
    });
});

function buscarCliente() {
    let dni = $("#txt-dni").val();
    let accion = "dni_cliente";
    $.ajax({
        type: "GET",
        url: "./ControladorClientes",
        data: $.param({
            dni: dni,
            accion: accion
        }),

        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult != false) {
                cliente = parsedResult;
                $("#nombreCliente").val(parsedResult.nombres);
            } else {
                console.log("Error al recuperar datos del cliente ");
            }
        }
    });
}

function buscarProducto() {
    let idProducto = $("#txt-codigoProducto").val();
    let accion = "editar";
    $.ajax({
        type: "GET",
        url: "./ControladorProductos",
        data: $.param({
            idProducto: idProducto,
            accion: accion
        }),

        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult != false) {
                producto = parsedResult;
                $("#txt-nombre-producto").val(parsedResult.nombres);
                $("#txt-precio").val(parsedResult.precio);
                $("#txt-stock").val(parsedResult.stock);
            } else {
                console.log("Error al recuperar datos del producto ");
                alert("El Codigo ingresado No existe");
            }
        }
    });
}

function agregarProducto(){
    
    let codigoProducto = $("#txt-codigoProducto").val();
    let nombreProducto = $("#txt-nombre-producto").val();
    let precioProducto = $("#txt-precio").val();
    let cantidadProducto = $("#cantidad").val();
    let stock = $("#txt-stock").val();
    
    var productList;
    if(localStorage.getItem("productList") == null){
        productList = [];
    }else{
        productList = JSON.parse(localStorage.getItem("productList"));
    }
    
    productList.push({
    
        codigoProducto: codigoProducto,
        nombreProducto: nombreProducto,
        precioProducto: precioProducto,
        cantidadProducto: cantidadProducto,
        stock: stock 
    });
    
    localStorage.setItem("productList", JSON.stringify(productList));
    mostrarProductos();
    totales();
}

function mostrarProductos(){
    var productList;
    if(localStorage.getItem("productList") == null){
        productList = [];
    }else{
        productList = JSON.parse(localStorage.getItem("productList"));
        console.log("Local Storage " + productList);
    }
    
    var html = "";
    
    productList.forEach(function (element, index){
        var subtotal = element.precioProducto*element.cantidadProducto;
        html += "<tr>";
        html += "<td>" + (index + 1) + "</td>";
        html += "<td>" + element.codigoProducto + "</td>";
        html += "<td>" + element.nombreProducto + "</td>";
        html += "<td>" + element.precioProducto + "</td>";
        html += "<td>" + element.cantidadProducto + "</td>";
        html += "<td>" + subtotal + "</td>";
        
        html += 
        '<td><button onclick="eliminarProduct(' + 
        index +
        ')" class="btn btn-danger">Delete</button><button onclick="obtenerProducto(' + 
        index +
        ')" class="btn btn-warning m-2">Edit</button></td>';
        html +="</tr>";
    });
    $("#productos-tbody").html(html);
}

function eliminarProduct(index){
    var productList;
    if(localStorage.getItem("productList") == null){
        productList = [];
    }else{
        productList = JSON.parse(localStorage.getItem("productList"));
    }

    productList.splice(index, 1);
    localStorage.setItem("productList", JSON.stringify(productList));
    mostrarProductos();
    totales();
}

function limpiarLocalStorage(){
    var productList;
    if(localStorage.getItem("productList") == null){
        productList = [];
    }else{
        productList = JSON.parse(localStorage.getItem("productList"));
    }
    
    for (var i=0; i < productList.length; i++) {
        productList.splice(i);
    }
    
    localStorage.setItem("productList", JSON.stringify(productList));
}


function obtenerProducto(index){
    id = index;
    $("#agregarProducto").attr("value","actualizar");
    
    var productList;
    if(localStorage.getItem("productList") == null){
        productList = [];
    }else{
        productList = JSON.parse(localStorage.getItem("productList"));
    }
       
        $("#txt-dni").val(productList[index].dni);
        $("#nombreCliente").val(productList[index].nombreCliente);
        $("#txt-codigoProducto").val(productList[index].codigoProducto);
        $("#txt-nombre-producto").val(productList[index].nombreProducto);
        $("#txt-precio").val(productList[index].precioProducto);
        $("#cantidad").val(productList[index].cantidadProducto);
        $("#txt-stock").val(productList[index].stock);
    
}

function actualizarProducto(index){
    var productList;
    if(localStorage.getItem("productList") == null){
        productList = [];
    }else{
        productList = JSON.parse(localStorage.getItem("productList"));
    }
    
    productList[index].dni = $("#txt-dni").val();
    productList[index].nombreCliente = $("#nombreCliente").val();
    productList[index].codigoProducto = $("#txt-codigoProducto").val();
    productList[index].nombreProducto = $("#txt-nombre-producto").val();
    productList[index].precioProducto = $("#txt-precio").val();
    productList[index].cantidadProducto = $("#cantidad").val();
    productList[index].stock = $("#txt-stock").val();

    localStorage.setItem("productList", JSON.stringify (productList));
    mostrarProductos(); 
    totales();
}

function totales(){
    var tot = 0;
    var productList;
    if(localStorage.getItem("productList") == null){
        productList = [];
    }else{
        productList = JSON.parse(localStorage.getItem("productList"));
    }
    for (var i = 0; i < productList.length; i++) {      
           tot = tot + productList[i].cantidadProducto * productList[i].precioProducto; 
           monto = tot;
     }
     $("#txt-total").val(tot.toFixed(2)) + " $";
}

function obtenerNumSerie(){
    let accion = "numserie";
    $.ajax({
        type: "GET",
        url: "./ControladorRegVenta",
        data: $.param({
            accion: accion
        }),
        success: function (result) {
            console.log("numero de serie: " + result);
            if (result != false) {
                $("#txt-numSerie").val(result);
            } else {
                console.log("Error obteniendo numero de serie");
            }
        }
    });
}

function generarVenta(){
    console.log("generar venta");
  let dni = $("#txt-dni").val();
  var productList = JSON.parse(localStorage.getItem("productList"));
  let numSerie = $("#txt-numSerie").val();
  var totalPagar = monto;
  let estado = "1";
  let accion = "generarVenta";
  
  $.ajax({
        type: "POST",
        url: "./ControladorRegVenta",
        data: $.param({
            dni: dni,
            productList: JSON.stringify(productList),
            numSerie: numSerie,
            totalPagar: totalPagar,
            estado: estado,
            accion: accion
        }),
        success: function (result) {
            let parsedResult = result;
            console.log("parsedResult " + parsedResult);
            if (parsedResult == 1) {
                console.log("Venta realizada con exito");
                limpiarLocalStorage();
                location.reload();
                
            } else {
                console.log("Error al insertar el cliente");
            }
        }
    });
}


