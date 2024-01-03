package com.techacademy.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

      /** Display User update screen */
    @GetMapping("/update/{id}/")
    public String getUser(@PathVariable("id") Integer id, Model model) {
     // Register for Model
        model.addAttribute("user", service.getUser(id));
     // Go to User update screen
        return "user/update";
    }

    /** User update process */
    @PostMapping("/update/{id}/")
    public String postUser(User user) {
     // User Registration
        service.saveUser(user);
     // Redirect to list screen
        return "redirect:/user/list";
    }
    // ----- 追加:ここから -----
    /** User deletion process */
    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {
     // Delete User in bulk
        service.deleteUser(idck);
     // Redirect to list screen
        return "redirect:/user/list";
    }
    // ----- 追加:ここまで -----
}