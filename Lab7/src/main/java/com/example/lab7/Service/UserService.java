package com.example.lab7.Service;

import com.example.lab7.CourseModel.Course;
import com.example.lab7.CourseModel.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    ArrayList<User> users = new ArrayList();

    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(String id, User user){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addCourseToStudent(String id,Course course){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                if(users.get(i).getRole().equalsIgnoreCase("Student")) {
                    users.get(i).getEnrolledCourses().add(course);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean addCourseToTeacher(String id,Course course){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                if(users.get(i).getRole().equalsIgnoreCase("Teacher")) {
                    users.get(i).getTeachingCourses().add(course);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<User> getAllStudent(){
        ArrayList<User> students = new ArrayList();
        for (User u:users){
            if (u.getRole().equalsIgnoreCase("Student")){
                students.add(u);
            }
        }
        return students;
    }
    public ArrayList<User> getAllTeacher(){
        ArrayList<User> teachers = new ArrayList();
        for (User u:users){
            if (u.getRole().equalsIgnoreCase("Teacher")){
                teachers.add(u);
            }
        }
        return teachers;
    }

}
