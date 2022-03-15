package com.example.demo2.repository;


import com.example.demo2.model.Person;
import com.example.demo2.repository.crud.PersonCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    @Autowired
    private PersonCRUDRepository personCRUDRepository;

    public List<Person> getAll(){
        return (List<Person>) personCRUDRepository.findAll();
    }

    public Optional<Person> getById(int id){
        return personCRUDRepository.findById(id);
    }

    public Person save(Person p){
        return personCRUDRepository.save(p);
    }

    public void delete(Person p){
         personCRUDRepository.delete(p);
    }

}
