package com.kingmalitha.spring_testing.student;

import com.kingmalitha.spring_testing.school.School;
import com.kingmalitha.spring_testing.school.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstnameContaining(String firstname);
//    You need specify the property name in the entity class, in this case it
//    is firstname -> FindAllByFirstname

    @RestController
    @RequiredArgsConstructor
    class SchoolController {

        private final SchoolRepository schoolRepository;



        @PostMapping("/schools")
        public School createSchool(School school) {
            return schoolRepository.save(school);
        }




    }
}
