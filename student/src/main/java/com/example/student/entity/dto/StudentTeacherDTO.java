package com.example.student.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTeacherDTO {
    private Long sId;
    private String sFirstName;
    private String sLastName;
    private Long tId;
    private String tFirstName;
    private String tLastName;
    private String subject;
}
