var userName = sessionStorage.getItem("userName");

function crearPartida() {
    let nombreUsuario = JSON.stringify(userName);
    $.ajax({
        dataType: 'json',
        data: nombreUsuario,
        url: "http://localhost:8080/api/Partida/crear",
        type: 'POST',
        contentType: 'application/json',
        async: false,
        success: function (response) {
            alert(response.id);
            sessionStorage.setItem("idPartida", response.id);
            location.replace("../tablero.html");
            getPartida();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Error al crear la partida");
        }
    });
}

