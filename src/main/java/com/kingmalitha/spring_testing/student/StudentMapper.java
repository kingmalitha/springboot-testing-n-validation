package com.kingmalitha.spring_testing.student;

import com.kingmalitha.spring_testing.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto studentDto) {
        if(studentDto == null){
            throw new NullPointerException("StudentDto should not be null");
        }
        Student student = new Student();
        student.setFirstname(studentDto.firstname());
        student.setLastname(studentDto.lastname());
        student.setEmail(studentDto.email());
        student.setAge(studentDto.age());

        var school = new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);
        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }

}
