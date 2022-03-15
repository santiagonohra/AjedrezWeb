package com.example.demo2.repository.crud;

import com.example.demo2.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonCRUDRepository extends CrudRepository<Person,Integer> {

}
