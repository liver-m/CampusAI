package com.zjut.campusai.service;

import com.zjut.campusai.dto.WarningReport;
import com.zjut.campusai.entity.Student;
import com.zjut.campusai.exception.CourseNotFoundException;
import com.zjut.campusai.exception.InvalidScoreException;
import com.zjut.campusai.exception.StudentNotFoundException;
import com.zjut.campusai.repository.StudentCourseRepository;
import com.zjut.campusai.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public String updateScore(Long id, int newScore){
        if(newScore < 0 ||newScore > 100)throw new InvalidScoreException("成绩不合适，应是0~100之间");
        Optional<Student> result = studentRepository.findById(id);
        if(result.isEmpty())throw new StudentNotFoundException(id);
        else{
            Student student = result.get();
            student.setScore(newScore);
            studentRepository.save(student);
            return student.toString();
        }
    }

    //根据Id进行联表查询学生的选课成绩
    public List<Object[]> getStudentCourseScoreById(Long id){
        Optional<Student> result1 = studentRepository.findById(id);
        if(result1.isEmpty())throw new StudentNotFoundException(id);
        return studentCourseRepository.findCoursesByStudentId(id);
    }

    //查询学生各科成绩，给学生推荐学习课程
    public String getCourseRecommendation(Long studentId){
        List<Object[]> list = getStudentCourseScoreById(studentId);
        if(list.isEmpty())throw new CourseNotFoundException("没有该学生的选课记录");

        StringBuilder sb = new StringBuilder();
        list.forEach(row ->{
            sb.append(String.format("%s - %s\n", row[0], row[1]));
        });
        return sb.toString();
    }

    //查询学生的成绩情况，加以判断，并进行标识，以生成学习预警
    public WarningReport generateWarningReport(Long studentId){
        List<Object[]> list = getStudentCourseScoreById(studentId);
        if(list.isEmpty())throw new CourseNotFoundException("没有该学生的选课记录");

        Student student = getStudentById(studentId);
        WarningReport warningReport;

        String riskLevel;
        int[] failAmount = {0};
        Map<String,Integer> courseScore = new HashMap<>();

        list.forEach(row ->{
            courseScore.put((String) row[0],(Integer) row[1]);
            if((Integer) row[1] < 60) failAmount[0]++;
        });

        if(failAmount[0] == 0)riskLevel ="low";
        else if (failAmount[0] > 0 && failAmount[0] < 3) riskLevel = "medium";
        else riskLevel = "high";

        warningReport = new WarningReport(student.getName(),courseScore,failAmount[0],riskLevel);
        return warningReport;
    }
}

