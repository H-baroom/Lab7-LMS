package com.example.lab7.CourseModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "not valid id")
    @Size(min = 5,message = "id should be at least 5 character")
    private String id;
    @NotEmpty(message = "not valid name")
    @Size(min = 5,max = 30,message = "Name should contain 5-30 character ")
    private String name;
    @Email(message = "not valid email")
    private String email;
    @NotEmpty(message = "not valid role")
    @Pattern(regexp = "^(Student|Teacher|Admin)$",
            message = "Role must be one of the following: Student, Teacher, or Admin."
    )
    private String role;
    // Relationships (e.g., a student can enroll in courses)
    private ArrayList<Course> enrolledCourses; // For students
    private ArrayList<Course> teachingCourses; // For teachers
}
