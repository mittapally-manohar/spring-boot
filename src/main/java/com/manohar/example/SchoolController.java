package com.manohar.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAllSchools() {
        return schoolService.findAllSchools();
    }

    @PostMapping("/schools")
    public SchoolDto saveSchool(
            @RequestBody SchoolDto schoolDto
    ){
        return schoolService.saveSchool(schoolDto);
    }
}
