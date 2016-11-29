package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.SubscriberFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.*;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.SubscriberRulesDescription.*;
import static br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task.task;
import static br.com.kproj.salesman.delivery.tasks.domain.model.user.User.user;

@Service("subscriberFacadeDeliveryModule")
public class SubscriberServiceImpl extends BaseModelServiceImpl<Subscriber> implements SubscriberFacade {

    private SubscriberRepository repository;
    private SubscribeValidator validator;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository repository, SubscribeValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Subscriber subscribe(SubscribeTask subscribeTask) {
        User user = subscribeTask.getUser();
        Task task = subscribeTask.getTask();

        Subscriber subscriber = SubscriberBuilder.createSubscriber()
                .withTask(task).withUser(user).build();

        validator.checkRules(subscriber, ignoreRules(ruleSubscriber()));

        return user.subscribe().the(task);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        validator.checkRules(subscriber, ignoreRules(ruleTask(), ruleUser()));

        user().unsubscribe(subscriber).of(task());
    }

    @Override
    public Iterable<Subscriber> findAll(Task task, Pageable pager) {
        return repository.findAll(task, pager);
    }

    @Override
    public BaseRepository<Subscriber, Long> getRepository() {
        return repository;
    }
}
