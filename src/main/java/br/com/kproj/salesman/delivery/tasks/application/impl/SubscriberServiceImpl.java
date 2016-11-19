package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.SubscriberFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscriberRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscribeTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscribeValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task.task;
import static br.com.kproj.salesman.delivery.tasks.domain.model.user.User.user;

@Service("subscriberFacadeDeliveryModule")
public class SubscriberServiceImpl extends BaseModelServiceImpl<Subscriber> implements SubscriberFacade {

    @Autowired
    private SubscriberRepository repository;

    @Autowired
    private SubscribeValidator validator;

    @Override
    public Subscriber subscribe(SubscribeTask subscribe) {
        validator.checkRules(subscribe);

        User user = subscribe.getUser();
        Task task = subscribe.getTask();

        return user.subscribe().the(task);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        validator.checkRules(subscriber);

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
