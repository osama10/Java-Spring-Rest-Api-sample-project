package com.example.demo.student.service;

import com.example.demo.student.model.Student;
import com.example.demo.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Struct;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public void createStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());

        if (optionalStudent.isPresent()) {
            throw  new IllegalArgumentException("Email already present");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Boolean studentExist = studentRepository.existsById(id);

        if (!studentExist) {
            throw new IllegalArgumentException("User with id " + id + " doesn't exist");
        }

        studentRepository.deleteById(id);

    }

    @Transactional
    public void updateStudent(Long id, Optional<String> name, Optional<String> email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Student of Id" + id + " doesn't exist")
                );

        if (name.isPresent()
                && !name.get().isEmpty()
                && !student.getName().equals(name.get())) {
            student.setName(name.get());
        }

        if (email.isPresent()
                && !email.get().isEmpty()
                && !email.get().equals(student.getEmail())) {
            Boolean studentExist = studentRepository.existsById(id);

            if (!studentExist) {
                throw new IllegalArgumentException("User with id " + id + " doesn't exist");
            }

            student.setEmail(email.get());
        }

    }


}
