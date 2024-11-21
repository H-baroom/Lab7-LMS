package com.example.lab7.Service;

import com.example.lab7.CourseModel.Course;
import com.example.lab7.CourseModel.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList();

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public boolean updateCourse(String id,Course course){
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)){
                courses.set(i,course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id){
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addCourseToStudent(String id, User user){
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)){
                if (user.getRole().equalsIgnoreCase("Student")){
                    courses.get(i).getEnrolledStudents().add(user);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean assignTeacherToCourse(String id,User user){
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)){
                if (user.getRole().equalsIgnoreCase("Teacher")) {
                    courses.get(i).setInstructor(user);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Course> searchByTitle(String title){
        ArrayList<Course> courseTitle = new ArrayList();
        for (Course c:courses){
            if (c.getTitle().equalsIgnoreCase(title)){
                courseTitle.add(c);
            }
        }
        return courseTitle;
    }
}
