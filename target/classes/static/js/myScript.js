var userName;
var id;


function sendDataUser(){
    //capturar Datos!
    //document.getElementById("userName").value


    //let v = $("#pwdr").val();
    let p={
        username:$("#userName").val(),
        email:$("#correo").val(),
        clave:$("#pwd").val(),
        claveConfirmacion:$("#pwdr").val(),
    }
    userName=$("#userNameInicio").val();

    console.log("\n" + p.email + p.clave + p.username);

    if(p.username.trim() == ""|| p.email.trim() == "" || p.clave.trim() == ""){
        alert("Campos vacios");
    }
    else if(p.clave==p.claveConfirmacion) {
        if (/^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/.exec(p.email)) {
            let personToSend = JSON.stringify(p);
            $.ajax({
                dataType: 'json',
                data: personToSend,
                url: "http://localhost:8080/api/Usuario/save",
                type: 'POST',
                contentType: 'application/json',
                success: function (response) {
                    alert("Registro exitoso");
                    console.log(response)
                    //getDataUser();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown)
                }
            });
        }
    }
    else{
        alert("Las contraseñas no coinciden");
    }

}

function getDataUser(){

    $.ajax({
        dataType: 'json',
        url:"http://localhost:8080/api/Usuario/all",
        type:'GET',
        contentType:'application/json',
        success:function(response) {
            paintDataUser(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}
function paintDataUser(r){
    $("#misDatos").empty();
    userName = $("#userNameInicio").val();

    sessionStorage.setItem("userName", userName);
    sessionStorage.setItem("userName", userName);
    sessionStorage.setItem("userName", userName);
    sessionStorage.setItem("userName", userName);
    let p={
        usernameLogin:$("#userNameInicio").val(),
        emailLogin:$("#emailLogin").val(),
        claveLogin:$("#pwdInicio").val(),
    }

    if(p.usernameLogin.trim() == "" || p.claveLogin.trim() == "") {
        alert("Campos vacios");
    }
    else {
        var bandera = 0;
        //var t = "";
        for (let i = 0; i < r.length; i++) {
            //t += "Nombre de usuario: " + r[i].username + "<br>";
            if (p.usernameLogin == r[i].username && p.claveLogin == r[i].clave) {
                bandera = 1;
                break;
            }
        }
        if (bandera == 0) {
            alert("Usuario y/o clave invalidos");
        }
        if (bandera == 1) {
            alert(`Bienvenido ${p.usernameLogin}`);
            bandera = 0;
            location.href = "Jugar.html";
            userName=$("#userNameInicio").val();
        }
        //$("#misDatos").append(t)
    }
}

