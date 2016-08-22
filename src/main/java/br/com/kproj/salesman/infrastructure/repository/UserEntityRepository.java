package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserEntityRepository extends BaseRepositoryLegacy<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity AS u WHERE u.login = :login AND u.password = :password")
    Optional<UserEntity> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("SELECT u FROM UserEntity AS u WHERE u.login = :login")
    Optional<UserEntity> findByLogin(@Param("login") String login);
}
