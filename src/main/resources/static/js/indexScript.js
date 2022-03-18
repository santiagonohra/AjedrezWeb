
function sendDataUser(){
    //capturar Datos!
    //document.getElementById("userName").value
    console.log("entro al metodo");
    //let v = $("#pwdr").val();
    let p={
        username:$("#userName").val(),
        email:$("#correo").val(),
        clave:$("#pwd").val()
    };

    console.log("Creo la estructura");
    userName=$("#userNameInicio").val();
    let claveConfirmacion = $("#pwdr").val();

    console.log("\n" + p.email + p.clave + p.username);

    if(p.clave == claveConfirmacion){
        console.log("Las claves si son iguales");
        if(p.username.trim() == ""|| p.email.trim() == "" || p.clave.trim() == ""){
            alert("Campos vacios");
        }else if(p.clave==claveConfirmacion) {
                let personToSend = JSON.stringify(p);
                console.log("AJAX");
                $.ajax({
                    dataType: 'json',
                    data: personToSend,
                    url: "http://localhost:8080/api/Usuario/save",
                    type: 'POST',
                    contentType: 'application/json',
                    async: false,
                    success: function (response) {
                        alert("Registro exitoso, inicie sesion");
                        console.log(response);
                        //getDataUser();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });
        }
    }else{
        alert("Las contraseñas no coinciden");
    }

}

function iniciarSesion(){
    let p={
        username:$("#userNameInicio").val(),
        clave:$("#pwdInicio").val(),
        email: "-"
    };
    let userName =$("#userNameInicio").val();

    if(p.username.trim() == "" || p.clave.trim() == "") {
        alert("Campos vacios");
    }
    else {
        let registerValidation = JSON.stringify(p);
        $.ajax({
            dataType: 'json',
            data: registerValidation,
            url: "http://localhost:8080/api/Usuario/iniciarSesion",
            type: 'POST',
            async: false,
            contentType: 'application/json',
            success: function (response) {
                if(response == true){
                    alert("Inicio de sesión exitoso");
                    sessionStorage.setItem("userName", userName);
                    location.replace("../Jugar.html");
                }else{
                    alert("No se encuentra registrado ningun usuario con esos datos");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown)
            }
        });

    }
}
function getDataUser(){

    $.ajax({
        dataType: 'json',
        url:"http://localhost:8080/api/Usuario/all",
        type:'GET',
        contentType:'application/json',
        async: false,
        success:function(response) {
            console.log(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}