package br.com.kproj.salesman.register.infrastructure.repository;


import br.com.kproj.salesman.register.infrastructure.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
