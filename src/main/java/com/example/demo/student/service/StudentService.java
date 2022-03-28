package com.example.demo.student.service;

import com.example.demo.student.model.Student;
import com.example.demo.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    /*
    List.of(
                new Student(1L, "Osama",  "osama@yahoo.com", LocalDate.of(1995, 12, 10), 25),
                new Student(2L, "Shazeen",  "shzeen@yahoo.com", LocalDate.of(1996, 06, 13), 24)
        );
     */
}
