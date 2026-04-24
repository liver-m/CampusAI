package com.zjut.campusai.ai;

import com.zjut.campusai.dto.WarningReport;
import com.zjut.campusai.entity.Student;
import com.zjut.campusai.service.StudentService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StudentTools {
    private final StudentService studentService;
    @Autowired
    public StudentTools(StudentService studentService){
        this.studentService = studentService;
    }

    @Tool("根据学生姓名查询学生信息")
    public Student getStudentInfoByName(String name){
        return studentService.getStudentByName(name);
    }

    @Tool("根据学生id查询学生信息")
    public Student getStudentInfoById(Long studentId) { return studentService.getStudentById(studentId);}

    @Tool("根据学生id修改学生的成绩，成绩只能在0~100之间。")
    public String updateScore(Long studentId, int newScore){
        return studentService.updateScore(studentId,newScore);
    }

    @Tool("根据学生id查询学生的各科成绩,并给学生推荐学习课程")
    public String getCourseRecommendation(Long studentId){
        return studentService.getCourseRecommendation(studentId);
    }

    @Tool("根据id查询学生课程成绩，并给出成绩预警")
    public WarningReport generateWarningReport(Long studentId){
        return studentService.generateWarningReport(studentId);
    }
}
