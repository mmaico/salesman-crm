package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.repository.VendorRepository;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.application.RegisterService;
import br.com.kproj.salesman.register.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class RegisterServiceImpl implements RegisterService {

    private  ClientService clientService;
    private  VendorRepository vendorRepository;
    private  UserService userService;

    @Autowired
    public RegisterServiceImpl(final ClientService clientService

            , final VendorRepository vendorRepository
            , final UserService userService
    ) {
        this.clientService = clientService;
        this.vendorRepository = vendorRepository;
        this.userService = userService;
    }

    @Override
    public Person register(Person client) {
        return clientService.save(client);
    }

    @Override
    public User register(User user) {
        return userService.save(user);
    }


}
