package br.com.kproj.salesman.delivery.tasks.domain.model.subscribe;


public interface SubscribeValidator {

    void checkRules(SubscribeTask subscribe);

    void checkRules(Subscriber subscriber);


}
