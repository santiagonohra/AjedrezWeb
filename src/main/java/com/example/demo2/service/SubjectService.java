package com.example.demo2.service;

import com.example.demo2.model.Person;
import com.example.demo2.model.Subject;
import com.example.demo2.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject save(Subject p){
        return subjectRepository.save(p);
    }

    public List<Subject> findAll(){
        return subjectRepository.getAll();
    }


}
