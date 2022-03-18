var idTablero = sessionStorage.getItem("idPartida");
var userName = sessionStorage.getItem("userName");
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
    console.log("Estoy en getPartida() inicio")
    let p = {
        id: idTablero,
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
            $("#turnoActual").html("Turno del equipo: "+response.turno);
            $("#oponentes").html(response.userName1+" vs. "+response.userName2);
            $("#idPartida").html("ID de la partida: "+response.id);
            paintFichas(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}

function paintFichas(r){
    var fichero= {
        "PEONBLANCO":'&#9817;',
        "ALFILBLANCO":'&#9815;',
        "TORREBLANCO": '&#9814;',
        "REINABLANCO":'&#9813;',
        "REYBLANCO":'&#9812;',
        "CABALLOBLANCO":'&#9816;',
        "PEONNEGRO":'&#9823;',
        "ALFILNEGRO":'&#9821;',
        "TORRENEGRO": '&#9820;',
        "REINANEGRO":'&#9819;',
        "REYNEGRO":'&#9818;',
        "CABALLONEGRO":'&#9822;'
    };
    shotDown();


    for(let i=0;i<32;i++){
        $("#"+r.tablero.fichas[i].posY+ "_" +r.tablero.fichas[i].posX).html(fichero[r.tablero.fichas[i].tipo+r.tablero.fichas[i].equipo]);
    }
}

function isCorrectMove(PosiI, PosiF){
    var movimiento = {
        posIX: parseInt(PosiI.charAt(2)),
        posIY: parseInt(PosiI.charAt(0)),
        posX: parseInt(PosiF.charAt(2)),
        posY: parseInt(PosiF.charAt(0)),
        idTablero: idTablero
    };
    console.log(movimiento.posIX + movimiento.posIY + movimiento.posX + movimiento.posY + movimiento.id);

    return $.ajax({
        url: 'http://localhost:8080/api/Partida/esValido',
        type: 'post',
        contentType: 'application/json',
        success: function (data) {
            getPartida(data);
        },
        async: false,
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

function unirse() {
    let miData = {
        id: 1,
        userName: userName
    };
    idTablero = 1,
        console.log(miData.id);
    miDatosJSON = JSON.stringify(miData);
    $.ajax({
        dataType: 'json',
        data: miDatosJSON,
        url: "http://localhost:8080/api/Partida/unirsePartida",
        type: 'POST',
        contentType: 'application/json',
        success: function (response) {
            alert("Se ha unido a la partida: "+idTablero);

            getPartida(response);


        },
        async: false,
        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
}
function crearPartida() {
    let nombreUsuario = JSON.stringify(userName);
    console.log(userName);
    $.ajax({
        dataType: 'json',
        data: nombreUsuario,
        url: "http://localhost:8080/api/Partida/crear",
        type: 'POST',
        contentType: 'application/json',
        success: function (response) {
            localStorage.setItem("id",response.id);
            bandera=false;
            ids(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("f");
        }
    });
}

function ids(r){
    console.log("estoy en igualando el ID global a "+r.id);
    idTablero = r.id;
    getPartida(r!=null);
}