package org.portfolio.streaming.factories;

import org.portfolio.streaming.dtos.UserDTO;
import org.portfolio.streaming.dtos.UserMinDTO;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFactory {

    @Autowired
    static RandomStringGenerator generator;

    public static User getDefaultUser () {


        return new User(1L, "TEST", "test@test.com", "TOTALLY SAFE PASSWORD!!");


    }


    public static UserDTO getDefaultUserDTO () {


        User defaultUser = getDefaultUser();

        return new UserDTO(defaultUser);

    }

    public static UserMinDTO getDefaultUserMinDTO () {

        User defaultUser = getDefaultUser();

        return new UserMinDTO(defaultUser);

    }



}
