package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends BaseRepository<User, Long> {

    @Query("SELECT u FROM User AS u WHERE u.login = :login AND u.password = :password")
    public User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
