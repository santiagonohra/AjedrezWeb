package usa.ajedrez.juego;

import usa.ajedrez.Coordenada;
import usa.ajedrez.fichas.Ficha;

public class Casilla {
    private Coordenada coordenada;
    private Ficha ficha = null;

    public Casilla(Coordenada coordenada, Ficha ficha){
        this.coordenada = coordenada;
        this.ficha = ficha;
    }

    public Casilla(Coordenada coordenada){
        this(coordenada, null);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public boolean sonIguales(Casilla casilla){
        return (casilla.getCoordenada().equals(coordenada));
    }

    public boolean estaOcupada(){
        return (ficha!=null);
    }

    public String getFichaString(){
        if(ficha==null){
            return "  ";
        }
        return ficha.toString();
    }

    public void quitarFicha(){
        ficha=null;
    }

    public void setFicha(Ficha ficha){
        this.ficha = ficha;
    }
}
