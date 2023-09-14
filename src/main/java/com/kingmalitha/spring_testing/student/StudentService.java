package com.kingmalitha.spring_testing.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponseDto createStudent(StudentDto studentDto) {
        Student student = studentMapper.toStudent(studentDto);
        return studentMapper.toStudentResponseDto(studentRepository.save(student));
    }

    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto getStudentById(Integer studentId) {
        return studentMapper.toStudentResponseDto(
                studentRepository.findById(studentId)
                        .orElse(null));
    }

    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<StudentResponseDto> findStudentByFirstname(String firstname) {
        List<Student> students = studentRepository.findAllByFirstnameContaining(firstname);
        return students.stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }






}
