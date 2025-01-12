package org.portfolio.streaming.factories;

import org.portfolio.streaming.entities.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleFactory {

    public static Set<Role> getRoleUser () {

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER", 1L));

        return roles;

    }


    public static Set<Role> getRoleAdmin () {

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_ADMIN", 2L));

        return roles;

    }

    public static Set<Role> getRoleUserAdmin () {

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_ADMIN", 2L));
        roles.add(new Role("ROLE_USER", 1L));

        return roles;

    }


}
