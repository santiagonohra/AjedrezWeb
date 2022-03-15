package com.example.demo2.repository.crud;

import com.example.demo2.model.Person;
import com.example.demo2.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectCRUDRepository extends CrudRepository<Subject,Integer> {

}
