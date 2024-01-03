package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.User;
import com.techacademy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /** display list screen */
    @GetMapping("/list")
    public String getList(Model model) {
        // Register all search results to Model
        model.addAttribute("userlist", service.getUserList());
        // Screen transition to user/list.html
        return "user/list";
    }

    // ----- 追加:ここから -----
    /** Display User registration screen */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
     // Go to User registration screen
        return "user/register";
    }

    /** User registration process */
    @PostMapping("/register")
    public String postRegister(User user) {
     // User Registration
        service.saveUser(user);
        // Redirect to list screen
        return "redirect:/user/list";
    }
    // ----- 追加:ここまで -----
}