package br.com.kproj.salesman.delivery.tasks.domain.model.subscribe;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;

public interface SubscribeValidator {

    void checkRules(Subscriber subscriber);

    void checkRules(Subscriber subscribe, IgnoreRules ignoreRules);


}
