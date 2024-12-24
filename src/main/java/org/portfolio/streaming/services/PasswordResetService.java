package org.portfolio.streaming.services;

import org.portfolio.streaming.entities.PasswordReset;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.repositories.PasswordResetTokenRepository;
import org.portfolio.streaming.repositories.UserRepository;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.portfolio.streaming.services.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final MailService mailService;
    private final UserRepository userRepo;
    @Value("${spring.mail.token.expiration}")
    private Long tokenDuration;

    public PasswordResetService(PasswordResetTokenRepository passwordResetTokenRepository, MailService mailService, UserRepository userRepo) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.mailService = mailService;
        this.userRepo = userRepo;
    }

    @Transactional
    public void initiatePasswordReset (String email) {

        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("No user found with this email"));
        String token = generatePassWordToken();
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setUser(user);
        passwordReset.setToken(token);
        passwordReset.setExpiryDate(LocalDateTime.now().plusMinutes(tokenDuration));
        passwordResetTokenRepository.save(passwordReset);
        mailService.sendPasswordResetToken(user.getEmail(), token);
    }

    private String generatePassWordToken () {

        return UUID.randomUUID().toString();

    }

    @Transactional
    public void resetPassword (String token, String password) {
        User user = userRepo.getReferenceById(passwordResetTokenRepository.searchUserByResetToken(token).orElseThrow(() -> new ResourceNotFoundException("Invalid token")).getId());
        if (LocalDateTime.now().isAfter(passwordResetTokenRepository.findByToken(token).getExpiryDate())) {
            throw new TokenExpiredException("The token you provide is expired");
        }
        user.setPassword(password);
        userRepo.save(user);
    }
}
