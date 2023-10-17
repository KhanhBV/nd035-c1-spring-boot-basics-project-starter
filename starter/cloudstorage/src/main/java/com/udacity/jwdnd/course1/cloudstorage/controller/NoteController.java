package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String uploadNote(@ModelAttribute("newNote") Note newNote, Authentication authentication, Model model) {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        newNote.setUserId(userId);

        if (newNote.getNoteId() == null) {
            try {
                noteService.createNote(newNote);
                model.addAttribute("successMsg", "Create note success");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("errMsg", "Create note failed!");
            }
        } else {
            try {
                noteService.updateNote(newNote);
                model.addAttribute("successMsg", "Update note success");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("errMsg", "Update note failed!");
            }
        }

        return "result";
    }

    @RequestMapping("/delete/{nodeId}")
    public String deleteNote(@PathVariable("nodeId") Integer nodeId, Model model) {
        try {
            noteService.deleteNote(nodeId);
            model.addAttribute("successMsg", "Delete note success");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errMsg", "Delete note failed!");
        }
        return "result";
    }
}
