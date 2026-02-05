package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.form.UserForm;
import com.smart.model.User;
import com.smart.services.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping("/home")
    public String home(){
        
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        
        return "about";
    }

     @RequestMapping("/register")
    public String registerPage(Model model){
        UserForm userForm=new UserForm();
        
        model.addAttribute("userForm",userForm);
        return "register";
    }

    //processing register
    @PostMapping("do-register")
     public String processRegister(@ModelAttribute UserForm userForm){
         
       User user= User.builder()
       .name(userForm.getName())
       .email(userForm.getEmail())
       .password(userForm.getPassword())
       .about(userForm.getAbout())
       .phoneNumber(userForm.getPhoneNumber())
       .profileLink("https://picsum.photos/id/237/200/300")
       .build();
          userService.saveUser(user);

        return "redirect:/register";
     }


}
