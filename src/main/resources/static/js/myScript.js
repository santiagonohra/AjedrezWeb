function isCorrectMove(posInicial, posFinal, ficha, color) {
    var casillaI = {
        posInicial: posInicial,
        posFinal: posFinal,
        ficha: ficha,
        color: color
    };

    return $.ajax({
        url: 'http://localhost:8080/api/Tablero/chess/is-correct-move',
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
        data: JSON.stringify(casillaI)
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
            $('#ALFIL2').css('color', '#267340');

        } else if(startPosition != null) {
            destinationPosition = $(this).attr('id');
            console.log('destination = ' + destinationPosition);
            var resultMove = isCorrectMove(startPosition, destinationPosition, 'ALFIL', '&#9821;');

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
