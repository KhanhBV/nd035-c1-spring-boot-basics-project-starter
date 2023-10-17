package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    public String createCredential(@ModelAttribute("newCredential") Credential newCredential, Authentication authentication, Model model) {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        newCredential.setUserId(userId);

        if (newCredential.getCredentialId() != null) {
            try {
                credentialService.updateCredential(newCredential);
                model.addAttribute("successMsg", "Update credential is successfully");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("errMsg", "Update credential is failed");
            }
        } else {
            try {
                credentialService.createCredential(newCredential);
                model.addAttribute("successMsg", "Create credential is successfully");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("errMsg", "Create credential is failed");
            }
        }

        return "result";
    }

    @RequestMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, Model model) {
        try {
            credentialService.deleteCredential(credentialId);
            model.addAttribute("successMsg", "Delete credential is success");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errMsg", "Delete credential is failed");
        }

        return "result";
    }
}
