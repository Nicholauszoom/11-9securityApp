//package com.example1.securityApp;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//import java.util.List;
//@RestController
//@RequestMapping(path ="api/v1/Rebuild")
//public class AdminController {
//    private AdminDetailsServiceImpl adminDetailsService;
//    private UsersRepository usersRepository;
//
//    //GetMapping Implementation on this controller
////    @GetMapping
////    public List<Users> getUsers() {
////        return adminDetailsService.getsUsers();
////    }
//
//    @PostMapping("/process_register")
//    public String processRegister(Users users) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(users.getPassword());
//        users.setPassword(encodedPassword);
//
//        usersRepository.save(users);
//
//        return "register_success";
//
//    }
//}