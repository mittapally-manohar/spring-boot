package com.manohar.example.student;

import com.manohar.example.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public StudentResponseDto toStudentResponseDto(Student savedStudent) {
        return new StudentResponseDto(savedStudent.getFirstName(), savedStudent.getLastName());
    }

    public Student toStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());

        School school = new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);
        return student;
    }
}
