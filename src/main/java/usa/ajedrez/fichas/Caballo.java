package usa.ajedrez.fichas;

import usa.ajedrez.Coordenada;
import usa.ajedrez.jugador.TipoJugador;

public class Caballo extends Ficha{

    public Caballo(TipoJugador jugador){
        super(TipoFicha.CABALLO, jugador);
    }

    @Override
    public boolean movimientoValido(Coordenada posInicial, Coordenada posFinal) {
        if(posInicial.equals(posFinal)){
            return false;
        }
        int difX=Math.abs(posInicial.getX()-posFinal.getX());
        int difY=Math.abs(posInicial.getY()-posFinal.getY());
        return((difX+difY==3 && difX!=0 && difY!=0));
    }

    @Override
    public Coordenada[] getCamino(Coordenada posInicial, Coordenada posFinal) {
        return new Coordenada[]{posInicial, posFinal};
    }
}
