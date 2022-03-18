
function sendData(){
    //capturar Datos!
    //document.getElementById("userName").value

    location.href = "tablero.html";
    let p={
        username:$("#userName").val(),
        clave:$("#pwd").val(),
    }

    if(p.username.trim() == "" || p.clave.trim() == ""){
        alert("f");
    }else{
        //let personToSend=JSON.stringify(p);
        $.ajax({
            dataType: 'json',
            //data:personToSend,
            url:"http://localhost:8080/api/Tablero/save",
            type:'POST',
            contentType:'application/json',
            success:function(response) {
                getData();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("f");
            }
        });
    }

}

function getData(){
    $.ajax({
        dataType: 'json',
        url:"http://localhost:8080/api/Tablero/all",
        type:'GET',
        contentType:'application/json',
        success:function(response) {
            paintData(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}

function getData2(){
    $.ajax({
        dataType: 'json',
        url:"http://localhost:8080/api/Tablero/all",
        type:'GET',
        contentType:'application/json',
        success:function(response) {
            hacerMovimiento(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}



function hacerMovimiento(r){
    let miData={
        //ficha: r[0].fichas[1],
        posIX:$("#movIX").val(),
        posIY:$("#movIY").val(),
        posX:$("#movX").val(),
        posY:$("#movY").val(),
    };
    let miDataJSON = JSON.stringify(miData);
    $.ajax({
        dataType: 'json',
        data:miDataJSON,
        url:"http://localhost:8080/api/Tablero/esValido",
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
        $("#"+r[0].fichas[i].posY+r[0].fichas[i].posX).html(fichero[r[0].fichas[i].tipo]);
    }*/


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

