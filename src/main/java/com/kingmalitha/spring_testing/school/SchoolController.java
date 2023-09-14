package com.kingmalitha.spring_testing.school;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    public SchoolDto createSchool(@RequestBody SchoolDto dto) {
        return schoolService.createSchool(dto);
    }


    @GetMapping
    public List<SchoolDto> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("{id}")
    public SchoolDto getSchoolById( @PathVariable Integer id) {
        return schoolService.getSchoolById(id);
    }

}
