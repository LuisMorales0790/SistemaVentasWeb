$(document).ready(function(){
    
    $("#form-login").submit(function (event) {
        
        event.preventDefault();
        autenticarUsuario();
    });
});

function autenticarUsuario(){
    let username = $("#usuario").val();
    console.log(username);
    let contrasena = $("#contrasena").val();
    console.log(contrasena);
    $.ajax({
       type: "GET",
       url: "./Validar",
       data: $.param({
          username: username,
          contrasena: contrasena
       }),      
       success: function (result){
           let parsedResult = JSON.parse(result);
           
           if(parsedResult != false){
               let idempleado = parsedResult["idEmpleado"];
               document.location.href = "Principal.jsp?idempleado=" + idempleado;
           }else{
               $("#login-error").removeClass("d-none");              
               setTimeout(function (){
                   location.reload();
               }, 3000);
           }
       }
    });
}


