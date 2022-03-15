
function sendData(){
    //capturar Datos!
    //document.getElementById("userName").value

    let p={
        name:$("#userName").val(),
        email:$("#email").val(),
        password:$("#pwd").val(),
        gender:$("#gender").is(':checked'),
    }
    let personToSend=JSON.stringify(p);
    $.ajax({
        dataType: 'json',
        data:personToSend,
        url:"api/Person/save",
        type:'POST',
        contentType:'application/json',
        success:function(response) {
            getData();
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}

function getData(){
    $.ajax({
        dataType: 'json',
        url:"api/Person/all",
        type:'GET',
        contentType:'application/json',
        success:function(response) {
            paintData(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {

        }
    });
}
function paintData(r){
    $("#misDatos").empty();
    var t="";
    for(let i=0;i<r.length;i++){
        let gender="hombre";
        if(r[i].gender){
            gender="mujer";
        }
        t+=`
            <a href="#">
                <div class="casilla ${gender}" onclick='mehicisteclick("${r[i].name}","${r[i].id}")'>
                     ${r[i].name}
                </div>
            </a>
    `;
    }
    $("#misDatos").append(t);
}

var originOrDestiny=0;
function mehicisteclick(info1,info2){

    if(originOrDestiny==0){
        originOrDestiny=1;
        console.log("seleccion origen:")
        console.log(info1);
        console.log(info2);
    }else if(originOrDestiny==1){
        originOrDestiny=0;
        console.log("seleccion destino:")
        console.log(info1);
        console.log(info2);
        console.log("enviando jugada....")

    }

}

function isCorrectMove(source, destination, type) {
    var figure = {
        source: source,
        destination: destination,
        type: type
    };

    return $.ajax({
        url: 'http://127.0.0.1:8081/api/chess/is-correct-move',
        type: 'post',
        contentType: 'application/json',
        success: function (data) {
            console.log('success...');
            return data;
        },
        error: function() {
            console.log("error...");
            alert("Problema con el servidor!");
            return false;
        },
        data: JSON.stringify(figure)
    });
}

$(document).ready(function() {
    var startPosition = null;
    var destinationPosition = null;

    $(".field").mouseup(function(){
        console.log('mousedown = ' + $(this).attr('id'));
        console.log('mousedown = ' + $(this).find('#BISHOP2').length);

        if($(this).find('#BISHOP2').length === 1 && startPosition == null) {
            startPosition = $(this).attr('id');
            $('#BISHOP2').css('color', '#267340');

        } else if(startPosition != null) {
            destinationPosition = $(this).attr('id');

            var resultMove = isCorrectMove(startPosition, destinationPosition, 'BISHOP');

            resultMove.then(function(response) {
                if(response) {
                    $('#BISHOP2').css('color', '#000000');

                    $('#BISHOP2').remove();
                    $('#'+destinationPosition).html('<span id="BISHOP2">&#9821;</span>');


                } else {
                    $('#BISHOP2').css('color', '#000000');

                    $('#BISHOP2').remove();
                    $('#'+startPosition).html('<span id="BISHOP2">&#9821;</span>');
                }

                startPosition = null;
                destinationPosition = null;
            });
        }
    });

})
