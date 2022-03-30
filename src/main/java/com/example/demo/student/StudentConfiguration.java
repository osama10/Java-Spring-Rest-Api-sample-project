package com.example.demo.student;

import com.example.demo.student.model.Student;
import com.example.demo.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository) {
        return args -> {
            Student osama = new Student( "Osama",  "osama@yahoo.com", LocalDate.of(1995, 12, 10));
            Student shazeen = new Student( "Shazeen",  "shzeen@yahoo.com", LocalDate.of(1996, 06, 13));
            studentRepository.saveAll(List.of(osama, shazeen));
        };
    }
}
