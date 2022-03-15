package com.example.demo2.controller;

import com.example.demo2.model.Person;
import com.example.demo2.model.Subject;
import com.example.demo2.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/all")
    public List<Subject> getPeople(){
        return subjectService.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Subject save(@RequestBody Subject p) {
        return subjectService.save(p);
    }

}
