
var idTablero;
var idTableroJSON;

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

function getData(){
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

function hacerMovimiento(){
    let miData={
        posIX:$("#movIX").val(),
        posIY:$("#movIY").val(),
        posX:$("#movX").val(),
        posY:$("#movY").val(),
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
            getData();
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });

}
function paintTablero(){
    let tab="";

    for(let i=1;i<9;i++){
        for (let j=1;j<9;j++){
            tab+="<div style=\"display: inline;font-size:50px;\" id='"+i+j+"' > <span> ^️ </span> </div>";

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

