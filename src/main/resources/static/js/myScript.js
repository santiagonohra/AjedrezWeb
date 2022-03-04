
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
        let personToSend=JSON.stringify(p);
        $.ajax({
            dataType: 'json',
            data:personToSend,
            url:"http://localhost:8080/api/Usuario/save",
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
        url:"http://localhost:8080/api/Usuario/all",
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
        t+="Nombre de usuario: "+r[i].username+"<br>";
    }

    $("#misDatos").append(t)
}

