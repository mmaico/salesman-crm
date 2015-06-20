package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.register.infrastructure.entity.Client;
import br.com.kproj.salesman.register.infrastructure.entity.Product;
import br.com.kproj.salesman.register.infrastructure.entity.Project;
import br.com.kproj.salesman.register.infrastructure.entity.Vendor;

public interface RegisterService {

    Client register(Client client);

    Product register(Project product);

    Vendor register(Vendor client);

}
