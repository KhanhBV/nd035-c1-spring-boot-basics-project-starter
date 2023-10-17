package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, File file, Authentication authentication, Model model) {
        User user = userService.getUser(authentication.getName());
        file.setUserId(user.getUserId());

        if (fileService.getFileByFileName(file.getFileName()) != null) {
            model.addAttribute("errMsg", "Can not upload file because file name is exist!!!");
        } else {
            try {
                fileService.uploadFile(file, multipartFile);
                model.addAttribute("successMsg", "Upload file successful");
            } catch (IOException e) {
               e.printStackTrace();
            }
        }

        return "result";
    }

    @RequestMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId, Model model) {
        try {
            fileService.deleteFile(fileId);
            model.addAttribute("successMsg", "Delete file is success!");
        } catch (Exception e) {
            model.addAttribute("errMsg", "Delete file is failed");
            e.printStackTrace();
        }
        return "result";
    }

    @RequestMapping("/view/{fileId}")
    public ResponseEntity downloadFile(@PathVariable("fileId") Integer fileId) {
        try {
            File file = fileService.getFileById(fileId);

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(new ByteArrayResource(file.getFileData()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
