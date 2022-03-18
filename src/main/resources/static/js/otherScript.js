var startPosition=null;
var destinationPosition=null;

function shotDown(){
    console.log('Esta en print tablero');
    let tab="";

    for(let i=1;i<9;i++){
        for (let j=1;j<9;j++){
            //tab+="<div style=\"display: inline;font-size:50px;\" id='"+i+j+"' > <span> ^️ </span> </div>";
            if((i+j) % 2 == 0){
                tab += "<div  class=\"field black\" onclick=\"clicknm(this.id)\" id=\"" + i + "_" + j + "\"></div>";
            }else{
                tab += "<div class=\"field white\" onclick=\"clicknm(this.id)\" id=\"" + i + "_" + j + "\"></div>";
            }
        }
        tab+="<br>";
    }
    $("#tablero1").html(tab);
    console.log('Se mando esto: ' + tab);
}

function getPartida(r){
    let p = {
        id: id,
        username: userName,
    }
    if(r==false){
        alert("Movimiento invalido");
    }

    let miDatosJSON = JSON.stringify(p);
    $.ajax({
        dataType: 'json',
        data: miDatosJSON,
        url:"http://localhost:8080/api/Partida/cargarPartida",
        type:'POST',
        contentType:'application/json',
        success:function(response) {
            paintFichas(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}

function paintFichas(r){
    var fichero= {
        "PEON":'&#9823;',
        "ALFIL":'&#9815;',
        "TORRE": '&#9814;',
        "REINA":'&#9812;',
        "REY":'&#9813;',
        "CABALLO":'&#9816;'
    };
    shotDown();


    for(let i=0;i<32;i++){
        $("#"+r.tablero.fichas[i].posY+ "_" +r.tablero.fichas[i].posX).html(fichero[r.tablero.fichas[i].tipo]);
    }
}

function isCorrectMove(PosiI, PosiF){
    var movimiento = {
        posIX: parseInt(PosiI.charAt(2)),
        posIY: parseInt(PosiI.charAt(0)),
        posX: parseInt(PosiF.charAt(2)),
        posY: parseInt(PosiF.charAt(0)),
        idTablero: id
    };
    console.log(movimiento.posIX + movimiento.posIY + movimiento.posX + movimiento.posY + movimiento.id);

    return $.ajax({
        url: 'http://localhost:8080/api/Partida/esValido',
        type: 'post',
        contentType: 'application/json',
        success: function (data) {
            getPartida(data);
        },
        error: function () {
            console.log("error...");
            alert("Problema con el servidor!");
            return false;
        },
        data: JSON.stringify(movimiento)
    });

}

function clicknm(id){
    if(startPosition == null) {
        startPosition = id;
    } else if(startPosition != null) {
        destinationPosition = id;
        console.log('destination = ' + destinationPosition);
        isCorrectMove(startPosition, destinationPosition);
        startPosition=null;
        destinationPosition=null;
    }
}

function unirse(){
    let miData={
        id: id,
        userName: $("#userName").val()
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