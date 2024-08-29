package com.ideas2it.hirezy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class for sending emails using Spring's `JavaMailSender`.
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Sends an email with the specified recipient, subject, and body.
     *
     * @param toEmail the recipient's email address
     * @param subject the subject of the email
     * @param body the body of the email
     */
    public void sendEmail(String toEmail,String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
