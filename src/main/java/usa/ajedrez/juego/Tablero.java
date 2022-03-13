package usa.ajedrez.juego;

import net.bytebuddy.matcher.CollectionOneToOneMatcher;
import usa.ajedrez.Coordenada;
import usa.ajedrez.fichas.*;
import usa.ajedrez.jugador.TipoJugador;

public class Tablero {
    private Casilla[][] casillas = new Casilla[8][8];

    public Tablero(){
        ponerCasillas();
        ponerFichasBlancas();
        ponerFichasNegras();
    }

    public void resetearTablero(){
        ponerCasillas();
        ponerFichasBlancas();
        ponerFichasNegras();
    }

    public void ponerFichasBlancas(){
        casillas[0][0].setFicha(new Torre(TipoJugador.BLANCO));
        casillas[7][0].setFicha(new Torre(TipoJugador.BLANCO));
        casillas[1][0].setFicha(new Caballo(TipoJugador.BLANCO));
        casillas[6][0].setFicha(new Caballo(TipoJugador.BLANCO));
        casillas[2][0].setFicha(new Alfil(TipoJugador.BLANCO));
        casillas[5][0].setFicha(new Alfil(TipoJugador.BLANCO));
        casillas[4][0].setFicha(new Rey(TipoJugador.BLANCO));
        casillas[3][0].setFicha(new Reina(TipoJugador.BLANCO));

        casillas[0][1].setFicha(new Peon(TipoJugador.BLANCO));
        casillas[1][1].setFicha(new Peon(TipoJugador.BLANCO));
        casillas[2][1].setFicha(new Peon(TipoJugador.BLANCO));
        casillas[3][1].setFicha(new Peon(TipoJugador.BLANCO));
        casillas[4][1].setFicha(new Peon(TipoJugador.BLANCO));
        casillas[5][1].setFicha(new Peon(TipoJugador.BLANCO));
        casillas[6][1].setFicha(new Peon(TipoJugador.BLANCO));
        casillas[7][1].setFicha(new Peon(TipoJugador.BLANCO));
    }

    public void ponerCasillas(){
        for (int i=0;i<8;i++)
        {
            for (int j=0;j<8;j++)
            {
                casillas[i][j]=new Casilla(new Coordenada(i,j));
            }
        }
    }

    public void ponerFichasNegras(){
        casillas[0][7].setFicha(new Torre(TipoJugador.NEGRO));
        casillas[7][7].setFicha(new Torre(TipoJugador.NEGRO));
        casillas[1][7].setFicha(new Caballo(TipoJugador.NEGRO));
        casillas[6][7].setFicha(new Caballo(TipoJugador.NEGRO));
        casillas[2][7].setFicha(new Alfil(TipoJugador.NEGRO));
        casillas[5][7].setFicha(new Alfil(TipoJugador.NEGRO));
        casillas[4][7].setFicha(new Rey(TipoJugador.NEGRO));
        casillas[3][7].setFicha(new Reina(TipoJugador.NEGRO));

        casillas[0][6].setFicha(new Peon(TipoJugador.NEGRO));
        casillas[1][6].setFicha(new Peon(TipoJugador.NEGRO));
        casillas[2][6].setFicha(new Peon(TipoJugador.NEGRO));
        casillas[3][6].setFicha(new Peon(TipoJugador.NEGRO));
        casillas[4][6].setFicha(new Peon(TipoJugador.NEGRO));
        casillas[5][6].setFicha(new Peon(TipoJugador.NEGRO));
        casillas[6][6].setFicha(new Peon(TipoJugador.NEGRO));
        casillas[7][6].setFicha(new Peon(TipoJugador.NEGRO));
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setFicha(Coordenada coordenada, Ficha ficha){
        getCasilla(coordenada).setFicha(ficha);
    }

    public Casilla getCasilla(Coordenada coordenada){
        Casilla resultado = null;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(casillas[i][j].getCoordenada().equals(coordenada)){
                    resultado = casillas[i][j];
                }
            }
        }
        return resultado;
    }

    public void mover(Coordenada coordenadaInicial, Coordenada coordenadaFinal){
        mover(getCasilla(coordenadaInicial), getCasilla(coordenadaFinal));
    }

    public void mover(Casilla casillaInicial, Casilla casillaFinal){
        if(casillaFinal.estaOcupada()){
            matarFicha(casillaFinal);
        }
        casillaFinal.setFicha(casillaInicial.getFicha());
        casillaInicial.quitarFicha();
    }

    public void matarFicha(Casilla casilla){
        if(casilla.estaOcupada()){
            casilla.quitarFicha();
        }
    }

    public void printTablero(){
        for(int y=7;y>=0;y++){
            for(int x=0;x<8;x++){
                System.out.print(casillas[x][y].getFichaString()+" ");
            }
            System.out.println("");
        }
    }
}
