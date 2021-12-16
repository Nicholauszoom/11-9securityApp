package com.example1.securityApp;

import com.example1.securityApp.courses.Course;
import com.example1.securityApp.courses.CourseRepository;
import com.example1.securityApp.courses.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {
    @Autowired
    private final CourseService courseService;

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final UsersRepository usersRepository;

    public TestController(CourseService courseService, CourseRepository courseRepository, UsersRepository usersRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.usersRepository = usersRepository;
    }

//    @RequestMapping("/")
//    public ModelAndView defaultHome() {
//        return new ModelAndView("index");
//    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/dashboard")
    public ModelAndView userDashboard() {
        return new ModelAndView("dashboard");
    }

//    @RequestMapping("/accessdenied")
//    public ModelAndView userAccessError() {
//        return new ModelAndView("accessdenied");
//    }

    @RequestMapping("/admin/")
    public ModelAndView admin() {
        return new ModelAndView("admin/login");
    }

    @RequestMapping("/admin/login")
    public ModelAndView adminlogin() {
        return new ModelAndView("admin/login");
    }

    //TODO:admin dashboard
//    @RequestMapping("/admin/dashboard")
//    public ModelAndView admindashboard() {
//        return new ModelAndView("admin/dashboard");
//    }


    @RequestMapping("/admin/dashboard")
    public String admindashboard(Model model) {
        List<Users> admindashboard =usersRepository.findAll();
        model.addAttribute("admindashboard", admindashboard);
        return "admin/dashboard";
    }

    @RequestMapping("/admin/accessdenied")
    public ModelAndView adminAccessError() {
        return new ModelAndView("admin/accessdenied");
    }

    //TODO:Registration form
    @PostMapping("/process_register")
    public String processRegister(Users users) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);

        usersRepository.save(users);

        return "register_success";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("users", new Users());

        return "signup_form";
    }
    @DeleteMapping("users/{userId}")
    public void delete (@PathVariable("userId")Long userId){
//        Boolean exists= usersRepository.existsById(userId);
//        if (!exists){
//            throw new IllegalStateException("user with id"+userId+"not exists" );
//        }
       usersRepository.deleteById(userId);
    }
//TODO:Courses Post and Get Mapping
    @GetMapping("/getCourse")
    public String getCourse(Model model){
        List<Course> getCourse= courseRepository.findAll();
        model.addAttribute("getCourse" ,getCourse);
        return "course";
    }
//    @GetMapping("/registercourse")
//    public String RegistrationForm(Model model) {
//        model.addAttribute("course", new Course());
//
//        return "admin/regist_signup";
//    }
    @PostMapping("/registerCourseProcess")
    public String postCourses(@RequestBody Course course){
        courseRepository.save(course);
        return "admin/register_course_sucess";
    }
    @GetMapping("/register_course")
    public String regCourse(Model model){
        model.addAttribute("course", new Course());
        return "admin/register_course";
    }
}
