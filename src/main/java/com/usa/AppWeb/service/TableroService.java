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

    //Retornar un objeto tipo casilla para despues saber si esta vacia o no
    public TestCasilla getCasillaPorPos(int posX, int posY, int idTablero){
        //Get casilla
        Optional<Tablero> miTablero = getById(idTablero);
        Tablero miTableroObjeto = miTablero.get();
        List<TestCasilla> casillas = miTableroObjeto.getCasillas();
        for(TestCasilla casilla : casillas){
            if(casilla.equalCoords(posX, posY)){
                return casilla;
            }
        }
        return null;

    }

    public void armarTablero(){
        Tablero miTablero =  new Tablero();
        List<TestCasilla> casillas = new ArrayList<>();
        TestCasilla casilla = new TestCasilla();
        casilla.setPosX(0);
        casilla.setPosY(1);
        casilla.setOccupied(true);
        casillas.add(casilla);

        List<TestFicha> fichas = new ArrayList<>();
        TestFicha ficha = new TestFicha();
        ficha.setEquipo(EquipoFicha.BLANCO);
        ficha.setTipo(TipoFicha.PEON);
        ficha.setPosX(0);
        ficha.setPosY(1);
        fichas.add(ficha);

        miTablero.setCasillas(casillas);
        miTablero.setFichas(fichas);

        tableroRepository.save(miTablero);
    }
}
