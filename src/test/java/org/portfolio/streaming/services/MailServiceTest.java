package org.portfolio.streaming.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.portfolio.streaming.config.TestConfig;
import org.portfolio.streaming.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class MailServiceTest {

    @InjectMocks
    private MailService mailService;
    @Mock
    private JavaMailSender mailSender;
    @Autowired
    private RandomStringGenerator randomStringGenerator;
    private String validToken;
    private String email;

    @BeforeEach
    public void setUp () {

        validToken = randomStringGenerator.generateRandomString(30L);;
        email = "someemail.com";

        Mockito.doNothing().when(mailSender).send(ArgumentMatchers.any(SimpleMailMessage.class));


    }


    @Test
    public void whenSendingAValidSimpleMessageEmailThenDoNothing () {

        Assertions.assertDoesNotThrow(() -> {
            mailService.sendPasswordResetToken(email,validToken);

        });


    }










    }
