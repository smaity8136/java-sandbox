package za.co.seedollar.java.email.templating.configuration;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by seedollar on 5/5/16.
 */
@Configuration
public class EmailConfiguration {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private int port;

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password}")
    private String password;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        return javaMailSender;
    }

    @Bean
    public GreenMail greenMail() {
        GreenMail greenMail = new GreenMail(ServerSetupTest.ALL);
        greenMail.setUser("oliver@email.com", username, password);
        greenMail.start();
        return greenMail;
    }
}
