package br.com.kproj.salesman.delivery.tasks.domain.model.user;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.services.UserSusbribesTask;
import br.com.kproj.salesman.delivery.tasks.domain.services.UserUnSusbribesTask;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private TaskRepository repository;

    public User(Long id) {
        this.id = id;
    }

    public User() {
        AutowireHelper.autowire(this);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserSusbribesTask subscribes() {
        return task -> repository.register(SubscribeTask.createSubscribe(this, task));
    }

    public UserUnSusbribesTask unsubscribes() {
        return task -> repository.unregister(SubscribeTask.createSubscribe(this, task));
    }

}
