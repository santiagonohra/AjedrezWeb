// Dimensiones del tablero y creación del canva
const ANCHO = 700;
const ALTO = 700;

const FILAS=8;
const COLUMNAS=8;

const AnchoCeld=ANCHO/FILAS;
const AltoCeld=ALTO/COLUMNAS;

const canvas = document.createElement('canvas');
const context=canvas.getContext(('2d'));

canvas.width = ANCHO;
canvas.height = ALTO;


//arreglo de colores y piezas
const colores={
    blanco:'#85c1e9',
    negro:'#2471a3'
}
//fichas del ajedrez
const piezas={
    rey:['♚','♔'],
    caballo:['♞','♘'],
    torre:['♜','♖'],
    reina:['♛','♕'],
    peon:['♟','♙'],
    alfil:['♝','♗'],
}

const coloresPiezas={
    blancas:'#FFFFFF',
    negras:'#000000'
}

//Funciones a usar
const llenarTablero=()=>{

    for(let i=0;i<FILAS; i++){
        for (let j=0; j<COLUMNAS;j++){
            let colorCuadrado=colores.blanco;
            let colorTexto=colores.negro;
            if((i+j)%2) {
                colorCuadrado=colores.negro;
                colorTexto=colores.blanco;
            }
            context.fillStyle=colorCuadrado
            context.fillRect(i*AnchoCeld,j*AltoCeld,AnchoCeld,AltoCeld);

            //escribir las posiciones
            context.fillStyle=colorTexto;
            context.textBaseline='top';//alinear
            context.textAlign='start';
            context.font='8px Arial';
            context.fillText(`[${i};${j}]`,i*AnchoCeld+10,j*AltoCeld+10);



            //comprobar que hay una ficha
            const pieza=Matriz[i][j];
            if(pieza){
                context.fillStyle=pieza.color;
                context.textBaseline='middle';//alinear
                context.textAlign='center';
                context.font='64px Arial';
                context.fillText(pieza.tipoPieza[0],i*AnchoCeld+AnchoCeld/2,j*AltoCeld+AnchoCeld/2);
                context.fillStyle=coloresPiezas.negras;
                context.fillText(pieza.tipoPieza[1],i*AnchoCeld+AnchoCeld/2,j*AltoCeld+AnchoCeld/2);
            }


        }
    }
}


//Inicializar tablero
const Matriz=[];
for(let i=0;i<FILAS; i++){
    Matriz[i]=[];
    for (let j=0; j<COLUMNAS;j++){
        Matriz[i][j]=null;
    }
}


//Ubicar piezas
for(let k=0;k<COLUMNAS;k++) {
    Matriz[k][1] = {
        tipoPieza: piezas.peon,
        color: coloresPiezas.negras
    }
    Matriz[k][6] = {
        tipoPieza: piezas.peon,
        color: coloresPiezas.blancas
    }
}

for(let k=0;k<2;k++){
    Matriz[0][k*7]={
        tipoPieza:piezas.torre,
        color:k ? coloresPiezas.blancas : coloresPiezas.negras
    }
    Matriz[7][k*7]={
        tipoPieza:piezas.torre,
        color:k ? coloresPiezas.blancas : coloresPiezas.negras
    }
    Matriz[1][k*7]={
        tipoPieza:piezas.caballo,
        color:k ? coloresPiezas.blancas : coloresPiezas.negras
    }
    Matriz[6][k*7]={
        tipoPieza:piezas.caballo,
        color:k ? coloresPiezas.blancas : coloresPiezas.negras
    }
    Matriz[2][k*7]={
        tipoPieza:piezas.alfil,
        color:k ? coloresPiezas.blancas : coloresPiezas.negras
    }
    Matriz[5][k*7]={
        tipoPieza:piezas.alfil,
        color:k ? coloresPiezas.blancas : coloresPiezas.negras
    }
    Matriz[3][k*7]={
        tipoPieza:piezas.reina,
        color:k ? coloresPiezas.blancas : coloresPiezas.negras
    }
    Matriz[4][k*7]={
        tipoPieza:piezas.rey,
        color:k ? coloresPiezas.blancas : coloresPiezas.negras
    }

}

llenarTablero();

function paintData(r){
    //puedo modificar dinamicamente el contenido de la pagina
    $("#misDatos").empty();
    var texto="";
    for(let i=0;i<r.length;i++){
        texto+="Nombre: "+r[i].name+"<br>"

    }
    $("#misDatos").append(texto)
}

document.body.appendChild(canvas);