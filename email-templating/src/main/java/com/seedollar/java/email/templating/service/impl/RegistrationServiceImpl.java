package com.seedollar.java.email.templating.service.impl;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import com.seedollar.java.email.templating.domain.User;
import com.seedollar.java.email.templating.service.RegistrationService;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by seedollar on 5/4/16.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void registerUser(String userName, String password, String emailAddress) {

        User newUser = new User(userName, emailAddress, password);
        System.out.println("newUser = " + newUser);

        sendEmail(newUser);
    }

    private void sendEmail(User newUser) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(newUser.getEmailAddress());
                message.setFrom("oliver@email.com"); // could be parameterized...
                message.setSubject("Welcome");
                Map model = new HashMap<>();
                model.put("user", newUser);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "email/welcome-email.vm", "UTF-8", model);
                message.setText(text, true);
            }
        };
        javaMailSender.send(preparator);
    }
}
