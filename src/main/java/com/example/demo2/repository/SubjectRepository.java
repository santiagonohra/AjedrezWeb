package com.example.demo2.repository;


import com.example.demo2.model.Person;
import com.example.demo2.model.Subject;
import com.example.demo2.repository.crud.SubjectCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubjectRepository {

    @Autowired
    private SubjectCRUDRepository subjectCRUDRepository;

    public List<Subject> getAll(){
        return (List<Subject>) subjectCRUDRepository.findAll();
    }

    public Optional<Subject> getById(int id){
        return subjectCRUDRepository.findById(id);
    }

    public Subject save(Subject p){
        return subjectCRUDRepository.save(p);
    }

    public void delete(Subject p){
         subjectCRUDRepository.delete(p);
    }

}
