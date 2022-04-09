package com.example.demo.student.repository;

import com.example.demo.student.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

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
        Boolean studentExists = sut.existsStudentByEmail(student.getEmail());

        // then
        assertThat(studentExists)
                .isTrue();

    }

    @Test
    void test_whenInCorrectEmailIsPassedToRepo_thenItShowsStudentDoesnotExist() {
        // given
        String email = "osama1@yahoo.com";
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));

        sut.save(student);

        // when
        Boolean studentExists = sut.existsStudentByEmail(email);

        // then
        assertThat(studentExists)
                .isFalse();
    }
}