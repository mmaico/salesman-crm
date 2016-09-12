package br.com.kproj.salesman.delivery2.tasks.application;


import br.com.kproj.salesman.delivery2.tasks.domain.model.user.ChangeStatus;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.Subscribe;

public interface UserFacade {

    void register(Subscribe subscribe);

    void unregister(Subscribe subscribe);

    void changeStatus(ChangeStatus status);

}
