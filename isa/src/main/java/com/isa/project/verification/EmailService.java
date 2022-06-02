package com.isa.project.verification;

import com.isa.project.model.*;
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
import java.util.Collection;

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
        mail.setText("https://fishing-booker-app.herokuapp.com/users/activate/" + token);
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
    public void sendNotificationOfDeclinedRegistrationRequest(ResponseToRegistrationRequest response) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(response.getAdministrator().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("Your Registration Has Been Declined. Administrator reason for rejection: " + response.getContent());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendNotificationOfApprovedReview(Review review, AppUser user) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("You received review for " + review.getService().getName());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendNotificationOfDeclinedDeletionRequest(ResponseToDeletionRequest response) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(response.getDeletionRequest().getUserEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("Your Deletion Has Been Declined. Administrator reason for rejection: " + response.getContent());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendNotificationOfDeclinedComplaint(ResponseToComplaint response) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(response.getComplaint().getClient().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("Your Complaint Has Been Declined. Administrator reason for rejection: " + response.getContent());
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendNotificationAdminReg(AppUser appUser, String password) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(appUser.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setText("You have been successfully registered. Your temporary password is: "+password+". Please change it on your first login.");
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

    @Async
    public void sendActionNotification(Action action) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();

        Collection<Client> clients = action.getService().getSubscribedClients();

        for (Client client : clients) {
            mail.setTo(client.getEmail());
            mail.setFrom(env.getProperty("spring.mail.username"));
            mail.setSubject("New action for" + action.getService().getName());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            var message = MessageFormat.format("New action has been created.\n" +
                    "Action details:\n" +
                    "Service: {0} \n" +
                    "Start time: {1} \n" +
                    "Number of days: {2} \n" +
                    "Number of people: {3} \n", action.getService().getName(), dateFormat.format(action.getStartTime()), action.getDurationInDays(), action.getMaxNumberOfPeople());
            if(action.getAdditionalServices().size() > 0) {
                message += "Additional services: ";
                boolean first = true;
                for(AdditionalService additionalService : action.getAdditionalServices()) {
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
}
