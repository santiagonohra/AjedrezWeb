var idTablero = sessionStorage.getItem("idPartida");
var userName = sessionStorage.getItem("userName");

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

function getPartida(){
    let p = {
        id: idTablero,
        username: userName,
    };

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
            alert("Error generando la partida :(");
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