package com.usa.AppWeb.service;

import com.usa.AppWeb.model.Casilla;
import com.usa.AppWeb.model.Ficha;
import com.usa.AppWeb.repository.CasillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasillaService {
    @Autowired
    private CasillaRepository casillaRepository;

    public Casilla save(Casilla casilla) {
        return casillaRepository.save(casilla);
    }

    public List<Casilla> findAll(){
        return (List<Casilla>) casillaRepository.getAll();
    }

    public boolean tieneFicha(Casilla casilla){
        return(casilla.getFicha()!=null);
    }

    public boolean mataFicha(Casilla casilla, Ficha ficha){
        if(tieneFicha(casilla)){
            if(casilla.getFicha().getEquipo()!=ficha.getEquipo()) {
                ficha.getCasilla().setFicha(null);
                ficha.setCasilla(casilla);
                casilla.setFicha(ficha);
                return true;
            }
        }
        return false;
    }


}
