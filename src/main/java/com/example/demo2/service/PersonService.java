package com.example.demo2.service;

import com.example.demo2.model.Person;
import com.example.demo2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(Person p){
        return personRepository.save(p);
    }

    public List<Person> findAll(){
        return personRepository.getAll();
    }


}
