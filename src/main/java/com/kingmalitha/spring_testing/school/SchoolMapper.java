package com.kingmalitha.spring_testing.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public School toSchool(SchoolDto dto) {
        School school = new School();
        school.setName(dto.name());
        return school;
    }

    public SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }
}
