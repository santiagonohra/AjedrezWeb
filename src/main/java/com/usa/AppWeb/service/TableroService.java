package com.usa.AppWeb.service;

import com.usa.AppWeb.model.*;
import com.usa.AppWeb.repository.TableroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableroService {
    @Autowired
    private TableroRepository tableroRepository;

    public Tablero save(Tablero tablero) {
        return tableroRepository.save(tablero);
    }

    public List<Tablero> findAll(){
        return (List<Tablero>) tableroRepository.getAll();
    }

    public Optional<Tablero> getById(int id){
        return tableroRepository.getById(id);
    }

    //Get ficha por pos del tablero con id tal
    public TestFicha getFichaPorPos(int posX, int posY, int idTablero){
        TestFicha returnFicha=null;
        Optional<Tablero> miTablero = getById(idTablero);
        Tablero miTableroObjeto = miTablero.get();
        List<TestFicha> fichas = miTableroObjeto.getFichas();

        for(TestFicha ficha : fichas){
            if(ficha.equalCoords(posX, posY)){
                returnFicha = ficha;
            }
        }
        return returnFicha;
    }
    public boolean moverFicha(int posIniX, int posIniY, int idTablero, int posFinalX, int posFinalY, EquipoFicha equipoMovimiento){
        Optional<Tablero> miTablero = getById(idTablero);
        Tablero miTableroObjeto = miTablero.get();
        //Save hace update si le mando un objeto con ya predefined id
        List<TestFicha> fichas = miTableroObjeto.getFichas();
        TestFicha ficha1 = getFichaPorPos(posIniX, posIniY, idTablero);
        TestFicha ficha2 = getFichaPorPos(posFinalX, posFinalY, idTablero);

        if(ficha1.getEquipo()!=equipoMovimiento){
            return false;
        }
        if((ficha1.getTipo()==TipoFicha.PEON && ficha2!=null && ficha1.getEquipo()!=ficha2.getEquipo()) && (Math.abs(posFinalX-posIniX)==Math.abs((posIniY-posFinalY)) && Math.abs(posFinalX-posIniX)==1)){
            return setFichaPorPos(posIniX, posIniY, idTablero, posFinalX, posFinalY, true);
        }

        if((ficha1.getTipo()==TipoFicha.PEON && ficha2!=null) && !(Math.abs(posFinalX-posIniX)==Math.abs((posIniY-posFinalY)) && Math.abs(posFinalX-posIniX)==1)){
            return false;
        }

        if((ficha1!=null && ficha2!=null) && (ficha1.getEquipo()!=ficha2.getEquipo())){
            return setFichaPorPos(posIniX, posIniY, idTablero, posFinalX, posFinalY, true);
        }
        if((ficha1!=null && ficha2==null)){
            return setFichaPorPos(posIniX, posIniY, idTablero, posFinalX, posFinalY, false);
        }
        return false;
    }
    //Set ficha (moverla) (o eliminarla en caso de que se la pelen)
    public boolean setFichaPorPos(int posIniX, int posIniY, int idTablero, int posFinalX, int posFinalY, boolean eliminar){
        Optional<Tablero> miTablero = getById(idTablero);
        Tablero miTableroObjeto = miTablero.get();
        //Save hace update si le mando un objeto con ya predefined id
        List<TestFicha> fichas = miTableroObjeto.getFichas();
        TestFicha ficha1 = getFichaPorPos(posIniX, posIniY, idTablero);
        if(eliminar && ficha1!=null){
            int fichaIndex = fichas.indexOf(ficha1);

            TestFicha fichaPorEliminar = getFichaPorPos(posFinalX, posFinalY, idTablero);

            ficha1.setPosY(posFinalY);
            ficha1.setPosX(posFinalX);

            fichas.set(fichaIndex, ficha1);

            fichas.remove(fichaPorEliminar);

            miTableroObjeto.setFichas(fichas);
            tableroRepository.save(miTableroObjeto);
            return true;
        }
        if(!eliminar && ficha1!=null){
            int fichaIndex = fichas.indexOf(ficha1);
            ficha1.setPosY(posFinalY);
            ficha1.setPosX(posFinalX);
            fichas.set(fichaIndex, ficha1);
            miTableroObjeto.setFichas(fichas);
            tableroRepository.save(miTableroObjeto);
            return true;
        }
        return false;
    }



    //Este metodo se usa en tableroController para guardar un tablero nuevo!!!
    public Tablero armarTablero(){
        Tablero miTablero =  new Tablero();
        List<TestFicha> fichas = new ArrayList<>();
        TestFicha ficha = new TestFicha();

        //Poner peones blancos
        for(int i=0;i<8;i++){
            ficha = new TestFicha(i+1, 2, TipoFicha.PEON, EquipoFicha.BLANCO);
            fichas.add(ficha);
        }
        //Poner peones negros
        for(int i=0;i<8;i++){
            ficha = new TestFicha(i+1, 7, TipoFicha.PEON, EquipoFicha.NEGRO);
            fichas.add(ficha);
        }
        //Poner reyes B&W
        ficha = new TestFicha(5, 1, TipoFicha.REY, EquipoFicha.BLANCO);
        fichas.add(ficha);
        ficha = new TestFicha(5, 8, TipoFicha.REY, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Poner Reinas B&W
        ficha = new TestFicha(4, 1, TipoFicha.REINA, EquipoFicha.BLANCO);
        fichas.add(ficha);
        ficha = new TestFicha(4, 8, TipoFicha.REINA, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Poner Torres B&W
        ficha = new TestFicha(1, 1, TipoFicha.TORRE, EquipoFicha.BLANCO);
        fichas.add(ficha);
        ficha = new TestFicha(8, 1, TipoFicha.TORRE, EquipoFicha.BLANCO);
        fichas.add(ficha);

        ficha = new TestFicha(1, 8, TipoFicha.TORRE, EquipoFicha.NEGRO);
        fichas.add(ficha);
        ficha = new TestFicha(8, 8, TipoFicha.TORRE, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Poner Caballos B&W
        ficha = new TestFicha(2, 1, TipoFicha.CABALLO, EquipoFicha.BLANCO);
        fichas.add(ficha);
        ficha = new TestFicha(7, 1, TipoFicha.CABALLO, EquipoFicha.BLANCO);
        fichas.add(ficha);

        ficha = new TestFicha(2, 8, TipoFicha.CABALLO, EquipoFicha.NEGRO); //era 2, 8
        fichas.add(ficha);
        ficha = new TestFicha(7, 8, TipoFicha.CABALLO, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Poner Alfiles B&W
        ficha = new TestFicha(3, 1, TipoFicha.ALFIL, EquipoFicha.BLANCO);
        fichas.add(ficha);
        ficha = new TestFicha(6, 1, TipoFicha.ALFIL, EquipoFicha.BLANCO);
        fichas.add(ficha);
        ficha = new TestFicha(3, 8, TipoFicha.ALFIL, EquipoFicha.NEGRO);
        fichas.add(ficha);
        ficha = new TestFicha(6, 8, TipoFicha.ALFIL, EquipoFicha.NEGRO);
        fichas.add(ficha);


        miTablero.setFichas(fichas);

        //Se guarda el tablero en la base de datos
        return tableroRepository.save(miTablero);
    }
}
