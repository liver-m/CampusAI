package com.zjut.campusai.repository;

import com.zjut.campusai.entity.StudentCourse;
import com.zjut.campusai.entity.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {
    @Query("""
            SELECT c.courseName, sc.score
            FROM Course c
            JOIN StudentCourse sc
            ON c.id = sc.id.courseId
            WHERE sc.id.studentId = :studentId
            """)
    List<Object[]> findCoursesByStudentId(@Param("studentId") Long studentId);
}
