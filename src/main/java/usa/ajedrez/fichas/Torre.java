package usa.ajedrez.fichas;

import usa.ajedrez.Coordenada;
import usa.ajedrez.jugador.TipoJugador;

public class Torre extends Ficha{

    public Torre(TipoJugador jugador){
        super(TipoFicha.ALFIL, jugador);
    }

    @Override
    public boolean movimientoValido(Coordenada posInicial, Coordenada posFinal) {
        if (posInicial.equals(posFinal)) {
            return false;
        }
        return(posInicial.getX()==posFinal.getX() || posInicial.getY()==posFinal.getY());
    }

    @Override
    public Coordenada[] getCamino(Coordenada posInicial, Coordenada posFinal) {
        int largoCamino=Math.abs(posInicial.getX()-posFinal.getX())+Math.abs(posInicial.getY()-posFinal.getY())+1;
        Coordenada[] camino = new Coordenada[largoCamino];

        for(int i=0;i<largoCamino;i++){
            if(posInicial.getX()==posFinal.getX()){
                camino[i]=new Coordenada(posInicial.getX(), Math.min(posInicial.getY(),posFinal.getY())+i);
            }else{
                camino[i]=new Coordenada(Math.min(posInicial.getX(), posFinal.getX())+i, posInicial.getY());
            }
        }
        return camino;
    }
}
