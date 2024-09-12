package com.example.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "student_teacher")
@IdClass(StudentTeacherId.class)
public class StudentTeacher {

    @Id
    @ManyToOne
    @JoinColumn(name = "s_id", nullable = false)
    @JsonIgnore
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "t_id", nullable = false)
    @JsonIgnore
    private Teacher teacher;

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
