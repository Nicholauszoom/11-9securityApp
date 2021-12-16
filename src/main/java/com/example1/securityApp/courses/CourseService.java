package com.example1.securityApp.courses;

import com.example1.securityApp.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    public final CourseRepository courseRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

//    public String postCourses(Course course){
//       courseRepository.save(course);
//       return "Success add a new course";
   // }
}
