package com.usa.AppWeb.service;

import com.usa.AppWeb.model.*;
import com.usa.AppWeb.repository.FichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.List;

@Service
public class FichaService {
    @Autowired
    private FichaRepository fichaRepository;
    private TableroService tableroService;

    public boolean esMovValido(int posFinalX, int posFinalY, TestFicha ficha){
        boolean validez=false;
        int posIniX=ficha.getPosX();
        int posIniY=ficha.getPosY();

        if(posIniX==posFinalX && posIniY==posFinalY){
            return false;
        }
        if(ficha.getTipo()== TipoFicha.REY){
            return(Math.abs(posFinalX-posIniX)<=1 && Math.abs(posFinalY-posIniY)<=1);
        }
        if(ficha.getTipo()==TipoFicha.REINA){
            if(Math.abs(posFinalX-posIniX)==Math.abs(posFinalY-posIniY)){
                return true;
            }
            if(posFinalX==posIniX || posFinalY==posIniY){
                return true;
            }
        }

        if(ficha.getTipo()==TipoFicha.TORRE){
            return(posFinalX==posIniX || posFinalY==posIniY);
        }
        if(ficha.getTipo()==TipoFicha.CABALLO){
            int difX=Math.abs(posFinalX-posIniX);
            int difY=Math.abs(posFinalY-posIniY);
            return((difX+difY==3 && difX!=0 && difY!=0));
        }
        if(ficha.getTipo()==TipoFicha.ALFIL){
            return(Math.abs(posFinalX-posIniX)==Math.abs(posFinalY-posIniY));
        }
        if(ficha.getTipo()==TipoFicha.PEON){
            //                                                                                                          2 2    3 3
            if((Math.abs(posIniY-posFinalY)==1 && Math.abs(posIniX-posFinalX)==0) || (Math.abs(posFinalX-posIniX)==Math.abs((posIniY-posFinalY)) && Math.abs(posFinalX-posIniX)==1)){

                if(ficha.getEquipo() == EquipoFicha.BLANCO){

                    if(posIniY<posFinalY){

                        return true;
                    }
                }
                if(ficha.getEquipo() == EquipoFicha.NEGRO){
                    if(posIniY>posFinalY){
                        return true;
                    }
                }
            }
            //Primer mov
            if (Math.abs(posIniY-posFinalY) == 2 && Math.abs(posIniX-posFinalX) == 0 && (posIniY == 1 || posIniY == 6)) {
                if(ficha.getEquipo() == EquipoFicha.BLANCO){
                    if(posIniY<posFinalY){
                        return true;
                    }
                }
                if(ficha.getEquipo() == EquipoFicha.NEGRO){
                    if(posIniY>posFinalY){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
