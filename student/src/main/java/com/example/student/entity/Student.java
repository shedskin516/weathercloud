package com.example.student.entity;

import com.example.student.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentTeacher> studentTeachers = new HashSet<>();

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, int age, Gender gender, Set<StudentTeacher> studentTeachers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.studentTeachers = studentTeachers;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<StudentTeacher> getStudentTeachers() {
        return studentTeachers;
    }

    public void setStudentTeachers(Set<StudentTeacher> studentTeachers) {
        this.studentTeachers = studentTeachers;
    }
}
