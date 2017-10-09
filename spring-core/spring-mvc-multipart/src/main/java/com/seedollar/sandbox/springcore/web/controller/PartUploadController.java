package com.seedollar.sandbox.springcore.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/part")
public class PartUploadController {

    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    public String showPartUploadPage() {
        return "/part/upload";
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadEvidence(@RequestPart("evidenceDocument") Part evidenceDocument, Model model) {

        try {
            evidenceDocument.write(Paths.get("/tmp/" + evidenceDocument.getName()).toAbsolutePath().toString());
            model.addAttribute("successfulUpload", true);
        } catch (IOException e) {
            model.addAttribute("uploadFailed", true);
            e.printStackTrace();
        }
        return "/part/upload";
    }
}
