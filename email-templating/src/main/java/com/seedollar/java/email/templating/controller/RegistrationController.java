package za.co.seedollar.java.email.templating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.co.seedollar.java.email.templating.service.RegistrationService;

/**
 * Created by seedollar on 5/4/16.
 */
@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping("/")
    public String registrationPage(Model model) {
        model.addAttribute(new RegistrationForm());
        return "registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registrationSubmit(Model model, @ModelAttribute RegistrationForm registrationForm) {
        registrationService.registerUser(registrationForm.getUserName(), "password", registrationForm.getEmailAddress());
        model.addAttribute("newUserRegistered", Boolean.TRUE);
        model.addAttribute("registrationForm", registrationForm);
        return  "registration";
    }
}
