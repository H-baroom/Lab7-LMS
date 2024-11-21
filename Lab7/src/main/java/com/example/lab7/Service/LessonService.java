package com.example.lab7.Service;

import com.example.lab7.CourseModel.Lesson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LessonService {
    ArrayList<Lesson> lessons = new ArrayList();

    public ArrayList<Lesson> getLessons(){
        return lessons;
    }
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
    public boolean updateLesson(String id,Lesson lesson){
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getId().equals(id)){
                lessons.set(i,lesson);
                return true;
            }
        }
        return false;
    }

    public boolean deleteLesson(String id){
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getId().equals(id)){
                lessons.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Lesson> searchByTitle(String title){
        ArrayList<Lesson> LessonByTtile = new ArrayList();
        for (Lesson l:lessons){
            if (l.getTitle().equalsIgnoreCase(title)){
                LessonByTtile.add(l);
            }
        }
        return null;
    }

    public boolean changeDuration(String id,int duration){
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getId().equals(id)){
                lessons.get(i).setDurationInMinutes(duration);
                return true;
            }
        }
        return false;
    }

    public boolean changeLocationClass(String id,String location){
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getId().equals(id)){
                lessons.get(i).setLocationClass(location);
                return true;
            }
        }
        return false;
    }


}
