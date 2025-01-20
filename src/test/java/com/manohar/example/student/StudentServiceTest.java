package com.manohar.example.student;

import static org.junit.jupiter.api.Assertions.*;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class StudentServiceTest {

    //which class to test
    @InjectMocks
    private StudentService studentService;

    //any dependencies
    @Mock
    StudentRepository studentRepository;

    @Mock
    StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveStudent() {
        //Given
        StudentDto studentDto = new StudentDto("Manohar",
                "Mittapally",
                "m@gmail.com",
                1);

        Student student = new Student(1,
                "Manohar",
                "Mittapally",
                "m@gmail.com",13);

       StudentResponseDto studentResponseDto = new StudentResponseDto("Manohar", "Mittapally");

        //Mock the calls
        when(studentMapper.toStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toStudentResponseDto(student)).thenReturn(studentResponseDto);

        //When
        StudentResponseDto responseDto = studentService.saveStudent(studentDto);

        //Then
        assertEquals(responseDto.lastName(), studentResponseDto.lastName());

        //Verify
        verify(studentMapper,times(1)).toStudent(studentDto);
        verify(studentMapper,times(1)).toStudentResponseDto(student);
        verify(studentRepository,times(1)).save(student);

    }

    @Test
    public void should_return_all_student_response_dtos(){
        //given
        List<Student> dtos = new ArrayList<>();
        dtos.add( new Student(1,
                "Manohar",
                "Mittapally",
                "m@gmail.com",13));

        //mock the calls
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Manohar", "Mittapally"));
        when(studentRepository.findAll())
                .thenReturn(dtos);

        //when
        List<StudentResponseDto> responseDtos = studentService.getAllStudents();

        //then
        assertEquals(dtos.size(), responseDtos.size());

        //verify
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void should_return_student_response_dto_by_id(){
        //given
        StudentResponseDto studentResponseDto = new StudentResponseDto("Manohar", "Mittapally");
        Student student = new Student(1,
                "Manohar",
                "Mittapally",
                "m@gmail.com",13);

        //mock the calls
        when(studentRepository.findById(1))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any()))
                .thenReturn(studentResponseDto);

        //when
        StudentResponseDto responseDto = studentService.getStudentByID(1);

        //then
        assertEquals(studentResponseDto.lastName(), responseDto.lastName());

        //verify
        verify(studentRepository,times(1)).findById(1);
    }

    @Test
    public void should_return_student_response_dto_by_name() {
        //given
        String name = "manohar";
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,
                "Manohar",
                "Mittapally",
                "m@gmail.com",13));

        //mock the calls
        when(studentRepository.findAllByFirstNameContaining(name))
                .thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("manohar","mittapally"));

        //when
       List<StudentResponseDto> studentsResponse = studentService.getStudentByName(name);

       //then
        assertEquals(studentsResponse.size(), students.size());

        //verify
        verify(studentRepository,times(1)).findAllByFirstNameContaining(name);
    }

    @Test
    public void should_delete_student_by_id() {
        //Given
        Integer id = 1;


        // When
        studentService.deleteStudentByID(id);

        // Then
        verify(studentRepository, times(1)).deleteById(id);
    }
}