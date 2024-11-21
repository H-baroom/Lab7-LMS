package com.example.lab7.CourseModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Lesson {
    @NotEmpty(message = "not valid id")
    @Size(min = 5,message = "id should be at least 5 character")
    private String id;
    @NotEmpty(message = "not valid title")
    @Size(min = 10,max = 40,message = "Title should contain 10-40 character")
    private String title;
    @NotEmpty(message = "not valid content")
    @Size(min = 120,max = 400,message = "content should contain 120-400 character")
    private String content;
    @NotNull(message = "not valid Duration")
    @Min(value = 30, message = "Duration must be at least 30 minute")
    private int durationInMinutes;
    @NotEmpty(message = "not valid Location class")
    private String locationClass;
    private Course course;
}
