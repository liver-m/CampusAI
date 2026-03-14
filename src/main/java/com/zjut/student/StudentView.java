package com.zjut.student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class StudentView {
    StudentService service = new StudentService();
    Scanner scanner = new Scanner(System.in);

    public void start(){
        System.out.println("欢迎来到学生管理系统！");

        while (true) {
            System.out.println("功能：");
            switch (choices()){
                case 1 :
                    addStudentUI();
                    break;
                case 2 :
                    updateStudentUI();
                    break;
                case 3 :
                    getAllStudentUI();
                    break;
                case 4 :
                    getStudentByIdUI();
                    break;
                case 5 :
                    deleteStudentUI();
                    break;
                case 6 :
                    exit(0);       //???
                default:
                    break;
            }
        }
    }

    private int choices(){
        int choice;
        System.out.println("""
                1.添加学生
                2.修改某个学生信息
                3.查询所有学生信息
                4.查询某个学生信息
                5.删除某个学生
                6.退出""");

        choice = scanner.nextInt();
        return choice;
    }

    private void addStudentUI(){

        System.out.println("请输入学生的姓名：");
        String name = scanner.next();
        scanner.nextLine();

        System.out.println("请输入学生的年龄：");
        int age = scanner.nextInt();
        scanner.nextLine();


        System.out.println("请输入学生的班级：");
        String classroom = scanner.nextLine();

        Student student = new Student(age,name,classroom);
        service.addStudent(student);
    }

    private void getAllStudentUI(){
        List<Student> students = service.getAllStudents();
        for(Student stu : students){
            System.out.println("ID："+stu.getId()+
                    ", 姓名："+stu.getName()+
                    ", 年龄："+stu.getAge()+
                    ", 班级："+stu.getClassroom());
        }
    }

    private void getStudentByIdUI(){
        System.out.println("请输入你要查询的学生的id：");
        int id = scanner.nextInt();
        scanner.nextLine();
        printStudent(id);
    }

    private void deleteStudentUI(){
        System.out.println("请输入你要删除的学生的id：");
        int id = scanner.nextInt();
        scanner.nextLine();

        if(!printStudent(id))return;

        service.deleteStudent(id);
    }

    private void updateStudentUI(){
        System.out.println("请输入你要更改的学生的id：");
        int id = scanner.nextInt();
        scanner.nextLine();

        if(!printStudent(id))return;

        System.out.println("你要将其姓名改为：");
        String name = scanner.next();
        scanner.nextLine();
        System.out.println("你要将其年龄改为：");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("你要将其班级改为：");
        String classroom = scanner.nextLine();

        Student student2 = new Student(id,age,name,classroom);
        service.updateStudent(student2);
    }

    private boolean printStudent(int id){

        Student student = service.getStudentById(id);

        if (student != null) {
            System.out.println("ID："+student.getId()+
                    ", 姓名："+student.getName()+
                    ", 年龄："+student.getAge()+
                    ", 班级："+student.getClassroom());
            return true;
        }else{
            System.out.println("不存在ID为"+id+"的学生");
            return false;
        }
    }
}
