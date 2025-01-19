package com.manohar.example.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;


    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto studentDto) {
        var student = studentMapper.toStudent(studentDto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .toList();
    }

    public StudentResponseDto getStudentByID(Integer id) {
        var student = studentRepository.findById(id).orElse(new Student());
        return studentMapper.toStudentResponseDto(student);
    }

    public List<StudentResponseDto> getStudentByName(String name) {
        return studentRepository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .toList();

    }

    public void deleteStudentByID(Integer id) {
        studentRepository.deleteById(id);
    }
}
