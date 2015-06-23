package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.*;
import br.com.kproj.salesman.infrastructure.repository.ClientRepository;
import br.com.kproj.salesman.infrastructure.repository.ProjectRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;

    @Autowired
    public RegisterServiceImpl(
            ClientRepository clientRepository,
            ProjectRepository projectRepository,
            VendorRepository vendorRepository,
            UserRepository userRepository
    ) {
        this.clientRepository = clientRepository;
        this.projectRepository = projectRepository;
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Client register(Client client) {
        final User user = userRepository.save(client.getUser());
        return clientRepository.save(new Client(client.getName(), user));
    }

    @Override
    public Product register(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Vendor register(Vendor vendor) {
        final User user = userRepository.save(vendor.getUser());
        return vendorRepository.save(new Vendor(vendor.getName(), user));
    }
}
