package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;
import com.example.demo.dao.StudentRepo;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    StudentRepo repo;

    @RequestMapping("/")
    public String home()
    {
        return "home.html";
    }

    @PostMapping(path="/student", consumes = {"application/json"})
    public Student addStudent(@RequestBody Student student)
    {
        repo.save(student);
        return student;
    }

    @PutMapping(path="/student", consumes = {"application/json"})
    public Student saveOrUpdateStudent(@RequestBody Student student)
    {
        repo.save(student);
        return student;
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable int id)
    {
        Student s = repo.getById(id);
        repo.delete(s);

        return "student deleted";
    }

    @GetMapping("/students")
    public List<Student> getStudents()
    {
       return repo.findAll();
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudent(@PathVariable("id") int id)
    {
        return repo.findById(id);
    }

}
