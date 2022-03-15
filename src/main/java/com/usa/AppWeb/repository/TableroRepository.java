package com.usa.AppWeb.repository;

import com.usa.AppWeb.model.Tablero;
import com.usa.AppWeb.repository.crud.TableroCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TableroRepository {
    @Autowired
    private TableroCRUDRepository tableroCRUDRepository;

    public List<Tablero> getAll(){
        return (List<Tablero>) tableroCRUDRepository.findAll();
    }

    public Optional<Tablero> getById(int id){
        return tableroCRUDRepository.findById(id);
    }

    public Tablero save(Tablero tablero) {
        return tableroCRUDRepository.save(tablero);
    }

    public void delete(Tablero tablero) {
        tableroCRUDRepository.delete(tablero);
    }
}
