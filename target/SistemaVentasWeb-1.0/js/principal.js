 var idempleado = new URL(location.href).searchParams.get("idempleado");
 var user;
 
$(document).ready(function (){
    console.log("Principal"); 
    console.log(idempleado);
      
    getUsuario(idempleado).then(function (){
        $("#nombres").html(user.nombre);
        $("#usuario").html(user.user);
        $("#salir").click(function (){
          document.location.href = "index.jsp";
        });
    });  
});

async function getUsuario(id){
    console.log("EL id es: " + id);
let accion = "validar";   
await   $.ajax({
        type: "GET",
        url: "./Controlador",
        data: $.param({
            accion: accion,
            idempleado: id
        }),
        success: function (result) {
            console.log("no parseado: " + result);
            let parsedResult = JSON.parse(result);
            console.log("parseado: " + parsedResult);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function iframePage(pagina){
    $("#myFrame").attr("src", pagina);
}


