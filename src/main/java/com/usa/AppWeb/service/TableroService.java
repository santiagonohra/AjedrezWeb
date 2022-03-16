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

    //Este metodo se usa en tableroController para guardar un tablero nuevo!!!
    public Tablero armarTablero(){
        Tablero miTablero =  new Tablero();
        List<TestCasilla> casillas = new ArrayList<>();
        List<TestFicha> fichas = new ArrayList<>();
        TestFicha ficha = new TestFicha();
        TestCasilla casilla = new TestCasilla();

        //Poner peones blancos
        for(int i=0;i<8;i++){
            casilla = new TestCasilla(i+1, 2, true);
            casillas.add(casilla);
            ficha = new TestFicha(i+1, 2, TipoFicha.PEON, EquipoFicha.BLANCO);
            fichas.add(ficha);
        }
        //Poner peones negros
        for(int i=0;i<8;i++){
            casilla = new TestCasilla(i+1, 7, true);
            casillas.add(casilla);
            ficha = new TestFicha(i+1, 7, TipoFicha.PEON, EquipoFicha.NEGRO);
            fichas.add(ficha);
        }
        //Poner reyes B&W
        casilla = new TestCasilla(5, 1, true);
        casillas.add(casilla);
        ficha = new TestFicha(5, 1, TipoFicha.REY, EquipoFicha.BLANCO);
        fichas.add(ficha);

        casilla = new TestCasilla(5, 8, true);
        casillas.add(casilla);
        ficha = new TestFicha(5, 8, TipoFicha.REY, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Poner Reinas B&W
        casilla = new TestCasilla(4, 1, true);
        casillas.add(casilla);
        ficha = new TestFicha(4, 1, TipoFicha.REINA, EquipoFicha.BLANCO);
        fichas.add(ficha);

        casilla = new TestCasilla(4, 8, true);
        casillas.add(casilla);
        ficha = new TestFicha(4, 8, TipoFicha.REINA, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Poner Torres B&W
        casilla = new TestCasilla(1, 1, true);
        casillas.add(casilla);
        ficha = new TestFicha(1, 1, TipoFicha.TORRE, EquipoFicha.BLANCO);
        fichas.add(ficha);
        casilla = new TestCasilla(8, 1, true);
        casillas.add(casilla);
        ficha = new TestFicha(8, 1, TipoFicha.TORRE, EquipoFicha.BLANCO);
        fichas.add(ficha);

        casilla = new TestCasilla(1, 8, true);
        casillas.add(casilla);
        ficha = new TestFicha(1, 8, TipoFicha.TORRE, EquipoFicha.NEGRO);
        fichas.add(ficha);
        casilla = new TestCasilla(8, 8, true);
        casillas.add(casilla);
        ficha = new TestFicha(8, 8, TipoFicha.TORRE, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Poner Caballos B&W
        casilla = new TestCasilla(2, 1, true);
        casillas.add(casilla);
        ficha = new TestFicha(2, 1, TipoFicha.CABALLO, EquipoFicha.BLANCO);
        fichas.add(ficha);
        casilla = new TestCasilla(7, 1, true);
        casillas.add(casilla);
        ficha = new TestFicha(7, 1, TipoFicha.CABALLO, EquipoFicha.BLANCO);
        fichas.add(ficha);

        casilla = new TestCasilla(2, 8, true);
        casillas.add(casilla);
        ficha = new TestFicha(2, 8, TipoFicha.CABALLO, EquipoFicha.NEGRO);
        fichas.add(ficha);
        casilla = new TestCasilla(7, 8, true);
        casillas.add(casilla);
        ficha = new TestFicha(7, 8, TipoFicha.CABALLO, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Poner Alfiles B&W
        casilla = new TestCasilla(3, 1, true);
        casillas.add(casilla);
        ficha = new TestFicha(3, 1, TipoFicha.ALFIL, EquipoFicha.BLANCO);
        fichas.add(ficha);
        casilla = new TestCasilla(6, 1, true);
        casillas.add(casilla);
        ficha = new TestFicha(6, 1, TipoFicha.ALFIL, EquipoFicha.BLANCO);
        fichas.add(ficha);

        casilla = new TestCasilla(3, 8, true);
        casillas.add(casilla);
        ficha = new TestFicha(3, 8, TipoFicha.ALFIL, EquipoFicha.NEGRO);
        fichas.add(ficha);
        casilla = new TestCasilla(6, 8, true);
        casillas.add(casilla);
        ficha = new TestFicha(6, 8, TipoFicha.ALFIL, EquipoFicha.NEGRO);
        fichas.add(ficha);

        //Se ponen las casillas vacias (que no tienen fichas al iniciar la partida) (son 8x4)
        for(int i=0;i<8;i++){
            //i = eje X (horizontal), j = eje y (vertical)
            for(int j=3;j<=6;j++){
                casilla = new TestCasilla(i+1, j, false);
                casillas.add(casilla);
            }
        }

        //Se guarda la lista de casillas y la lista de fichas en miTablero
        miTablero.setCasillas(casillas);
        miTablero.setFichas(fichas);

        //Se guarda el tablero en la base de datos
        return tableroRepository.save(miTablero);
    }
}
