package usa.ajedrez.fichas;

import usa.ajedrez.Coordenada;
import usa.ajedrez.jugador.TipoJugador;

public class Peon extends Ficha{
    public Peon(TipoJugador jugador){
        super(TipoFicha.PEON, jugador);
    }

    @Override
    public boolean movimientoValido(Coordenada posInicial, Coordenada posFinal) {
        if(posInicial.equals(posFinal)){
            return false;
        }

        if(Math.abs(posInicial.getY()-posFinal.getY())==1 && Math.abs(posInicial.getX()-posFinal.getX())==0){
            return movPeonesBlancosONegros(posInicial, posFinal);
        }
        //Primer mov
        if (Math.abs(posInicial.getY() - posFinal.getY()) == 2 && Math.abs(posInicial.getX() - posFinal.getX()) == 0 && (posInicial.getY() == 1 || posInicial.getY() == 6)) {
            return movPeonesBlancosONegros(posInicial, posFinal);
        }
        return false;
    }


    //Las fichas blancas "suben" y las fichas blancas "bajan" (direccion de movimiento con respecto el tablero)
    private boolean movPeonesBlancosONegros(Coordenada posInicial, Coordenada posFinal) {
        if(this.getJugador() == TipoJugador.BLANCO){
            if(posInicial.getY() < posFinal.getY()){
                return true;
            }
        }
        if(this.getJugador()==TipoJugador.NEGRO){
            if(posInicial.getY() > posFinal.getY()){
                return true;
            }
        }
        return false;
    }


    @Override
    public Coordenada[] getCamino(Coordenada posInicial, Coordenada posFinal) {
        if(posInicial.getX()!= posFinal.getX()){
            return new Coordenada[]{posInicial, posFinal};
        }
        int largoCamino = Math.abs(posInicial.getY()-posFinal.getY())+1;
        Coordenada[] camino = new Coordenada[largoCamino];

        for(int i=0; i<largoCamino;i++){
            camino[i] = new Coordenada(posInicial.getX(), Math.min(posInicial.getY(), posFinal.getY())+ i);
        }

        return camino;
    }
}
