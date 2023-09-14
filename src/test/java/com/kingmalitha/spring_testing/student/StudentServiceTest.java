package com.kingmalitha.spring_testing.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void ShouldSuccessFullyCreateStudent() {
        // Given

        StudentDto dto = new StudentDto(
                "Malitha",
                "Sandaruwan",
                "malitha@gmail.com",
                15,
                1
        );

        Student student = new Student(
                "Malitha",
                "Sandaruwan",
                "malitha@gmail.com",
                15
        );

        Student savedStudent = new Student(
                "Malitha",
                "Sandaruwan",
                "malitha@gmail.com",
                15
        );

        savedStudent.setId(1);

//        Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);

        when(studentRepository.save(student)).thenReturn(savedStudent);

        when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(
                new StudentResponseDto(
                        "Malitha",
                        "Sandaruwan",
                        "malitha@gmail.com"
                )
        );

        // When

        StudentResponseDto response = studentService.createStudent(dto);


        //Then
        assertEquals(dto.firstname(), response.firstname());
        assertEquals(dto.lastname(), response.lastname());
        assertEquals(dto.email(), response.email());

        verify(studentMapper,times(1))
                .toStudent(dto);
        verify(studentRepository,times(1))
                .save(student);
        verify(studentMapper,times(1))
                .toStudentResponseDto(savedStudent);


    }

    @Test
    public void ShouldReturnAllStudents(){

//        Given
        List<Student> students = new ArrayList<>();
        students.add(
                new Student(
                        "Malitha",
                        "Sandaruwan",
                        "malitha@gmail.com",
                        15
                )
        );

//        mocks the calls
        when(studentRepository.findAll()).thenReturn(students);

        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(
                new StudentResponseDto(
                        "Malitha",
                        "Sandaruwan",
                        "malitha@gmail.com"
                ));

//        When
        List<StudentResponseDto> response = studentService.getAllStudents();

//        Then
        assertEquals(students.size(),response.size());
        verify(studentRepository,times(1))
                .findAll();

    }
}