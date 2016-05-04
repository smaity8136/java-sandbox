package za.co.seedollar.java.email.templating.service.impl;

import org.springframework.stereotype.Service;
import za.co.seedollar.java.email.templating.domain.User;
import za.co.seedollar.java.email.templating.service.RegistrationService;

/**
 * Created by seedollar on 5/4/16.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void registerUser(String userName, String password, String emailAddress) {

        User newUser = new User(userName, emailAddress, password);
        System.out.println("newUser = " + newUser);



    }
}
