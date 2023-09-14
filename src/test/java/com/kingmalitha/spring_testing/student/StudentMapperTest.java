package com.kingmalitha.spring_testing.student;

import com.kingmalitha.spring_testing.school.School;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void ShouldMapStudentDtoToStudent(){
        StudentDto dto = new StudentDto(
                "Malitha",
                "Sandaruwan",
                "malitha@gmail.com",
                15,
                1
        );

        Student student = studentMapper.toStudent(dto);

        assertEquals(dto.firstname(),student.getFirstname());
        assertEquals(dto.lastname(),student.getLastname());
        assertEquals(dto.email(),student.getEmail());
        assertEquals(dto.age(),student.getAge());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(),student.getSchool().getId());
    }

    @Test
    public void ShouldMapStudentToStudentResponseDto() {
//    Given
        Student student = new Student(
                "Malitha",
                "Sandaruwan",
                "malitha@gmail.com",
                15
        );

//    When
        StudentResponseDto response =
                studentMapper.toStudentResponseDto(student);

//    Then
        assertEquals(student.getFirstname(),response.firstname());
        assertEquals(student.getLastname(),response.lastname());
        assertEquals(student.getEmail(),response.email());

    }

    @Test
    public void ShouldThrowNullPointerExceptionWhenStudentDtoIsNull(){
       var exp= assertThrows(
                NullPointerException.class,
                () -> studentMapper.toStudent(null)
        );
       assertEquals("StudentDto should not be null",exp.getMessage());
    }
}