package com.example.lab7.CourseModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "not valid id")
    @Size(min = 5,message = "id should be at least 5 character")
    private String id;
    @NotEmpty(message = "not valid title")
    @Size(min = 10,max = 40,message = "Title should contain 10-40 character")
    private String title;
    @NotEmpty(message = "not valid description")
    @Size(min = 25,max = 50,message = "Description should contain 25-50 character")
    private String description;
    private User instructor; // Teacher assigned to the course
    private ArrayList<User> enrolledStudents; // List of students in the course

}
