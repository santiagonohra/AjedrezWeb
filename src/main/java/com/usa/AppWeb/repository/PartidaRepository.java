package com.usa.AppWeb.repository;

import com.usa.AppWeb.model.Casilla;
import com.usa.AppWeb.model.Partida;
import com.usa.AppWeb.repository.crud.PartidaCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PartidaRepository {
    @Autowired
    private PartidaCRUDRepository partidaCRUDRepository;

    public List<Partida> getAll(){
        return (List<Partida>) partidaCRUDRepository.findAll();
    }

    public Optional<Partida> getById(int id){
        return partidaCRUDRepository.findById(id);
    }

    public Partida save(Partida partida) {
        return partidaCRUDRepository.save(partida);
    }

    public void delete(Partida partida) {
        partidaCRUDRepository.delete(partida);
    }
}
