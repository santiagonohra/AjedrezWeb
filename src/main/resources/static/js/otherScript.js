function shotDown(){
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
    $("#tablero1").html(tab);
    console.log('Se mando esto: ' + tab);
}