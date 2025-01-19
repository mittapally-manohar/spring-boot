package com.manohar.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {
    private SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll() {
        return schoolRepository.findAll()
                .stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }

    private SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }


    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto schoolDto
    ){
        var school = toSchool(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    private School toSchool(SchoolDto schoolDto) {
        return new School(schoolDto.name());
    }
}
