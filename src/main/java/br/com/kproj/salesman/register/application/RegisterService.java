package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.*;

public interface RegisterService {

    Client register(Client client);

    Product register(Project product);

    User register(User user);

}
