package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.infrastructure.entity.Product;
import br.com.kproj.salesman.infrastructure.entity.Project;
import br.com.kproj.salesman.infrastructure.entity.Vendor;

public interface RegisterService {

    Client register(Client client);

    Product register(Project product);

    Vendor register(Vendor client);

}
