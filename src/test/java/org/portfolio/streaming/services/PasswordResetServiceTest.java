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
import org.portfolio.streaming.configs.customgrant.PasswordEncoderConfig;
import org.portfolio.streaming.entities.PasswordReset;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.factories.UserFactory;
import org.portfolio.streaming.repositories.PasswordResetTokenRepository;
import org.portfolio.streaming.repositories.UserRepository;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.portfolio.streaming.services.exceptions.TokenExpiredException;
import org.portfolio.streaming.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class PasswordResetServiceTest {

    @InjectMocks
    private PasswordResetService passwordResetService;
    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Mock
    private MailService mailService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoderConfig passwordEncoderConfig;
    private String validMail;
    private String invalidMail;
    private String validToken;
    private String invalidToken;
    @Autowired
    private RandomStringGenerator stringGenerator;
    private Long validId;
    @Mock
    private PasswordEncoder encoder;
    private User user;
    private PasswordReset validPasswordReset;
    private PasswordReset expiredTokenReset;
    @BeforeEach
    public void setUp () {

        validMail = UserFactory.getDefaultUser().getEmail();
        invalidMail = stringGenerator.generateRandomString(10L);
        validToken = stringGenerator.generateRandomString(30L);
        invalidToken = validToken + "1234440krd";
        validId = 1L;
        user = UserFactory.getDefaultUser();
        validPasswordReset = new PasswordReset(validToken, user, LocalDateTime.now().plusMinutes(2L));
        expiredTokenReset = new PasswordReset(validToken, user, LocalDateTime.now().minusMinutes(10L));


        Mockito.when(userRepository.findByEmail(validMail)).thenReturn(Optional.of(user));
        Mockito.doThrow(ResourceNotFoundException.class).when(userRepository).findByEmail(invalidMail);
        Mockito.when(userRepository.getReferenceById(validId)).thenReturn(user);
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        Mockito.when(passwordResetTokenRepository.save(ArgumentMatchers.any(PasswordReset.class))).thenReturn(validPasswordReset);
        Mockito.doNothing().when(mailService).sendPasswordResetToken(validMail, validToken);
        Mockito.when(passwordResetTokenRepository.searchUserByResetToken(validToken)).thenReturn(Optional.of(user));
        Mockito.when(passwordResetTokenRepository.findByToken(validToken)).thenReturn(validPasswordReset);
        Mockito.when(encoder.encode(ArgumentMatchers.anyString())).thenReturn("encoded-password");
        Mockito.when(passwordEncoderConfig.encoder()).thenReturn(encoder);
        ReflectionTestUtils.setField(passwordResetService, "tokenDuration", 3600L);

    }


    @Test
    public void whenEMailIsValidInitiatePasswordResetWithSuccess () {

        Assertions.assertDoesNotThrow(() -> {

            passwordResetService.initiatePasswordReset(validMail);
        });

    }

    @Test
    public void whenEmailIsInvalidThrowResourceNotFoundException () {


        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            passwordResetService.initiatePasswordReset(invalidMail);

        });


    }

    @Test
    public void whenTokenIsValidThenDoNothing () {


        Assertions.assertDoesNotThrow(() -> {

            passwordResetService.resetPassword(validToken, user.getPassword());


        });

        Mockito.verify(passwordEncoderConfig, Mockito.times(1)).encoder();
        Mockito.verify(encoder, Mockito.times(1)).encode(ArgumentMatchers.anyString());


    }

    @Test
    public void whenTokenIsInvalidThenThrowResourceNotFoundException () {


        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

        passwordResetService.resetPassword(invalidToken, user.getPassword());

        });


    }

    @Test
    public void whenTokenIsExpiredThenThrowTokenExpiredException () {

        Mockito.when(passwordResetTokenRepository.findByToken(validToken)).thenReturn(expiredTokenReset);

        Assertions.assertThrows(TokenExpiredException.class, () -> {

            passwordResetService.resetPassword(validToken, user.getPassword());

        });



    }






}
