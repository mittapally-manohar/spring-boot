package com.manohar.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyFirstController {
    private final StudentRepository studentRepository;

    public MyFirstController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    public Student saveStudent(
            @RequestBody Student student
    ) {
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{student-id}")
    public Student getStudentByID(
            @PathVariable("student-id") Integer id
    ) {
        return studentRepository.findById(id).orElse(new Student());
    }

    @GetMapping("/students/search/{name}")
    public List<Student> getStudentByName(
            @PathVariable("name") String name
    ) {
        return studentRepository.findAllByFirstNameContaining(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudentByID(
            @PathVariable("student-id") Integer id
    ) {
        studentRepository.deleteById(id);
    }
}
