package br.com.kproj.salesman.register.application;


import br.com.kproj.salesman.infrastructure.entity.Client;

public interface ClientService extends ModelService<Client> {

    Client register(Client client);
}
