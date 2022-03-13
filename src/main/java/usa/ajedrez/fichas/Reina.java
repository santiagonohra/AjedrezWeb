package usa.ajedrez.fichas;

import usa.ajedrez.Coordenada;
import usa.ajedrez.jugador.TipoJugador;

public class Reina extends Ficha{

    private Alfil alfil= new Alfil(TipoJugador.NEGRO);
    private Torre torre = new Torre(TipoJugador.NEGRO);

    public Reina(TipoJugador jugador){
        super(TipoFicha.REINA, jugador);
    }
    
    @Override
    public boolean movimientoValido(Coordenada posInicial, Coordenada posFinal) {
        return(alfil.movimientoValido(posInicial, posFinal) || torre.movimientoValido(posInicial, posFinal));
    }

    @Override
    public Coordenada[] getCamino(Coordenada posInicial, Coordenada posFinal) {
        Coordenada[] camino;
        if(torre.movimientoValido(posInicial, posFinal)){
            camino = torre.getCamino(posInicial, posFinal);
        }else{
            //Si es mov de alfil
            camino = alfil.getCamino(posInicial, posFinal);
        }
        return camino;
    }
}
