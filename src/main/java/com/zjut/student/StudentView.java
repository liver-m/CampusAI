package com.zjut.student;
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
                    switch(choices2()){
                        case 1:
                            getStudentByIdUI();
                            break;
                        case 2:
                            getStudentByNameUI();
                            break;
                    }

                    break;
                case 5 :
                    deleteStudentUI();
                    break;
                case 6:
                    searchStudentsUI();
                    break;
                case 7 :
                    exit(0);
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
                6.搜索学生
                7.退出""");

        choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private int choices2(){
        int choice;
        System.out.println("""
                1.根据id查询
                2.根据姓名查询
                """);
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

        Student student = new Student(name,age,classroom);
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

    private void searchStudentsUI(){
        System.out.println("姓名(选填)：");
        String name = scanner.nextLine().trim();
        System.out.println("接下来依次输入MinAge，MaxAge：");
        System.out.println("最小年龄：");
        String minStr = scanner.nextLine().trim();
        Integer minage = minStr.isEmpty()?null:Integer.parseInt(minStr);
        System.out.println("最大年龄：");
        String maxStr = scanner.nextLine().trim();
        Integer maxage = maxStr.isEmpty()?null:Integer.parseInt(maxStr);
        List<Student> students = service.searchStudents(name,minage,maxage);
        for(Student stu : students){
            System.out.println("ID："+stu.getId()+
                    ", 姓名："+stu.getName()+
                    ", 年龄："+stu.getAge()+
                    ", 班级："+stu.getClassroom());
        }
    }

    private void getStudentByIdUI(){
        System.out.println("请输入你要查询的学生的id：");
        Long id = scanner.nextLong();
        scanner.nextLine();
        printStudent(id);
    }

    private void getStudentByNameUI(){
        System.out.println("请输入你要查询的学生的姓名：");
        String name = scanner.next();
        scanner.nextLine();
        Student student = service.getStudentByName(name);
        if (student != null) {
            System.out.println("ID："+ student.getId()+
                    ", 姓名："+ student.getName()+
                    ", 年龄："+ student.getAge()+
                    ", 班级："+ student.getClassroom());
        }else{
            System.out.println("不存在姓名为"+name+"的学生");
        }
    }

    private void deleteStudentUI(){
        System.out.println("请输入你要删除的学生的id：");
        Long id = scanner.nextLong();
        scanner.nextLine();

        if(!printStudent(id))return;

        service.deleteStudent(id);
    }

    private void updateStudentUI(){
        System.out.println("请输入你要更改的学生的id：");
        Long id = scanner.nextLong();
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

        Student student2 = new Student(id,name,age,classroom);
        service.updateStudent(student2);
    }

    private boolean printStudent(Long id){

        Student student = service.getStudentById(id);

        if (student != null) {
            System.out.println("ID："+ student.getId()+
                    ", 姓名："+ student.getName()+
                    ", 年龄："+ student.getAge()+
                    ", 班级："+ student.getClassroom());
            return true;
        }else{
            System.out.println("不存在ID为"+id+"的学生");
            return false;
        }
    }
}
