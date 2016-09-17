package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.UserFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.ChangeStatus;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.Subscribe;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.SubscribeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserFacade {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private SubscribeValidator validator;

    @Override
    public void register(Subscribe subscribe) {
        validator.checkRules(subscribe);
        repository.register(subscribe);
    }

    @Override
    public void unregister(Subscribe subscribe) {
        validator.checkRules(subscribe);
        repository.unregister(subscribe);
    }

    @Override
    public void changeStatus(ChangeStatus changeStatus) {
        repository.changeStatus(changeStatus);
    }
}
