package com.zjut.campusai.controller;

import com.zjut.campusai.common.ApiResponse;
import com.zjut.campusai.dto.StudentRequest;
import com.zjut.campusai.service.StudentService;
import com.zjut.campusai.vo.StudentVO;
import jakarta.validation.Valid;
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
    public ApiResponse<List<StudentVO>> getAllStu(){
        return ApiResponse.success(studentService.getAllStudents());
    }


    @GetMapping("{id}")
    public ApiResponse<StudentVO> getStuById(@PathVariable Long id){
        return ApiResponse.success(studentService.getStudentById(id));
    }


    @GetMapping("/slow")
    public String slowEndpoint() throws InterruptedException{
        Thread.sleep(500);
        return Thread.currentThread().toString();
    }

    @PostMapping
    public ApiResponse<StudentVO> addStu(@RequestBody @Valid StudentRequest request){
        return ApiResponse.success(studentService.addStudent(request));
    }

    @DeleteMapping("{id}")
    public ApiResponse<Void> deleteStu(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ApiResponse.success(null);
    }

    @PutMapping("{id}")
    public ApiResponse<StudentVO> updateStu(@PathVariable Long id,@RequestBody @Valid StudentRequest stu){
        return ApiResponse.success(studentService.updateStudent(id,stu));
    }
}