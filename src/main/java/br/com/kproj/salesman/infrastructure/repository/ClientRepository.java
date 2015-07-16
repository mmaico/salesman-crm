package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.infrastructure.repository.custom.ClientRepositoryCustom;

public interface ClientRepository extends BaseRepository<Client, Long>, ClientRepositoryCustom {
}
