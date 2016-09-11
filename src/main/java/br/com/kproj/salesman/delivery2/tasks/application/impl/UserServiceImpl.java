package br.com.kproj.salesman.delivery2.tasks.application.impl;

import br.com.kproj.salesman.delivery2.tasks.application.UserFacade;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.Subscribe;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserFacade {

    @Autowired
    private TaskRepository repository;

    @Override
    public void register(Subscribe subscribe) {

    }

    @Override
    public void unregister(Subscribe subscribe) {

    }

    @Override
    public void changeStatus(Task task, User user) {

    }
}
