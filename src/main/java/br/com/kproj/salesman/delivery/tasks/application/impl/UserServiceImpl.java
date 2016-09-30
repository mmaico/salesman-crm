package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.UserFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.ChangeStatus;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.SubscribeTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.SubscribeValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userFacadeDeliveryModule")
public class UserServiceImpl implements UserFacade {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private SubscribeValidator validator;

    @Override
    public void register(SubscribeTask subscribe) {
        validator.checkRules(subscribe);
        User user = subscribe.getUser();
        Task task = subscribe.getTask();

        user.subscribes().the(task);
    }

    @Override
    public void unregister(SubscribeTask subscribe) {
        validator.checkRules(subscribe);
        User user = subscribe.getUser();
        Task task = subscribe.getTask();

        user.unsubscribes().the(task);
    }

    @Override
    public void changeStatus(ChangeStatus changeStatus) {
        repository.changeStatus(changeStatus);
    }
}
