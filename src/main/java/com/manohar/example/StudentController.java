package com.manohar.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @RequestBody StudentDto studentDto
    ) {
        var student = toStudent(studentDto);
        var savedStudent = studentRepository.save(student);
        return toStudentResponseDto(savedStudent);
    }

    private StudentResponseDto toStudentResponseDto(Student savedStudent) {
       return new StudentResponseDto(savedStudent.getFirstName(), savedStudent.getLastName());
    }

    private Student toStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());

        School school = new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);
        return student;
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
