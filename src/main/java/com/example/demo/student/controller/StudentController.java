package com.example.demo.student.controller;
import com.example.demo.student.model.Student;
import com.example.demo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public  void updateStudent(@PathVariable("studentId") Long id,
                               @RequestParam(required = false) Optional<String> name,
                               @RequestParam(required = false) Optional<String> email) {
        studentService.updateStudent(id, name, email);
    }
}
