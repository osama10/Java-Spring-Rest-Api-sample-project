package com.example.demo.student.repository;

import com.example.demo.student.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository sut;

    @AfterEach
    void tearDown() {
        sut.deleteAll();
    }
    
    @Test
    void test_whenCorrectEmailIsPassedToRepo_thenItShowsStudentExist() {
        // given
        String email = "osama@yahoo.com";
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));

        sut.save(student);

        // when
        Optional<Student> optionalStudent = sut.findStudentByEmail(email);

        // then
        assertTrue(optionalStudent.isPresent());
    }

    @Test
    void test_whenCorrectEmailIsPassedToRepo_thenItShowsStudentDoesnotExist() {
        // given
        String email = "osama1@yahoo.com";
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));

        sut.save(student);

        // when
        Optional<Student> optionalStudent = sut.findStudentByEmail(email);

        // then
        assertFalse(optionalStudent.isPresent());
    }
}