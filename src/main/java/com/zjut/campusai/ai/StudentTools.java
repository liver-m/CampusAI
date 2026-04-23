package com.zjut.campusai.ai;

import com.zjut.campusai.entity.Student;
import com.zjut.campusai.service.StudentService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentTools {
    private final StudentService studentService;
    @Autowired
    public StudentTools(StudentService studentService){
        this.studentService = studentService;
    }

    @Tool("根据学生姓名查询学生信息")
    public Student getStu(String name){
        return studentService.getStudentByName(name);
    }

    @Tool("根据学生id修改学生的成绩，成绩只能在0~100之间。")
    public String updateScore(Long studentId, int newScore){
        if(newScore < 0 ||newScore > 100)return "成绩不合适，应是0~100之间";
        Student s;
        try{
             s = studentService.updateScore(studentId,newScore);
        }catch(Exception e){
            return "不存在id为" + studentId + "的学生";
        }
        return s.toString();
    }

    @Tool("根据学生id查询学生的各科成绩,并给学生推荐学习课程")
    public String getCourseRecommendation(Long studentId){
        List<Object[]> list =  studentService.getStudentCourseScoreById(studentId);
        if(list.isEmpty())return "没有该学生的选课记录";

        StringBuilder sb = new StringBuilder();
        list.forEach(row ->{
            sb.append(String.format("%s - %s\n", row[0], row[1]));
        });
        return sb.toString();
    }
}
