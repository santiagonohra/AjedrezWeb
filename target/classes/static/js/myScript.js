
var idTablero;
var idTableroJSON;

function sendData(){
    //capturar Datos!
    //document.getElementById("userName").value

<<<<<<< HEAD
    location.href = "tablero.html";
    let p={
        username:$("#userName").val(),
        clave:$("#pwd").val(),
    }
=======
    let username = $("#userName").val();
    let clave = $("#pwd").val();
>>>>>>> Santiago2


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
            getData(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });

}
function paintTablero(){
    console.log('Esta en print tablero');
    let tab="";

    for(let i=1;i<9;i++){
        for (let j=1;j<9;j++){
            //tab+="<div style=\"display: inline;font-size:50px;\" id='"+i+j+"' > <span> ^️ </span> </div>";
            if((i+j) % 2 == 0){
                tab += "<div class=\"field black\" id=\"" + i + "_" + j + "\"></div>";
            }else{
                tab += "<div class=\"field white\" id=\"" + i + "_" + j + "\"></div>";
            }
        }
        tab+="<br>";
    }
$("#tablero").html(tab);
console.log('Se mando esto: ' + tab);
}


function paintData(r){
    idTablero = r.id;
    $("#misDatos").empty();
    var t="";
    paintTablero();
    /*var fichero= {
        "PEON":'♙',
        "ALFIL":'♝',
        "TORRE": '♜',
        "REINA":'♕',
        "REY":'♔',
        "CABALLO":'♘'
     };

    for(let i=0;i<32;i++){
<<<<<<< HEAD
        $("#"+r[0].fichas[i].posY+r[0].fichas[i].posX).html(fichero[r[0].fichas[i].tipo]);
    }*/
=======
        $("#"+r.tablero.fichas[i].posY+r.tablero.fichas[i].posX).html(fichero[r.tablero.fichas[i].tipo]);
    }
>>>>>>> Santiago2


    $("#misDatos").append(t)
}

function isCorrectMove(posInicial, posFinal) {
    var movimiento = {
        posIX: parseInt(posInicial.charAt(0)),
        posIY: parseInt(posInicial.charAt(2)),
        posX: parseInt(posFinal.charAt(0)),
        posY: parseInt(posFinal.charAt(2))
    };

    return $.ajax({
        url: 'http://localhost:8080/api/Tablero/esValido',
        type: 'post',
        contentType: 'application/json',
        success: function (data) {
            console.log('success...');
            return data;
        },
        error: function () {
            console.log("error...");
            alert("Problema con el servidor!");
            return false;
        },
        data: JSON.stringify(movimiento)
    });
}


$(document).ready(function() {
    var startPosition = null;
    var destinationPosition = null;
    //var color = null;
    $(".field").mouseup(function(){
        console.log('mousedown = ' + $(this).attr('id'));
        console.log('mousedown = ' + $(this).find('#ALFIL2').length);

        if($(this).find('#ALFIL2').length == 1 && startPosition == null) {
            startPosition = $(this).attr('id');
            $(this).css('color', '#267340');

        } else if(startPosition != null) {
            destinationPosition = $(this).attr('id');
            console.log('destination = ' + destinationPosition);
            var resultMove = isCorrectMove(startPosition, destinationPosition);

            resultMove.then(function(response) {
                if(response) {
                    $('#ALFIL2').css('color', '#000000');

                    $('#ALFIL2').remove();
                    $('#'+destinationPosition).html('<span id="ALFIL2">&#9821;</span>');


                } else {
                    $('#ALFIL2').css('color', '#000000');

                    $('#ALFIL2').remove();
                    $('#'+startPosition).html('<span id="ALFIL2">&#9821;</span>');
                }

                startPosition = null;
                destinationPosition = null;
            });
        }
    });

})

