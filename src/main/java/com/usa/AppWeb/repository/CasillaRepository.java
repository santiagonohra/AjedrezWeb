package com.usa.AppWeb.repository;

import com.usa.AppWeb.model.Casilla;
import com.usa.AppWeb.model.Usuario;
import com.usa.AppWeb.repository.crud.CasillaCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CasillaRepository {
    @Autowired
    private CasillaCRUDRepository casillaCRUDRepository;

    public List<Casilla> getAll(){
        return (List<Casilla>) casillaCRUDRepository.findAll();
    }

    public Optional<Casilla> getById(int id){
        return casillaCRUDRepository.findById(id);
    }

    public Casilla save(Casilla casilla) {
        return casillaCRUDRepository.save(casilla);
    }

    public void delete(Casilla casilla) {
        casillaCRUDRepository.delete(casilla);
    }
}
