package br.com.kproj.salesman.delivery.tasks.domain.services;

import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;

@FunctionalInterface
public interface SubscriberSubscribeTask {

    Subscriber the(Task task);
}
