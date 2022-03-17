
function sendData(){
    //capturar Datos!
    //document.getElementById("userName").value

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
        $("#"+r[0].fichas[i].posY+r[0].fichas[i].posX).html(fichero[r[0].fichas[i].tipo]);
    }


    $("#misDatos").append(t)
}

