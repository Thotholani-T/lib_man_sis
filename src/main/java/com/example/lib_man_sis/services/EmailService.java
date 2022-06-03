package com.example.lib_man_sis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendOverdueEmail(String receiverEmail) {
        String message = "Hello. Your book is overdue.\n Kindly return it to avoid incurring charges. Thank you. \n Regards,\n The Central Public Library";
        String subject = "Book is overdue! Please return";
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("emailsender725@gmail.com");
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        this.mailSender.send(mailMessage);
    }
}
