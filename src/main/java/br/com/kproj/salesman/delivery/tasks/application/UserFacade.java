package br.com.kproj.salesman.delivery.tasks.application;


import br.com.kproj.salesman.delivery.tasks.domain.model.user.ChangeStatus;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.SubscribeTask;

public interface UserFacade {

    void register(SubscribeTask subscribe);

    void unregister(SubscribeTask subscribe);

    void changeStatus(ChangeStatus status);

}
