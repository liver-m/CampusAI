package com.zjut.student;

public class Student{
    private int age;
    private String name;
    private String classroom;
    private int id;

    public Student(){
    }

    public Student(int age, String name, String classroom){
        this.age = age;
        this.name = name;
        this.classroom = classroom;
    }

    public Student(int id,int age,String name,String classroom){
        this.id = id;
        this.age = age;
        this.name = name;
        this.classroom =classroom;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public void setClassroom(String classroom){
        this.classroom = classroom;
    }

    public String getClassroom(){
        return this.classroom;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}