package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.web.form.MultiPartFileForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/multipartfile")
public class MultipartFileUploadController {

    @RequestMapping(path = "upload", method = RequestMethod.GET)
    public String showMultipartFileUploadPage(Model model) {
        model.addAttribute(new MultiPartFileForm());
        return "/multipartfile/upload";
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadEvidence(@ModelAttribute("multiPartFileForm") MultiPartFileForm multiPartFileForm, Model model, Errors errors) {

        try {
            Files.write(Paths.get("/tmp/" + multiPartFileForm.getEvidenceDocument().getOriginalFilename()), multiPartFileForm.getEvidenceDocument().getBytes());
            model.addAttribute("successfulUpload", true);
        } catch (IOException e) {
            model.addAttribute("uploadFailed", true);
            e.printStackTrace();
        }
        return "/multipartfile/upload";
    }
}
