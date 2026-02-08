package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.form.UserForm;
import com.smart.helper.Identity;
import com.smart.helper.message;
import com.smart.model.User;
import com.smart.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping("/home")
    public String home() {

        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage() {

        return "about";
    }

    @RequestMapping("/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);
        return "register";
    }


    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }


    // processing register
    @PostMapping("do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,
            HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .about(userForm.getAbout())
                .phoneNumber(userForm.getPhoneNumber())
                .profileLink("https://picsum.photos/id/237/200/300")
                .build();
        userService.saveUser(user);

        message mess = message.builder().content("Registration Successfull").type(Identity.success).build();

        session.setAttribute("message", mess);

        return "redirect:/register";
    }

}
