package com.zjut.student.service;

import com.zjut.student.Student;
import com.zjut.student.exception.StudentNotFoundException;
import com.zjut.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceV2 {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceV2(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    //查所有学生
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    //按ID查单个学生
    public Student getStudentById(Long id){
        Optional<Student> result = studentRepository.findById(id);
        if(result.isEmpty())throw new StudentNotFoundException(id);
        else return result.get();
    }
    //新增学生
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    //按ID删除学生
    public void deleteStudentById(Long id){
        Optional<Student> result = studentRepository.findById(id);
        if(result.isEmpty())throw new StudentNotFoundException(id);
        else studentRepository.deleteById(id);
    }

    //修改学生信息
    public Student updateStudent(Long id, Student student){
        Optional<Student> result = studentRepository.findById(id);
        if(result.isEmpty())throw new StudentNotFoundException(id);
        else{
            student.setId(id);
            return studentRepository.save(student);
        }
    }
}

