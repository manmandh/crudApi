package com.marry.crudapi.controller;

import com.marry.crudapi.dto.request.UserCreationRequest;
import com.marry.crudapi.entity.User;
import com.marry.crudapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/insert")
//    public User insertTestUser(@RequestBody UserCreationRequest request) {
//        request.setUsername("MaryRyan");
//        request.setPassword("pass123");
//        request.setFirstName("Marry");
//        request.setLastName("Ryan");
//        request.setDob(LocalDate.of(2001, 1, 1));
//
//        System.out.println("Auto insert test user");
//        return userService.createRequest(request);
//    }

    @GetMapping("/home")
    public String text(Model model) {
        model.addAttribute("message", "Hello I am Marry!");
        return "home";
    }
}

