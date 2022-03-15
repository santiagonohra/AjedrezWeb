package com.usa.AppWeb.repository;

import com.usa.AppWeb.model.Ficha;
import com.usa.AppWeb.repository.crud.FichaCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FichaRepository {
    @Autowired
    private FichaCRUDRepository fichaCRUDRepository;

    public List<Ficha> getAll(){
        return (List<Ficha>) fichaCRUDRepository.findAll();
    }

    public Optional<Ficha> getById(int id){
        return fichaCRUDRepository.findById(id);
    }

    public Ficha save(Ficha ficha) {
        return fichaCRUDRepository.save(ficha);
    }

    public void delete(Ficha ficha) {
        fichaCRUDRepository.delete(ficha);
    }
}
