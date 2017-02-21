package br.com.kproj.salesman.administration.users.application.impl;

import br.com.kproj.salesman.administration.users.application.UserFacade;
import br.com.kproj.salesman.administration.users.domain.model.administrator.Administrator;
import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserEventHandler;
import br.com.kproj.salesman.administration.users.domain.model.user.UserRepository;
import br.com.kproj.salesman.administration.users.domain.model.user.UserValidator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.administration.users.domain.model.administrator.Administrator.administrator;

@Service
public class UserServiceImpl extends BaseModelServiceImpl<User> implements UserFacade {

    @Autowired
    private UserRepository repository;

    @Autowired
    @Qualifier("userDomainValidator")
    private UserValidator validator;

    @Autowired
    private UserEventHandler eventHandler;


    @Override
    public Optional<User> register(User user) {
        validator.checkRules(user);

        Optional<User> userSaved = administrator().register(user);
        eventHandler.userChanged(userSaved.get());

        return userSaved;
    }

    @Override
    public User update(User user) {
        validator.checkRules(user);

        User userUpdated = administrator().update(user);
        eventHandler.userChanged(user);

        return userUpdated;
    }

    @Override
    public BaseRepository<User, Long> getRepository() {
        return repository;
    }


}
