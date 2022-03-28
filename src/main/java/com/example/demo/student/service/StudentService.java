package com.example.demo.student.service;

import com.example.demo.student.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(1L, "Osama",  "osama@yahoo.com", LocalDate.of(1995, 12, 10), 25),
                new Student(2L, "Shazeen",  "shzeen@yahoo.com", LocalDate.of(1996, 06, 13), 24)
        );
    }
}
