package com.example.lab7.Controller;

import com.example.lab7.ApiResponse.ApiResponse;
import com.example.lab7.CourseModel.Course;
import com.example.lab7.CourseModel.User;
import com.example.lab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final View error;

    @GetMapping("/get")
    public ResponseEntity getCourses(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity addCourses(@RequestBody @Valid Course course, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id,@RequestBody @Valid Course course,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (courseService.updateCourse(id,course)){
            return ResponseEntity.status(200).body(new ApiResponse("Course Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id){
        if (courseService.deleteCourse(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not exist"));
    }

    @PutMapping("/addCourseToStudent/{id}")
    public ResponseEntity addCourseToStudent(@PathVariable String id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (courseService.addCourseToStudent(id,user)){
            return ResponseEntity.status(200).body(new ApiResponse("Student added to Course"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not a student or course not exist"));
    }

    @PostMapping("/assignTeacherToCourse/{id}")
    public ResponseEntity assignTeacherToCourse(@PathVariable String id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(courseService.assignTeacherToCourse(id, user)){
            return ResponseEntity.status(200).body(new ApiResponse("Teacher assign to Course"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not a Teacher or course not exist"));

    }
    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title){
        ArrayList<Course> ByTitle = courseService.searchByTitle(title);
        if (ByTitle != null){
            return ResponseEntity.status(200).body(ByTitle);
        }
        return ResponseEntity.status(400).body(new ApiResponse("not exist any course by this title"));
    }

}
