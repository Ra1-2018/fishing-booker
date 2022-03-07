package com.isa.project.verification;

import com.isa.project.model.AdditionalService;
import com.isa.project.model.AppUser;
import com.isa.project.model.RegistrationRequest;
import com.isa.project.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
@EnableAsync
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendNotificationAsync(AppUser appUser, String token) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(appUser.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Registration Confirmation");
        mail.setText("http://localhost:8080/users/activate/" + token);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendNotificationOfApprovedRegistrationRequest(RegistrationRequest request) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(request.getUser().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("Registration Confirmation");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendNotificationOfDeclinedRegistrationRequest(RegistrationRequest request) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(request.getUser().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("Your Registration Has Been Declined");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendNotificaitionAdminReg(AppUser appUser, String password) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(appUser.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("You have been successfully registered. Your temporarly password is: "+password+". Please change it on your first login.");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendReservationNotification(Reservation reservation) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(reservation.getClient().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Reservation confirmation");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        var message = MessageFormat.format("Your Reservation has been confirmed.\n" +
                "Reservation details:\n" +
                "Service: {0} \n" +
                "Start time: {1} \n" +
                "Number of days: {2} \n" +
                "Number of people: {3} \n" +
                "Total price: {4} \n", reservation.getService().getName(), dateFormat.format(reservation.getReservationStartDateAndTime()), reservation.getDurationInDays(), reservation.getNumberOfPeople(), reservation.getPrice());
        if(reservation.getAdditionalServices().size() > 0) {
            message += "Additional services: ";
            boolean first = true;
            for(AdditionalService additionalService : reservation.getAdditionalServices()) {
                if(first) {
                    first = false;
                }
                else {
                    message += ", ";
                }
                message += additionalService.getName();
            }
        }
        mail.setText(message);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }
}
