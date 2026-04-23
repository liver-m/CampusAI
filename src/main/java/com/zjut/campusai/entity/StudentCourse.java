package com.zjut.campusai.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class StudentCourse {
    @EmbeddedId
    StudentCourseId id;

    private int score;

    public StudentCourse() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
