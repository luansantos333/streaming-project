package org.portfolio.streaming.repositories.projections;

public interface UserDetailsProjection {

    String getUsername();

    String getPassword();

    Long getRoleId();
    String getAuthority();



}
