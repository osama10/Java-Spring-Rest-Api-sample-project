package com.example.demo;

import com.example.demo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Osama");
	}

	@GetMapping
	public List<Student> firstAPI() {
		return List.of(
				new Student(1L, "Osama",  "osama@yahoo.com", LocalDate.of(1995, 12, 10), 25),
				new Student(2L, "Shazeen",  "shzeen@yahoo.com", LocalDate.of(1996, 06, 13), 24)
		);
	}
}
