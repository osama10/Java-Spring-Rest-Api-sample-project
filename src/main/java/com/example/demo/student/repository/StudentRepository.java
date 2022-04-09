package com.example.demo.student.repository;

import com.example.demo.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsStudentByEmail(String email);
}
