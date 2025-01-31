package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final FileService fileService;

    private final EncryptionService encryptionService;

    public HomeController(UserService userService, NoteService noteService, CredentialService credentialService, FileService fileService, EncryptionService encryptionService) {
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String showHomePage(Model model, Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        model.addAttribute("encryptionService",encryptionService);

        model.addAttribute("newNote", new Note());
        model.addAttribute("newCredential", new Credential());

        model.addAttribute("listNote", noteService.getNotes(userId));
        model.addAttribute("listFile", fileService.getFiles(userId));
        model.addAttribute("listCredential", credentialService.getCredentials(userId));

        return "home";
    }
}
