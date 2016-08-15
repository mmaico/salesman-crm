package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends BaseRepositoryLegacy<User, Long> {

    @Query("SELECT u FROM User AS u WHERE u.login = :login AND u.password = :password")
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("SELECT u FROM User AS u WHERE u.login = :login")
    Optional<User> findByLogin(@Param("login") String login);
}
