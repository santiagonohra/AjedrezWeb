
var idTablero;
var idTableroJSON;
var idIni=null;
var idFin=null;

function registrarUsuario(){
    let datos={
        username: $("#userName").val(),
        clave:$("#pwd").val(),
        email:" ",
    }
    let misDatos=JSON.stringify(datos);
    $.ajax({
        dataType: 'json',
        data: misDatos,
        url:"http://localhost:8080/api/Usuario/save",
        type:'POST',
        contentType:'application/json',
        success:function(response) {
            mostrarUsuario();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("f");
        }
    });
}

function mostrarUsuario(){
    $.ajax({
        dataType: 'json',
        url:"http://localhost:8080/api/Usuario/all",
        type:'GET',
        contentType:'application/json',
        success:function(response) {
            printUsuario(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}

function printUsuario(r){
    $("#misDatos").empty();
    var t="";
    for(let i=0;i<r.length;i++){
        t+="Nombre de usuario: "+r[i].username+"<br>";
    }

    $("#misDatos").append(t)
}

function sendData(){
    //capturar Datos!
    //document.getElementById("userName").value

    let username = $("#userName").val();
    let clave = $("#pwd").val();


    if(username.trim() == "" || clave.trim() == ""){
        alert("f");
    }else{
        let nombreUsuario=JSON.stringify(username);
        $.ajax({
            dataType: 'json',
            data: nombreUsuario,
            url:"http://localhost:8080/api/Partida/crear",
            type:'POST',
            contentType:'application/json',
            success:function(response) {
                paintData(response);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("f");
            }
        });
    }

}
function unirse(){
    let miData={
        id: idTablero.toString(),
        userName: "Dimelo123"
    }
    miDatosJSON = JSON.stringify(miData);
    $.ajax({
        dataType: 'json',
        data: miDatosJSON,
        url:"http://localhost:8080/api/Partida/unirsePartida",
        type:'POST',
        contentType:'application/json',
        success:function(response) {
            alert("Se ha unido Dimelo123");
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}

function getData(r){
    if(!r){
        alert("Movimiento invalido!")
    }
    let miData={
        id: idTablero.toString(),
        userName: $("#userName").val()
    };
    miDatosJSON = JSON.stringify(miData);
    $.ajax({
        dataType: 'json',
        data: miDatosJSON,
        url:"http://localhost:8080/api/Partida/cargarPartida",
        type:'POST',
        contentType:'application/json',
        success:function(response) {
            paintData(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}

function leerClicks(id){
    if(idIni==null){
        idIni=id;
    }else if(idFin==null){
        idFin=id;
        hacerMovimiento(idIni, idFin);
        idIni=null;
        idFin=null;
    }
}

function hacerMovimiento(ini, finPos){
    let miData={
        posIX:ini.charAt(1),
        posIY:ini.charAt(0),
        posX:finPos.charAt(1),
        posY:finPos.charAt(0),
        idTablero: idTablero
    };
    let miDataJSON = JSON.stringify(miData);
    $.ajax({
        dataType: 'json',
        data:miDataJSON,
        url:"http://localhost:8080/api/Partida/esValido",
        type:'POST',
        contentType:'application/json',
        success:function(response) {
            getData(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });

}
function paintTablero(){
    let tab="";

    for(let i=1;i<9;i++){
        for (let j=1;j<9;j++){
            tab+="<div onclick=\"leerClicks(this.id);\" style=\"display: inline;font-size:50px;\" id='"+i+j+"' > <span> ^️ </span> </div>";

        }
        tab+="<br>";
    }
$("#tablero").html(tab);
}


function paintData(r){
    idTablero = r.id;
    $("#misDatos").empty();
    var t="";
    paintTablero();
    var fichero= {
        "PEON":'♙',
        "ALFIL":'♝',
        "TORRE": '♜',
        "REINA":'♕',
        "REY":'♔',
        "CABALLO":'♘'
     };

    for(let i=0;i<32;i++){
        $("#"+r.tablero.fichas[i].posY+r.tablero.fichas[i].posX).html(fichero[r.tablero.fichas[i].tipo]);
    }


    $("#misDatos").append(t)
}

