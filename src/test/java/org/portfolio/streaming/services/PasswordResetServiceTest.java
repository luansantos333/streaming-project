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
import org.portfolio.streaming.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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
    @MockBean
    private PasswordEncoderConfig passwordEncoderConfig;
    private String validMail;
    private String invalidMail;
    private String validToken;
    private String invalidToken;
    @Autowired
    private RandomStringGenerator stringGenerator;
    private Long validId;
    @BeforeEach
    public void setUp () {

        validMail = UserFactory.getDefaultUser().getEmail();
        invalidMail = stringGenerator.generateRandomString(10L);
        validToken = stringGenerator.generateRandomString(30L);
        invalidToken = validToken + "1234440krd";
        validId = 1L;

        Mockito.when(userRepository.findByEmail(validMail)).thenReturn(Optional.of(UserFactory.getDefaultUser()));
        Mockito.doThrow(ResourceNotFoundException.class).when(userRepository).findByEmail(invalidMail);
        Mockito.when(userRepository.getReferenceById(validId)).thenReturn(UserFactory.getDefaultUser());
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(UserFactory.getDefaultUser());
        Mockito.when(passwordResetTokenRepository.save(ArgumentMatchers.any(PasswordReset.class))).thenReturn(new PasswordReset());
        Mockito.doNothing().when(mailService).sendPasswordResetToken(validMail, validToken);
        Mockito.when(passwordResetTokenRepository.searchUserByResetToken(validToken)).thenReturn(Optional.of(UserFactory.getDefaultUser()));
        Mockito.when(passwordResetTokenRepository.findByToken(validToken)).thenReturn(new PasswordReset(validToken, UserFactory.getDefaultUser(), LocalDateTime.now().plusMinutes(2L)));
        Mockito.when(passwordEncoderConfig.encoder().encode((CharSequence) UserFactory.getDefaultUser().getPassword())).thenReturn("encoded-password");

    }


    @Test
    public void whenEMailIsValidInitiatePasswordResetWithSuccess () {

        ReflectionTestUtils.setField(passwordResetService, "tokenDuration", 3600L);
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

        ReflectionTestUtils.setField(passwordResetService, "tokenDuration", 3600L);
        Assertions.assertDoesNotThrow(() -> {

            passwordResetService.resetPassword(validToken, UserFactory.getDefaultUser().getPassword());

        });


    }






}
