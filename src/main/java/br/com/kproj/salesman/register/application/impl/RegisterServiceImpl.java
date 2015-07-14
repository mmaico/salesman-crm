package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.Client;
import br.com.kproj.salesman.infrastructure.entity.Product;
import br.com.kproj.salesman.infrastructure.entity.Project;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.repository.ProjectRepository;
import br.com.kproj.salesman.infrastructure.repository.VendorRepository;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.application.RegisterService;
import br.com.kproj.salesman.register.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final ClientService clientService;
    private final ProjectRepository projectRepository;
    private final VendorRepository vendorRepository;
    private final UserService userService;

    @Autowired
    public RegisterServiceImpl(final ClientService clientService
            , final ProjectRepository projectRepository
            , final VendorRepository vendorRepository
            , final UserService userService
    ) {
        this.clientService = clientService;
        this.projectRepository = projectRepository;
        this.vendorRepository = vendorRepository;
        this.userService = userService;
    }

    @Override
    public Client register(Client client) {
        userService.save(client.getUser());
        return clientService.save(client);
    }

    @Override
    public Product register(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public User register(User user) {
        return userService.save(user);
    }


}
