package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.web.form.RequestPartForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Controller
@RequestMapping("/requestpart")
public class RequestPartUploadController {

    public RequestPartUploadController() {
    }

    @RequestMapping(path = "upload", method = RequestMethod.GET)
    public String uploadPage(Model model) {
        model.addAttribute(new RequestPartForm());
        return "/requestpart/upload";
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadEvidence(@RequestPart("evidenceDocument") byte[] file, Model model, Errors errors){
        try {
            Files.write(Paths.get("/tmp/" + UUID.randomUUID().toString() + ".evd"), file, StandardOpenOption.CREATE);
            model.addAttribute("successfulUpload", true);
        } catch (IOException e) {
            model.addAttribute("uploadFailed", true);
        }
        return "/requestpart/upload";
    }
}

