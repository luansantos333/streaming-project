package org.portfolio.streaming.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class RandomStringGenerator {

    private static final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


    public String generateRandomString (Long length) {

        String randomString = new String();

        Random rng = new SecureRandom();
        for (int i = 0; i< length; i++) {

            randomString += chars[rng.nextInt(0, 25)];

        }

        return randomString;

    }

    public char[] getChars() {
        return chars;
    }
}
