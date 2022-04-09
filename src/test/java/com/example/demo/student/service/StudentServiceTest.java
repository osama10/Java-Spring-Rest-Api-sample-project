package com.example.demo.student.service;

import com.example.demo.student.model.Student;
import com.example.demo.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static  org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService sut;

    @BeforeEach
    void  setup(){
        sut = new StudentService(studentRepository);
    }
    @Test
    void getStudents() {
        // When
        sut.getStudents();

        // Then
        verify(studentRepository)
                .findAll();
    }

    @Test
    void canCreateStudent() {
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));
        sut.createStudent(student);

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

       assertEquals(student, studentArgumentCaptor.getValue());

    }

    @Test
    void willThrowErrorIfEmailIsALreadyPresent(){
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));

        given(studentRepository.existsStudentByEmail(student.getEmail()))
                .willReturn(true);

        assertThatThrownBy(() -> sut.createStudent(student))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Email already present");

    }

    @Test
    @Disabled
    void deleteStudent() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}