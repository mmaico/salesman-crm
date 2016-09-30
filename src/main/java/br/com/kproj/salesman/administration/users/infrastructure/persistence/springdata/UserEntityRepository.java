package br.com.kproj.salesman.administration.users.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userEntityRepositoryUsersModule")
public interface UserEntityRepository extends BaseRepositoryLegacy<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity AS u WHERE u.login = :login AND u.password = :password")
    Optional<UserEntity> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("SELECT u FROM UserEntity AS u WHERE u.login = :login")
    Optional<UserEntity> findByLogin(@Param("login") String login);
}
