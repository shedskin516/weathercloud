package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.entity.dto.StudentTeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    public Student createStudent(Student student);

    public Optional<Student> getStudentById(Long id);

    public Student updateStudent(Student studentDetails);

    public boolean deleteStudent(Long id);

    public List<Student> getAllStudents();

    public List<StudentTeacherDTO> findStudentsWithTeacherAndIdGreaterThan(int sId);
}
