package com.usa.AppWeb.service;

import com.usa.AppWeb.model.*;
import com.usa.AppWeb.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PartidaService {
    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private TableroService tableroService;
    @Autowired
    private FichaService fichaService;

    public Partida save(Partida partida) {
        return partidaRepository.save(partida);
    }

    public boolean delete(Partida partida){
        List<Partida> allMatches = findAll();
        for(Partida p : allMatches){
            if(p.equals(partida)){
                partidaRepository.delete(p);
                return true;
            }
        }
        return false;
    }

    public Optional<Partida> findById(int id){
        return partidaRepository.getById(id);
    }

    public List<Partida> findAll(){
        return (List<Partida>) partidaRepository.getAll();
    }

    public Partida crearPartida(String userName1){
        Partida partida = new Partida();
        partida.setTablero(tableroService.armarTablero());
        partida.setUserName1(userName1);
        partida.setEquipoJugador1(EquipoFicha.BLANCO);
        partida.setTurno(EquipoFicha.BLANCO);
        partidaRepository.save(partida);
        return partida;
    }

    public boolean unirsePartida(String userName2, int idPartida){
        Optional<Partida> partidaList = partidaRepository.getById(idPartida);
        Partida partida = partidaList.get();
        partida.setUserName2(userName2);
        partida.setEquipoJugador2(EquipoFicha.NEGRO);
        if(partida!=null){
            partidaRepository.save(partida);
            return true;
        }
        return false;
    }

    public Partida cargarPartida(int id){
        Optional<Partida> partidaCargada = partidaRepository.getById(id);
        return partidaCargada.get();
    }

    public boolean moverFicha(int posIniX, int posIniY, int idTablero, int posFinalX, int posFinalY){
        Optional<Partida> partida = findById(idTablero);
        Partida miPartidaObjeto = partida.get();
        System.out.println("Dentro del partida  service moverficha");
        if(miPartidaObjeto.getUserName2()==null){
            System.out.println("Dentro de user2 nulo");
            return false;
        }
/*
        if(estaEnJaque(miPartidaObjeto.getTurno(), idTablero)){
            TestFicha ficha = tableroService.getFichaPorPos(posIniX, posIniY, idTablero);
            if(fichaService.esMovValido(posFinalX, posFinalY, posIniX, posIniY, idTablero) && ficha.getTipo()==TipoFicha.REY){
                miPartidaObjeto.setTurno(EquipoFicha.NEGRO);
                partidaRepository.save(miPartidaObjeto);
                return true;
            }
            return false;
        }*/
        if(fichaService.esMovValido(posFinalX, posFinalY, posIniX, posIniY, idTablero)){
            System.out.println("Dentro del ficha service if");
            if(tableroService.moverFicha(posIniX, posIniY, idTablero, posFinalX, posFinalY, miPartidaObjeto.getTurno())){
                System.out.println("Dentro del tablero service if");
                if(miPartidaObjeto!=null && miPartidaObjeto.getTurno()==EquipoFicha.BLANCO){
                    miPartidaObjeto.setTurno(EquipoFicha.NEGRO);
                    partidaRepository.save(miPartidaObjeto);
                    return true;
                }else if(miPartidaObjeto!=null && miPartidaObjeto.getTurno()==EquipoFicha.NEGRO){
                    miPartidaObjeto.setTurno(EquipoFicha.BLANCO);
                    partidaRepository.save(miPartidaObjeto);
                    return true;
                }
            }
        }
        System.out.println("Terminando el partidaservice moverficha y about to return false");
        return false;
    }

    public List<TestFicha> getFichasJaque(EquipoFicha equipo, int idTablero){
        //Todas las fichas del adversario que pongan en jaque a mi rey
        Optional<Tablero> miTablero = tableroService.getById(idTablero);
        Tablero miTableroObjeto = miTablero.get();
        List<TestFicha> fichas = miTableroObjeto.getFichas();
        List<TestFicha> fichasJaque = new ArrayList<TestFicha>();
        EquipoFicha equipoOpuesto = null;
        TestFicha rey = tableroService.getRey(equipo, idTablero);
        for(TestFicha ficha : fichas){
            if(ficha.getEquipo()!=equipo){
                if(fichaService.esMovValido(rey.getPosX(), rey.getPosY(), ficha.getPosX(), ficha.getPosY(), idTablero)){
                    fichasJaque.add(ficha);
                }
            }
        }
        return fichasJaque;
    }

    public boolean estaEnJaque(EquipoFicha equipo, int idTablero){
        return(getFichasJaque(equipo, idTablero).size()>0);
    }
}
