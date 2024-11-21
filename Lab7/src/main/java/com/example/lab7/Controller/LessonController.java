package com.example.lab7.Controller;

import com.example.lab7.ApiResponse.ApiResponse;
import com.example.lab7.CourseModel.Lesson;
import com.example.lab7.Service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping("/add")
    public ResponseEntity addLesson(@RequestBody @Valid Lesson lesson, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        lessonService.addLesson(lesson);
        return ResponseEntity.status(200).body(new ApiResponse("Lesson added"));
    }

    @GetMapping("/get")
    public ResponseEntity getLesson(){
        ArrayList<Lesson> getlesson = lessonService.getLessons();
        return ResponseEntity.status(200).body(getlesson);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateLesson(@PathVariable String id,@RequestBody @Valid Lesson lesson,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (lessonService.updateLesson(id, lesson)){
            return ResponseEntity.status(200).body(new ApiResponse("Lesson updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Lesson not Exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteLesson(@PathVariable String id){
        if (lessonService.deleteLesson(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Lesson deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Lesson not Exist"));
    }

    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title){
        if (lessonService.searchByTitle(title) != null){
            return ResponseEntity.status(200).body(lessonService.searchByTitle(title));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Lesson by this title not Exist"));
    }

    @PutMapping("/changeDuration/{id}/{duration}")
    public ResponseEntity changeDuration(@PathVariable String id,@PathVariable int duration){
        if (lessonService.changeDuration(id, duration)){
            return ResponseEntity.status(200).body(new ApiResponse("Lesson Duration Changed"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Lesson not Exist"));
    }

    @PutMapping("/changeLocationClass/{id}/{location}")
    public ResponseEntity changeLocationClass(@PathVariable String id,@PathVariable String location){
        if (lessonService.changeLocationClass(id, location)){
            return ResponseEntity.status(200).body(new ApiResponse("Lesson Location Changed"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Lesson not Exist"));
    }


}
