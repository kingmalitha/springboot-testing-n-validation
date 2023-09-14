package com.kingmalitha.spring_testing.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;


    @PostMapping
    public StudentResponseDto createStudent(
           @Valid @RequestBody StudentDto dto
    ) {
        return studentService.createStudent(dto);
    }

    @GetMapping
    public List<StudentResponseDto> findAllStudents() {

        return studentService.getAllStudents();
    }


    @GetMapping("{studentId}")
    public StudentResponseDto findStudentById(
            @PathVariable("studentId") Integer studentId
    ){
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/search/{studentName}")
    public List<StudentResponseDto> findStudentById(
            @PathVariable("studentName") String name
    ){
       return studentService.findStudentByFirstname(name);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudentById(
            @PathVariable("studentId") Integer studentId
    ){
        studentService.deleteStudentById(studentId);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ){
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }






}
