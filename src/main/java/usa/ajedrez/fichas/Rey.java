package usa.ajedrez.fichas;

import usa.ajedrez.Coordenada;
import usa.ajedrez.jugador.TipoJugador;

public class Rey extends Ficha{
    public Rey(TipoJugador jugador){
        super(TipoFicha.REY, jugador);
    }

    @Override
    public boolean movimientoValido(Coordenada posInicial, Coordenada posFinal) {
        if(posInicial.equals(posFinal)){
            return false;
        }
        if(Math.abs(posFinal.getX()-posInicial.getX())<=1 && Math.abs(posFinal.getY()-posInicial.getY())<=1){
            return true;
        }
        return false;
    }

    @Override
    public Coordenada[] getCamino(Coordenada posInicial, Coordenada posFinal) {
        return new Coordenada[]{posInicial, posFinal};
    }
}
