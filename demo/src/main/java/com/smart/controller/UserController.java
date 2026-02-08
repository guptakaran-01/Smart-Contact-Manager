package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


// User DashBoard
 @GetMapping("/dashboard")
 public String userDashboard(){

    return "user/dashboard";
 }

 // User Profile
 @GetMapping("/profile")
 public String userProfile(){
    return "user/profile";
 }

}
