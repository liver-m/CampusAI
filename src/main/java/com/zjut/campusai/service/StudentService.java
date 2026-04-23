package com.zjut.campusai.service;

import com.zjut.campusai.entity.Student;
import com.zjut.campusai.exception.StudentNotFoundException;
import com.zjut.campusai.repository.StudentCourseRepository;
import com.zjut.campusai.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository,StudentCourseRepository studentCourseRepository){
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
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

    //按姓名查学生
    public Student getStudentByName(String name){
        Optional<Student> result = studentRepository.findByName(name);
        if(result.isEmpty())throw new StudentNotFoundException(name);
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

    //修改学生成绩
    public Student updateScore(Long id, int newScore){
        Optional<Student> result = studentRepository.findById(id);
        if(result.isEmpty())throw new StudentNotFoundException(id);
        else{
            Student student = result.get();
            student.setScore(newScore);
            return studentRepository.save(student);
        }
    }

    //根据Id进行联表查询学生的选课成绩
    public List<Object[]> getStudentCourseScoreById(Long id){
        Optional<Student> result1 = studentRepository.findById(id);
        if(result1.isEmpty())throw new StudentNotFoundException(id);
        return studentCourseRepository.findCoursesByStudentId(id);
    }
}

