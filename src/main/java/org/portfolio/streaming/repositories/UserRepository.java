package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.repositories.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT user_sys.email AS username, user_sys.password, roles.id AS roleId, roles.authority FROM tb_user AS user_sys INNER JOIN tb_user_role " +
            "ON user_sys.id = tb_user_role.user_id INNER JOIN tb_role AS roles ON tb_user_role.role_id = roles.id WHERE user_sys.email = :username")
    public List<UserDetailsProjection> findUserRolesByUsername(String username);

    @Query
    Optional<User> findByEmail(String email);


}
