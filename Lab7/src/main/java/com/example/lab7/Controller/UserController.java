package com.example.lab7.Controller;

import com.example.lab7.ApiResponse.ApiResponse;
import com.example.lab7.CourseModel.Course;
import com.example.lab7.CourseModel.User;
import com.example.lab7.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }
    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(userService.updateUser(id,user)){
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not exist"));
    }

    @PostMapping("/addCourseToStudent/{id}")
    public ResponseEntity addCourseToStudent(@PathVariable String id,@RequestBody @Valid Course course, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (userService.addCourseToStudent(id, course)){
            return ResponseEntity.status(200).body(new ApiResponse("added course to user"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not exist"));
    }

    @PostMapping("/addCourseToTeacher/{id}")
    public ResponseEntity addCourseToTeacher(@PathVariable String id,@RequestBody @Valid Course course, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (userService.addCourseToTeacher(id, course)){
            return ResponseEntity.status(200).body(new ApiResponse("added course to Teacher"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not exist"));
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity getAllStudent(){
        if(userService.getAllStudent() !=null){
            ArrayList<User> stud = userService.getAllStudent();
            return ResponseEntity.status(200).body(stud);
        }
        return ResponseEntity.status(400).body(new ApiResponse("No student exist"));
    }
    @GetMapping("/getAllTeacher")
    public ResponseEntity getAllTeacher(){
        ArrayList<User> Teach = userService.getAllTeacher();
        if(userService.getAllTeacher() !=null){
            return ResponseEntity.status(200).body(Teach);
        }
        return ResponseEntity.status(400).body(new ApiResponse("No Teacher exist"));
    }
}
