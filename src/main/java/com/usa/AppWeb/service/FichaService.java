package com.usa.AppWeb.service;

import com.usa.AppWeb.model.*;
import com.usa.AppWeb.repository.FichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FichaService {
    @Autowired
    private FichaRepository fichaRepository;
    @Autowired
    private TableroService tableroService;

    public boolean esMovValido(int posFinalX, int posFinalY, int posIniX, int posIniY, int idTablero){
        boolean validez=false;
        TestFicha ficha = tableroService.getFichaPorPos(posIniX, posIniY, idTablero);


        if(posIniX==posFinalX && posIniY==posFinalY){
            return false;
        }
        if(ficha.getTipo()== TipoFicha.REY){
            return(Math.abs(posFinalX-posIniX)<=1 && Math.abs(posFinalY-posIniY)<=1);
        }
        if(ficha.getTipo()==TipoFicha.REINA){
            if(Math.abs(posFinalX-posIniX)==Math.abs(posFinalY-posIniY)){
                return(esCaminoValido(ficha, posFinalX, posFinalY, idTablero));
            }
            if(posFinalX==posIniX || posFinalY==posIniY){
                return(esCaminoValido(ficha, posFinalX, posFinalY, idTablero));
            }
        }

        if(ficha.getTipo()==TipoFicha.TORRE){
            return((posFinalX==posIniX || posFinalY==posIniY) && (esCaminoValido(ficha, posFinalX, posFinalY, idTablero)));
        }
        if(ficha.getTipo()==TipoFicha.CABALLO){
            int difX=Math.abs(posFinalX-posIniX);
            int difY=Math.abs(posFinalY-posIniY);
            return((difX+difY==3 && difX!=0 && difY!=0) && (esCaminoValido(ficha, posFinalX, posFinalY, idTablero)));
        }
        if(ficha.getTipo()==TipoFicha.ALFIL){
            return((Math.abs(posFinalX-posIniX)==Math.abs(posFinalY-posIniY)) && (esCaminoValido(ficha, posFinalX, posFinalY, idTablero)));
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
            if (Math.abs(posIniY-posFinalY) == 2 && Math.abs(posIniX-posFinalX) == 0 && (posIniY == 2 || posIniY == 7)) {
                System.out.println("Primer movimiento ");
                if(ficha.getEquipo() == EquipoFicha.BLANCO){
                    if(posIniY<posFinalY){
                        return(esCaminoValido(ficha, posFinalX, posFinalY, idTablero));
                    }
                }
                if(ficha.getEquipo() == EquipoFicha.NEGRO){
                    if(posIniY>posFinalY){
                        return(esCaminoValido(ficha, posFinalX, posFinalY, idTablero));
                    }
                }
            }
        }
        return false;
    }

    public boolean esCaminoValido(TestFicha ficha, int posFinalX, int posFinalY, int idTablero){
        Casilla[] camino;
        if(ficha.getTipo()==TipoFicha.CABALLO){
            return true;
        }

        if(ficha.getTipo()==TipoFicha.REINA){
            //si es mov de alfil
            if(Math.abs(posFinalX-ficha.getPosX())==Math.abs(posFinalY-ficha.getPosY())){
                int largo = Math.abs(posFinalX-ficha.getPosX())-1;
                camino = new Casilla[largo];
                int direccionX = Integer.signum(posFinalX-ficha.getPosX());
                int direccionY = Integer.signum(posFinalY-ficha.getPosY());
                for(int i=0;i<largo;i++){
                    camino[i]=new Casilla((ficha.getPosX()+((i+1)*direccionX)), (ficha.getPosY()+((i+1)*direccionY)));
                }
                for(Casilla casilla : camino){
                    if(tableroService.getFichaPorPos(casilla.getPosX(), casilla.getPosY(), idTablero)!=null){
                        return false;
                    }
                }
                return true;
            }
            //Si es mov de torre
            if(posFinalX==ficha.getPosX() || posFinalY==ficha.getPosY()){
                int largo = Math.abs(posFinalX-ficha.getPosX())+Math.abs(posFinalY-ficha.getPosY())-1;
                camino = new Casilla[largo];

                int direccionX = Integer.signum(posFinalX-ficha.getPosX());
                int direccionY = Integer.signum(posFinalY-ficha.getPosY());
                for(int i=0;i<largo;i++){
                    camino[i]=new Casilla((ficha.getPosX()+((i+1)*direccionX)), (ficha.getPosY()+((i+1)*direccionY)));
                }
                for(Casilla casilla : camino){
                    if(tableroService.getFichaPorPos(casilla.getPosX(), casilla.getPosY(), idTablero)!=null){
                        return false;
                    }
                }
                return true;


            }
        }

        if(ficha.getTipo()==TipoFicha.ALFIL){
            if(Math.abs(posFinalX-ficha.getPosX())==Math.abs(posFinalY-ficha.getPosY())){
                int largo = Math.abs(posFinalX-ficha.getPosX())-1;
                camino = new Casilla[largo];
                int direccionX = Integer.signum(posFinalX-ficha.getPosX());
                int direccionY = Integer.signum(posFinalY-ficha.getPosY());
                for(int i=0;i<largo;i++){
                    camino[i]=new Casilla((ficha.getPosX()+((i+1)*direccionX)), (ficha.getPosY()+((i+1)*direccionY)));
                }
                for(Casilla casilla : camino){
                    if(tableroService.getFichaPorPos(casilla.getPosX(), casilla.getPosY(), idTablero)!=null){
                        return false;
                    }
                }
                return true;
            }
        }

        if(ficha.getTipo()==TipoFicha.TORRE){
            if(posFinalX==ficha.getPosX() || posFinalY==ficha.getPosY()){
                int largo = Math.abs(posFinalX-ficha.getPosX())+Math.abs(posFinalY-ficha.getPosY())-1;
                camino = new Casilla[largo];

                int direccionX = Integer.signum(posFinalX-ficha.getPosX());
                int direccionY = Integer.signum(posFinalY-ficha.getPosY());
                for(int i=0;i<largo;i++){
                    camino[i]=new Casilla((ficha.getPosX()+((i+1)*direccionX)), (ficha.getPosY()+((i+1)*direccionY)));
                }
                for(Casilla casilla : camino){
                    if(tableroService.getFichaPorPos(casilla.getPosX(), casilla.getPosY(), idTablero)!=null){
                        return false;
                    }
                }
                return true;
            }
        }

        if(ficha.getTipo()==TipoFicha.PEON){
            if(posFinalX==ficha.getPosX() && posFinalY!=ficha.getPosY()){
                int largo = Math.abs(posFinalX-ficha.getPosX())+Math.abs(posFinalY-ficha.getPosY())-1;
                camino = new Casilla[largo];

                int direccionX = Integer.signum(posFinalX-ficha.getPosX());
                int direccionY = Integer.signum(posFinalY-ficha.getPosY());
                for(int i=0;i<largo;i++){
                    camino[i]=new Casilla((ficha.getPosX()+((i+1)*direccionX)), (ficha.getPosY()+((i+1)*direccionY)));
                }
                for(Casilla casilla : camino){
                    if(tableroService.getFichaPorPos(casilla.getPosX(), casilla.getPosY(), idTablero)!=null){
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }
}
