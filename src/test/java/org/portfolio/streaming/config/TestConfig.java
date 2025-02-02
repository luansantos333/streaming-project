package org.portfolio.streaming.config;

import org.portfolio.streaming.utils.RandomStringGenerator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {


    @Bean
    RandomStringGenerator randomStringGenerator () {

        return new RandomStringGenerator();

    }


}
