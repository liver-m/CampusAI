package com.zjut.student.controller;

import com.zjut.student.Student;
import com.zjut.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStu(){
        return studentService.getAllStudents();
    }


    @GetMapping("{id}")
    public Student getStuById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }


    @GetMapping("/slow")
    public String slowEndpoint() throws InterruptedException{
        Thread.sleep(500);
        return Thread.currentThread().toString();
    }

    @PostMapping
    public Student addStu(@RequestBody Student stu){
        return studentService.addStudent(stu);
    }

    @DeleteMapping("{id}")
    public void deleteStu(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }

    @PutMapping("{id}")
    public Student updateStu(@PathVariable Long id,@RequestBody Student stu){
        return studentService.updateStudent(id,stu);
    }
}