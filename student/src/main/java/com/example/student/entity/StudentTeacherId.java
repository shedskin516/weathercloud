package com.example.student.entity;

import java.io.Serializable;
import java.util.Objects;

public class StudentTeacherId implements Serializable {

    private Long student;
    private Long teacher;

    // Constructors, equals(), and hashCode() methods
    public StudentTeacherId() {}

    public StudentTeacherId(Long student, Long teacher) {
        this.student = student;
        this.teacher = teacher;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTeacherId that = (StudentTeacherId) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, teacher);
    }
}
