package com.example.demo.student.service;

import com.example.demo.student.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static  org.mockito.Mockito.*;


class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService sut;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void  setup(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        sut = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();

    }
    @Test
    void getStudents() {
        // When
        sut.getStudents();

        // Then
        verify(studentRepository).findAll();
    }

    @Test
    @Disabled
    void createStudent() {
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