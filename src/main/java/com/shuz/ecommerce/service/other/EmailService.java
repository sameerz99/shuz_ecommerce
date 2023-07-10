package com.shuz.ecommerce.service.other;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String toEmail, String subject, String userMsg){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(userMsg);
            helper.setFrom("sameerlamaz99@gmail.com");
            javaMailSender.send(message);

        }catch (Exception e){
            System.out.println("message not sent");
        }


    }
}
