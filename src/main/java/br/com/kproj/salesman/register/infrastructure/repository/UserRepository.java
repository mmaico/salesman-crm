package br.com.kproj.salesman.register.infrastructure.repository;

import br.com.kproj.salesman.register.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
