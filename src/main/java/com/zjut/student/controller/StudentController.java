package com.zjut.student.controller;

import com.zjut.student.Student;
import com.zjut.student.service.StudentServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceV2 studentServiceV2;

    @Autowired
    public StudentController(StudentServiceV2 studentServiceV2){
        this.studentServiceV2 = studentServiceV2;
    }

    @GetMapping
    public List<Student> getAllStu(){
        return studentServiceV2.getAllStudents();
    }


    @GetMapping("{id}")
    public Student getStuById(@PathVariable Long id){
        return studentServiceV2.getStudentById(id);
    }

    @PostMapping
    public Student addStu(@RequestBody Student stu){
        return studentServiceV2.addStudent(stu);
    }

    @DeleteMapping("{id}")
    public void deleteStu(@PathVariable Long id){
        studentServiceV2.deleteStudentById(id);
    }

    @PutMapping("{id}")
    public Student updateStu(@PathVariable Long id,@RequestBody Student stu){
        return studentServiceV2.updateStudent(id,stu);
    }
}