package org.portfolio.streaming.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.portfolio.streaming.config.TestConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class PasswordResetServiceTest {
}
