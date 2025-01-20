package com.manohar.example.student;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto("Manohar",
                "Mittapally",
                "mm@gmail.com",
                1);

        Student student = mapper.toStudent(dto);

        assertEquals(student.getFirstName(), dto.firstName());
        assertEquals(student.getLastName(), dto.lastName());
        assertEquals(student.getEmail(), dto.email());
        assertNotNull(student.getSchool());
        assertEquals(student.getSchool().getId(), dto.schoolId());

    }

    @Test
    public void should_throw_null_pointer_exception_when_student_dto_is_null() {
        var exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("StudentDto should not be null", exp.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student();
        student.setFirstName("Manohar");
        student.setLastName("Mittapally");

        StudentResponseDto studentResponseDto = mapper.toStudentResponseDto(student);

        assertEquals(studentResponseDto.firstName(), student.getFirstName());
        assertEquals(studentResponseDto.lastName(), student.getLastName());
    }

}