package com.manohar.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @RequestBody StudentDto studentDto
    ) {
        return studentService.saveStudent(studentDto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto getStudentByID(
            @PathVariable("student-id") Integer id
    ) {
        return studentService.getStudentByID(id);
    }

    @GetMapping("/students/search/{name}")
    public List<StudentResponseDto> getStudentByName(
            @PathVariable("name") String name
    ) {
        return studentService.getStudentByName(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudentByID(
            @PathVariable("student-id") Integer id
    ) {
        studentService.deleteStudentByID(id);
    }
}
