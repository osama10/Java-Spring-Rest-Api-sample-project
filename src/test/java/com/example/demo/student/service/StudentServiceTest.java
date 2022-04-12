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
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static  org.mockito.Mockito.*;


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
    void test_whenANewStudentIsPassed_StudentIsCreatedByStudentService() {
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));
        sut.createStudent(student);
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository)
                .save(studentArgumentCaptor.capture());

        assertThat(student)
                .isEqualTo(studentArgumentCaptor.getValue());
    }

    @Test
    void test_whenAStudentWithAlreadyExistedEmailIsCreated_serviceWillThrowError(){
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));

        given(studentRepository.existsStudentByEmail(student.getEmail()))
                .willReturn(true);

        assertThatThrownBy(() -> sut.createStudent(student))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Email already present");

        verify(studentRepository, never())
                .save(any());
    }

    @Test
    void test_whenDeleteIsCalledWithCorrectStudentId_studentServiceDeletesStudent() {
        Long studentId = getRandomId();

        given(studentRepository.existsById(studentId))
                .willReturn(true);

        sut.deleteStudent(studentId);

        ArgumentCaptor<Long> studentIdArgCaptor = ArgumentCaptor.forClass(Long.class);

        verify(studentRepository)
                .deleteById(studentIdArgCaptor.capture());

        assertThat(studentId)
                .isEqualTo(studentIdArgCaptor.getValue());
    }

    @Test
    void test_whenDeleteIsCalledWithWrongStudentId_studentServiceThrowsError() {
        Long studentId = getRandomId();

        given(studentRepository.existsById(studentId))
                .willReturn(false);

        assertThatThrownBy(() -> sut.deleteStudent(studentId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User with id " + studentId + " doesn't exist");
    }

    @Test
    void test_whenUpdateIsCalledWithNewName_studentServiceUpdatesTheName() {
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));
        String newName = "Osama Bin Bashir";
        Long studentId = getRandomId();

        given(studentRepository.findById(studentId))
                .willReturn(Optional.of(student));

        sut.updateStudent(studentId, Optional.of(newName), Optional.empty());
        assertThat(newName)
                .isEqualTo(student.getName());
    }

    @Test
    void test_whenUpdateIsCalledWithNewEmail_studentServiceUpdatesTheName() {
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));
        String newEmail = "osama.binbashir@yahoo.com";
        Long studentId = getRandomId();

        given(studentRepository.findById(studentId))
                .willReturn(Optional.of(student));

        given(studentRepository.existsById(studentId))
                .willReturn(true);

        sut.updateStudent(studentId, Optional.empty(), Optional.of(newEmail));
        assertThat(newEmail)
                .isEqualTo(student.getEmail());
    }

    @Test
    void test_whenUpdateIsCalledEmailThatDoesntExist_studentServiceThrowsError() {
        Student student = new Student("Osama",
                "osama@yahoo.com",
                LocalDate.of(1995, 12, 19));
        String newEmail = "osama.binbashir@yahoo.com";
        Long studentId = getRandomId();

        given(studentRepository.findById(studentId))
                .willReturn(Optional.of(student));

        given(studentRepository.existsById(studentId))
                .willReturn(true);

        assertThatThrownBy(() -> sut.updateStudent(studentId, Optional.empty(), Optional.of(newEmail)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User with id " + studentId + " doesn't exist");

    }

    private Long getRandomId() {
         Random random = new Random();
         return random.nextLong();
    }
}