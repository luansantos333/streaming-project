package org.portfolio.streaming.config;

import org.portfolio.streaming.utils.RandomStringGenerator;
import org.portfolio.streaming.utils.TokenUtil;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    TokenUtil tokenUtil () {
        return new TokenUtil();

    }

    @Bean
    RandomStringGenerator randomStringGenerator () {

        return new RandomStringGenerator();

    }


}
