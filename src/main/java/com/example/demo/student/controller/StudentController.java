package com.example.demo.student.controller;

import com.example.demo.student.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    @GetMapping
    public List<Student> getStudents() {
        return List.of(
                new Student(1L, "Osama",  "osama@yahoo.com", LocalDate.of(1995, 12, 10), 25),
                new Student(2L, "Shazeen",  "shzeen@yahoo.com", LocalDate.of(1996, 06, 13), 24)
        );
    }
}
