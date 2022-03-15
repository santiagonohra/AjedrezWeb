package com.usa.AppWeb.service;

import com.usa.AppWeb.model.Tablero;
import com.usa.AppWeb.model.Usuario;
import com.usa.AppWeb.repository.TableroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableroService {
    @Autowired
    private TableroRepository tableroRepository;

    public Tablero armarTablero(){
        Tablero miTablero = new Tablero();
        return miTablero;
    }

    public Tablero save(Tablero tablero) {
        return tableroRepository.save(tablero);
    }

    public List<Tablero> findAll(){
        return (List<Tablero>) tableroRepository.getAll();
    }
}
