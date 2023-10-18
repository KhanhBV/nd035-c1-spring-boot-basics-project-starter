package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class ErrorFileController {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
     public String handleFileMaxSizeException(MaxUploadSizeExceededException e, Model model) {
        model.addAttribute("errMsg", "Upload file fail because size of file more than 5MB!!");
        return "result";
    }
}
