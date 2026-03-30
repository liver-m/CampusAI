package com.zjut.student;

import com.zjut.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);

    }

    @Override
    public void run(String... args) throws Exception {
        Student stu = new Student("张三",21,"软外1班");
        studentRepository.save(stu);
        System.out.println("===所有学生===");
        studentRepository.findAll().forEach(System.out::println);
    }
}