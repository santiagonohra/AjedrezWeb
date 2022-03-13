package usa.ajedrez.fichas;

import usa.ajedrez.Coordenada;
import usa.ajedrez.jugador.TipoJugador;

public class Alfil extends Ficha{

    public Alfil(TipoJugador jugador){
        super(TipoFicha.ALFIL, jugador);
    }

    @Override
    public boolean movimientoValido(Coordenada posInicial, Coordenada posFinal) {
        if(posInicial.equals(posFinal)){
            return false;
        }
        return((Math.abs(posInicial.getX()-posFinal.getX()))==(Math.abs(posInicial.getY()-posFinal.getY())));
    }

    @Override
    public Coordenada[] getCamino(Coordenada posInicial, Coordenada posFinal) {
        int largoCamino = (Math.abs(posInicial.getX()-posFinal.getX())+Math.abs(posInicial.getY()-posFinal.getY()))/2+1;
        Coordenada[] camino = new Coordenada[largoCamino];

        int iX=Integer.signum(posFinal.getX()-posInicial.getX());
        int iY=Integer.signum(posFinal.getY()-posInicial.getY());

        for(int i=0;i<largoCamino;i++){
            camino[i]=new Coordenada(posInicial.getX()+iX*i, posInicial.getY()+iY*i);
        }

        return camino;
    }
}
