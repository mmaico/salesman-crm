package br.com.kproj.salesman.delivery.tasks.application;


import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscribeTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

public interface SubscriberFacade extends ModelFacade<Subscriber> {

    Subscriber subscribe(SubscribeTask subscribe);

    void unsubscribe(Subscriber subscriber);

    Iterable<Subscriber> findAll(Task task, Pageable pager);

}
