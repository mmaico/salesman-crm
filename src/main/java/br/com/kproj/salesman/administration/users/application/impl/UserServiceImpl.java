package br.com.kproj.salesman.administration.users.application.impl;

import br.com.kproj.salesman.administration.users.application.UserFacade;
import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserRepository;
import br.com.kproj.salesman.administration.users.domain.model.user.UserValidator;
import br.com.kproj.salesman.administration.users.domain.service.UserEventService;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseModelServiceImpl<User> implements UserFacade {

    @Autowired
    private UserRepository repository;

    @Autowired
    @Qualifier("userDomainValidator")
    private UserValidator validator;

    @Autowired
    private UserEventService eventService;


    @Override
    public Optional<User> register(User user) {

        validator.checkRules(user);
        Optional<User> userSaved = repository.save(user);

        eventService.userChanged(userSaved.get());
        return userSaved;
    }

    @Override
    public BaseRepository<User, Long> getRepository() {
        return repository;
    }
}
