package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs) throws Exception {
        String err = null;

        try {
            if (userService.getUser(user.getUsername()) != null) {
                err = "Username is existed, can you try another username!";
            }

            if (err == null) {
                int result = userService.createNewUser(user);
                if (result < 0) {
                    err = "Signup is failed. Please try again!";
                }
            }

            if (err == null) {
                redirectAttrs.addFlashAttribute("successMsg", "You successfully signed up!");
                return "redirect:/login";
            } else {
                model.addAttribute("errorMsg", err);
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg", "Error when create user!");
        }

        return "signup";
    }
}
