package com.kingmalitha.spring_testing.school;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolDto createSchool(SchoolDto dto) {

        System.out.println("Name = " + dto.name());
        School school = schoolMapper.toSchool(dto);
        System.out.println("school = " + school);
        School savedSchool = schoolRepository.save(school);
        System.out.println("savedSchool = " + savedSchool);
        return schoolMapper.toSchoolDto(savedSchool);
    }

    public SchoolDto getSchoolById(Integer id) {
        return schoolMapper.toSchoolDto(schoolRepository.findById(id).orElseThrow());
    }

    public List<SchoolDto> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
