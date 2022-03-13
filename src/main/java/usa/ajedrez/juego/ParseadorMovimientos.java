package usa.ajedrez.juego;

import java.util.List;

public class ParseadorMovimientos {
    public static String parsear(List<Movimiento> listaMov){
        String movsParseados = "";
        for(Movimiento mov : listaMov){
            if(parsearMovimiento(mov).length()!=1){
                movsParseados+= " "+parsearMovimiento(mov);
            }else{
                movsParseados+= parsearMovimiento(mov);
            }
        }
        return movsParseados;
    }

    private static String parsearMovimiento(Movimiento movimiento){
        if(movimiento.getCoordenadaInicial().equals(movimiento.getCoordenadaFinal())){
            return movimiento.getFicha().getTipo().toString();
        }
        return movimiento.getCoordenadaInicial().getCoordenadaParseada()+movimiento.getCoordenadaFinal().getCoordenadaParseada();
    }
}
