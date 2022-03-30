package com.example.demo.student.service;

import com.example.demo.student.model.Student;
import com.example.demo.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        Boolean userExist = studentRepository.existsById(id);

        if (userExist) {
            throw new IllegalArgumentException("User with id " + id + "doesn't exist");
        }

        studentRepository.deleteById(id);
    }
}
