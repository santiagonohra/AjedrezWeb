package usa.ajedrez.juego;

import usa.ajedrez.jugador.TipoJugador;

import java.util.ArrayList;
import java.util.List;

public class TableroControlador {
    private Tablero tablero;
    private TipoJugador jugadorActual = TipoJugador.BLANCO;
    private List<Movimiento> listaMovimientos = new ArrayList<Movimiento>();

    public TableroControlador(){
        this.tablero = new Tablero();
    }

    public void resetearTablero(){
        listaMovimientos = new ArrayList<Movimiento>();
        tablero.resetearTablero();
        jugadorActual = TipoJugador.BLANCO;
    }

    private void cambiarJugadorActual(){
        if(jugadorActual==TipoJugador.BLANCO){
            jugadorActual=TipoJugador.NEGRO;
        }else{
            jugadorActual=TipoJugador.BLANCO;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public TipoJugador getJugadorActual() {
        return jugadorActual;
    }

    public List<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }


}
