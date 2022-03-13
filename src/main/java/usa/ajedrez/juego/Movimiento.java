package usa.ajedrez.juego;

import usa.ajedrez.Coordenada;
import usa.ajedrez.fichas.Ficha;

public class Movimiento {
    private Coordenada coordenadaInicial, coordenadaFinal, coordenadaDeCaptura;
    private Ficha ficha, fichaMatada = null;

    public Movimiento(Coordenada coordenadaInicial, Coordenada coordenadaFinal, Ficha ficha) {
        this(coordenadaInicial, coordenadaFinal, ficha, null);
    }

    public Movimiento(Coordenada coordenadaInicial, Coordenada coordenadaFinal, Ficha ficha, Casilla casillaDeCaptura) {
        this.coordenadaInicial = coordenadaInicial;
        this.coordenadaFinal = coordenadaFinal;
        this.ficha = ficha;
        if(casillaDeCaptura!=null){
            this.fichaMatada=casillaDeCaptura.getFicha();
            this.coordenadaDeCaptura=casillaDeCaptura.getCoordenada();
        }
    }

    public Coordenada getCoordenadaInicial() {
        return coordenadaInicial;
    }

    public Coordenada getCoordenadaFinal() {
        return coordenadaFinal;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public Ficha getFichaMatada(){
        return fichaMatada;
    }

    public Coordenada getCoordenadaDeCaptura() {
        return coordenadaDeCaptura;
    }

    public boolean esMatada(){
        return(fichaMatada!=null);
    }
}
