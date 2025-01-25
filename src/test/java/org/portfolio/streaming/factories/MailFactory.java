package org.portfolio.streaming.factories;

import org.portfolio.streaming.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
@Import(TestConfig.class)
public class MailFactory {

    @Autowired


    public static SimpleMailMessage createMailMessage (String sender, String destination, String token, String subject) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(destination);
        message.setSubject(subject);
        message.setText(token);

        return message;

    }


}
