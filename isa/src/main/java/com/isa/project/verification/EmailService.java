package com.isa.project.verification;

import com.isa.project.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@EnableAsync
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendNotificaitionAsync(AppUser appUser, String token) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(appUser.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Primer slanja emaila pomoću asinhronog Spring taska");
        //mail.setText("Pozdrav " + appUser.getName() + ",\n\nhvala što pratiš ISA.");
        mail.setText("http://localhost:8080/users/activate/" + token);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }
}
