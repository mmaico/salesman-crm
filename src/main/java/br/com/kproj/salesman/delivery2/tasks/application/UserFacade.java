package br.com.kproj.salesman.delivery2.tasks.application;


import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.Subscribe;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.User;

public interface UserFacade {

    void register(Subscribe subscribe);

    void unregister(Subscribe subscribe);

    void changeStatus(Task task, User user);

}
