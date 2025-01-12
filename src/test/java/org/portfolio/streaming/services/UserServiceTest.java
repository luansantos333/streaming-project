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
import org.portfolio.streaming.dtos.UserDTO;
import org.portfolio.streaming.dtos.UserMinDTO;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.factories.ReviewFactory;
import org.portfolio.streaming.factories.RoleFactory;
import org.portfolio.streaming.factories.UserFactory;
import org.portfolio.streaming.repositories.UserRepository;
import org.portfolio.streaming.services.exceptions.DataExistentException;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.portfolio.streaming.utils.RandomStringGenerator;
import org.portfolio.streaming.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserUtil userUtil;
    @Mock
   private PasswordEncoderConfig encoderConfig;
    @Mock
    private UserRepository userRepository;
    private UserDTO userDTO;
    private UserDTO newUserDTO;
    private UserMinDTO userMinDTO;
    private User user;
    private String validUsername;
    private String invalidUsername;
    @Autowired
    private RandomStringGenerator generator;
    private Long validUserId;
    private Long nonExistentUserId;

    @BeforeEach
    public void setUp () {

        userDTO = UserFactory.getDefaultUserDTO();
        userMinDTO = UserFactory.getDefaultUserMinDTO();
        user = UserFactory.getDefaultUser();
        newUserDTO = new UserDTO(2L, generator.generateRandomString(10L), generator.generateRandomString(8L), generator.generateRandomString(20L));
        validUsername = userDTO.getEmail();
        invalidUsername = generator.generateRandomString(10L);
        validUserId = 1L;
        nonExistentUserId = 2L;

        Mockito.when(userRepository.findByEmail(validUsername)).thenReturn(Optional.ofNullable(user));
        Mockito.doThrow(ResourceNotFoundException.class).when(userRepository).findByEmail(invalidUsername);
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        Mockito.when(encoderConfig.encoder()).thenReturn(new BCryptPasswordEncoder());
        Mockito.when(userRepository.existsById(validUserId)).thenReturn(true);
        Mockito.when(userRepository.existsById(nonExistentUserId)).thenReturn(false);
        Mockito.when(userUtil.getLoggedUsername()).thenReturn(user.getUsername());
        Mockito.when(userRepository.findById(validUserId)).thenReturn(Optional.ofNullable(user));


    }

    @Test
    public void whenUsernameIsValidThenReturnUserDTO () {

        UserDTO byEmail = userService.findByEmail(validUsername);
        Assertions.assertEquals(userDTO.getEmail(), byEmail.getEmail());
        Assertions.assertEquals(userDTO.getId(), byEmail.getId());
        Assertions.assertEquals(userDTO.getName(), byEmail.getName());
        Assertions.assertEquals(userDTO.getPassword(), byEmail.getPassword());



    }
    @Test
    public void whenInvalidUsernameThenThrowResourceNotFoundException () {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {


            userService.findByEmail(invalidUsername);

        });




    }

    @Test
    public void whenValidUserDTOSavedThenReturnUserMinDTO () {

        User usuarioEspecifico = new User(newUserDTO.getId(), newUserDTO.getName(), newUserDTO.getEmail(), newUserDTO.getPassword());
        Mockito.doReturn(usuarioEspecifico).when(userRepository).save(ArgumentMatchers.any(User.class));
        UserMinDTO save = userService.addNewUser(newUserDTO);
        Assertions.assertEquals(newUserDTO.getName(), save.getName());
        Assertions.assertEquals(newUserDTO.getEmail(), save.getEmail());

    }

    @Test
    public void whenUsernameIsPresentThrowDataExistentException () {
        
        Assertions.assertThrows(DataExistentException.class, () -> {
            userService.addNewUser(userDTO);
        });

    }

    @Test
    public void whenUserIdIsValidGrantAdminPrivileges () {

        UserMinDTO adminPrivileges = userService.grantAdminPrivileges(validUserId);
        Assertions.assertEquals(user.getEmail(), adminPrivileges.getEmail() );
        Assertions.assertEquals(user.getName(), adminPrivileges.getName() );

    }

    @Test
    public void whenUserIdIsNonExistentOnGrantAdminThenThrowResourceNotFoundException () {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            userService.grantAdminPrivileges(nonExistentUserId);

        });

    }

    @Test
    public void whenDeleteWithValidUserIdThenDoNothing () {
        UserService service = Mockito.mock(UserService.class);
        service.deleteUser(validUserId);
        Mockito.verify(service, Mockito.times(1)).deleteUser(validUserId);

    }

    @Test
    public void whenDeleteWithNonExistentIdThenThrowResourceNotFoundException () {


        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            userService.deleteUser(nonExistentUserId);

        });


    }


}
