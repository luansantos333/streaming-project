package org.portfolio.streaming.services;

import org.portfolio.streaming.dtos.UserDTO;
import org.portfolio.streaming.dtos.UserMinDTO;
import org.portfolio.streaming.entities.Role;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.repositories.UserRepository;
import org.portfolio.streaming.repositories.projections.UserDetailsProjection;
import org.portfolio.streaming.services.exceptions.DatabaseException;
import org.portfolio.streaming.services.exceptions.ForbiddenException;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.portfolio.streaming.utils.UserUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final UserUtil userUtil;


    public UserService(UserRepository userRepo, UserUtil userUtil) {
        this.userRepo = userRepo;
        this.userUtil = userUtil;
    }

    public UserDTO findByEmail(String username) {

        User byEmail = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Couldn't found any user with the username provided"));
        return new UserDTO(byEmail);
    }


    /*
    @Transactional
    public UserMinDTO addNewUser (UserDTO dto) {

        repository.save()



    }
    */

    @Transactional
    public void deleteUser(Long id) {

        if (!userRepo.existsById(id)) {

            throw new IllegalArgumentException("No user found with this id");
        }

        User user = userRepo.findById(id).get();

        boolean isOwnerOrAdmin = userIsOwnerOrAdmin(user);

        if (!isOwnerOrAdmin) {


            throw new ForbiddenException("You can't remove another user");

        }

        try {

            userRepo.deleteById(user.getId());

        } catch (DataIntegrityViolationException e) {

            throw new DatabaseException("Couldn't delete user because it has dependencies with other entities");
        }


    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> userRolesByUsername = userRepo.findUserRolesByUsername(username);

        if (userRolesByUsername.size() == 0) {

            throw new UsernameNotFoundException("Couldn't found any user with the username provided");

        }

        User user = new User();

        user.setEmail(userRolesByUsername.get(0).getUsername());
        user.setPassword(userRolesByUsername.get(0).getPassword());

        for (UserDetailsProjection p : userRolesByUsername) {

            user.addRole(new Role(p.getAuthority(), p.getRoleId()));

        }

        return user;

    }

    protected User authenticated() {
        try {
            String username = userUtil.getLoggedUsername();
            return userRepo.findByEmail(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }


    @Transactional
    public UserMinDTO getMe() {
        User authenticated = authenticated();
        return new UserMinDTO(authenticated);
    }

    private boolean userIsOwnerOrAdmin(User user) {

        if (!authenticated().getId().equals(user.getId()) && authenticated().getAuthorities().stream().noneMatch(x -> x.getAuthority().equals("ROLE_ADMIN"))) {

            return false;

        }

        return true;

    }

}
