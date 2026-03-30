package com.zjut.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentService {
    //add students
    public void addStudent(Student stu){
        //1.construct connectivity 2.preparedStatement
        String sql = "insert into tb_student (name,age,classroom) values (?,?,?)";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,stu.getName());
            pstmt.setInt(2,stu.getAge());
            pstmt.setString(3,stu.getClassroom());

            int rows = pstmt.executeUpdate();

            if(rows > 0){
                System.out.println("学生添加成功！");
            } else{
                System.out.println("学生添加失败！");
            }

        }catch(SQLException e){
            System.out.println("错误："+e.getMessage());
            throw new RuntimeException("添加学生失败：数据库操作异常",e);
        }
    }

    //get all students
    public List<Student> getAllStudents() {
        List<Student> studentEntities = new ArrayList<>();
        String sql = "select * from tb_student";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                Long id = rs.getLong("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");
                String classroom = rs.getString("classroom");

                Student student = new Student(id, name,age, classroom);
                studentEntities.add(student);
            }
        } catch (SQLException e) {
            System.out.println("查询学生时出错："+e.getMessage());
            throw new RuntimeException("查询学生失败",e);
        }
        return studentEntities;
    }

    //查询单个学生
    //按id查询
    public Student getStudentById(Long ID){
        Student student = null;
        String sql = "select * from tb_student where id = ?";

        try(Connection coon = DBUtil.getConnection();
        PreparedStatement pstmt = coon.prepareStatement(sql)){

            pstmt.setLong(1,ID);

            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    Long id = rs.getLong("id");
                    int age = rs.getInt("age");
                    String name = rs.getString("name");
                    String classroom = rs.getString("classroom");

                    student = new Student(id, name,age,  classroom);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("数据库连接出错",e);
        }
        return student;
    }

    //按姓名查询
    public Student getStudentByName(String Name){
        Student student = null;
        String sql = "select * from tb_student where name = ?";

        try(Connection coon = DBUtil.getConnection();
            PreparedStatement pstmt = coon.prepareStatement(sql)){

            pstmt.setString(1,Name);

            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    Long id = rs.getLong("id");
                    int age = rs.getInt("age");
                    String name = rs.getString("name");
                    String classroom = rs.getString("classroom");

                    student = new Student(id, name, age, classroom);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("数据库连接出错",e);
        }
        return student;
    }

    public List<Student> searchStudents(String name, Integer ageMin, Integer ageMax) {
        List<Student> allstus = getAllStudents();           // 先拿到所有学生
        List<Student> result = new ArrayList<>();       // 新建结果列表

        for (Student s : allstus) {                         // 遍历每个学生
            // 两个boolean判断
            boolean nameMatch = (name == null || name.isEmpty() || s.getName().contains(name));                   // 你来填姓名模糊
            boolean ageMatch  = (ageMin==null || s.getAge()>=ageMin)&&
                    (ageMax==null||s.getAge()<=ageMax);
            if (nameMatch && ageMatch) {
                result.add(s);
            }
        }
        return result;                                  // 返回过滤后的列表
    }


    public PageResult<Student> searchStudentsWithPage(
            String name, Integer ageMin, Integer ageMax,String classroom,
            int currentPage, int pageSize) {
        StringBuilder sql = new StringBuilder("select * from tb_student where 1=1");
        List<Object> params = new ArrayList<>();

        if(name != null && !name.trim().isEmpty()){
            sql.append(" and name like ?");
            params.add("%" + name + "%");
        }

        if(ageMin != null){
            sql.append("and age >= ?");
            params.add(ageMin);
        }

        if(ageMax != null){
            sql.append(" and age <= ?");
            params.add(ageMax);
        }

        if(classroom != null && !classroom.trim().isEmpty()){
            sql.append(" and classroom like ?");
            params.add("%" + classroom + "%");
        }

        try(Connection coon = DBUtil.getConnection();
        PreparedStatement pstmt = coon.prepareStatement(String.valueOf(sql))){

            for(int i =0; i < params.size(); i++){
                pstmt.setObject(i + 1, params.get(i));
            }

            List<Student> list = new ArrayList<>();
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    list.add(new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("classroom")
                    ));
                }
            }

            return new PageResult<>(list.size(), list, currentPage, pageSize);

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    //删除学生
    public void deleteStudent(Long id){
        String sql = "delete from tb_student where id = ?";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setLong(1,id);
            int rows = pstmt.executeUpdate();

            if(rows > 0){
                System.out.println("学生删除成功！");
            }
            else{
                System.out.println("删除失败，ID不存在！");
            }

        }catch(SQLException e){
            throw new RuntimeException("数据库连接错误",e);
        }
    }


    //修改学生信息
    public void updateStudent(Student stu){
        String sql = "update tb_student set age = ?, name = ?, classroom = ? where id = ?";

        try(Connection coon = DBUtil.getConnection();
        PreparedStatement pstmt = coon.prepareStatement(sql)){

            pstmt.setInt(1,stu.getAge());
            pstmt.setString(2,stu.getName());
            pstmt.setString(3,stu.getClassroom());
            pstmt.setLong(4,stu.getId());

            int rows = pstmt.executeUpdate();
            if(rows > 0){
                System.out.println("学生信息修改学生成功！");
            }else{
                System.out.println("修改失败，ID不存在！");
            }

        }catch(SQLException e){
            throw new RuntimeException("数据库连接出错",e);
        }
    }



}