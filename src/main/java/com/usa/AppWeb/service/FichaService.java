package com.usa.AppWeb.service;

import com.usa.AppWeb.model.*;
import com.usa.AppWeb.repository.FichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService {
    @Autowired
    private FichaRepository fichaRepository;

    public boolean esMovValido(TestCasilla posFinal, TestFicha ficha){
        boolean validez;
        TestCasilla posInicial = new TestCasilla();
        posInicial.setPosX(ficha.getPosX());
        posInicial.setPosY(ficha.getPosY());
        if(ficha.getTipo()== TipoFicha.REY){
            if(posFinal.equals(posFinal)){
                return false;
            }
            if(Math.abs(posFinal.getPosX()-posInicial.getPosX())<=1 && Math.abs(posFinal.getPosY()-posInicial.getPosY())<=1){
                return true;
            }
        }
        if(ficha.getTipo()==TipoFicha.REINA){
            if(posInicial.equals(posFinal)){
                return false;
            }
            if(Math.abs(posFinal.getPosX()-posInicial.getPosX())==Math.abs(posFinal.getPosY()-posInicial.getPosY())){
                return true;
            }
            if(posFinal.getPosX()==posInicial.getPosX() || posFinal.getPosY()== posInicial.getPosY()){
                return true;
            }
        }

        if(ficha.getTipo()==TipoFicha.TORRE){
            if(posInicial.equals(posFinal)){
                return false;
            }
            return(posFinal.getPosX()==posInicial.getPosX() || posFinal.getPosY()== posInicial.getPosY());
        }
        if(ficha.getTipo()==TipoFicha.CABALLO){
            if(posInicial.equals(posFinal)){
                return false;
            }
            int difX=Math.abs(posInicial.getPosX()-posFinal.getPosX());
            int difY=Math.abs(posInicial.getPosY()-posFinal.getPosY());
            return((difX+difY==3 && difX!=0 && difY!=0));
        }
        if(ficha.getTipo()==TipoFicha.ALFIL){
            if(posInicial.equals(posFinal)){
                return false;
            }
            return(Math.abs(posFinal.getPosX()-posInicial.getPosX())==Math.abs(posFinal.getPosY()-posInicial.getPosY()));
        }
        if(ficha.getTipo()==TipoFicha.PEON){
            if(posInicial.equals(posFinal)){
                return false;
            }
            if(Math.abs(posInicial.getPosY()-posFinal.getPosY())==1 && Math.abs(posInicial.getPosX()-posFinal.getPosX())==0){
                return true;
            }
            //Primer mov
            if (Math.abs(posInicial.getPosY() - posFinal.getPosY()) == 2 && Math.abs(posInicial.getPosX() - posFinal.getPosX()) == 0 && (posInicial.getPosY() == 1 || posInicial.getPosY() == 6)) {
                return true;
            }
        }
        return false;
    }
}
