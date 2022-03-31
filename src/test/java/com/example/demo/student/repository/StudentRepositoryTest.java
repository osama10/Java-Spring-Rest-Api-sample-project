package com.example.demo.student.repository;

import com.example.demo.student.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @Autowired
    private StudentRepository sut;

    @Test
    void itShouldFindStudentByEmail() {
        // given
        String email = "osama@yahoo.com";
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));

        // when
        Optional<Student> optionalStudent = sut.findStudentByEmail(email);

        // then
        assertTrue(optionalStudent.isPresent());
    }
}