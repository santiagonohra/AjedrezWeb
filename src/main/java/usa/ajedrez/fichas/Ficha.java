package usa.ajedrez.fichas;

import usa.ajedrez.Coordenada;
import usa.ajedrez.jugador.TipoJugador;

public abstract class Ficha {
    private TipoFicha tipo;
    private TipoJugador jugador;

    public Ficha(TipoFicha tipo, TipoJugador jugador) {
        this.tipo = tipo;
        this.jugador = jugador;
    }

    public String toString() {
        return jugador.toString() + tipo.toString();
    }

    public TipoFicha getTipo() {
        return tipo;
    }

    public TipoJugador getJugador() {
        return jugador;
    }

    public abstract boolean movimientoValido(Coordenada posInicial, Coordenada posFinal);

    public abstract Coordenada[] getCamino(Coordenada posInicial, Coordenada posFinal);
}
