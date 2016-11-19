package br.com.kproj.salesman.delivery.tasks.domain.model.user;

import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscriberRepository;
import br.com.kproj.salesman.delivery.tasks.domain.services.SubscriberSubscribeTask;
import br.com.kproj.salesman.delivery.tasks.domain.services.SubscriberUnsubscribeTask;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscribeTask.createSubscriber;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private SubscriberRepository repository;

    public User(Long id) {
        this();
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

    public SubscriberSubscribeTask subscribe() {
        return task -> repository.subscribe(createSubscriber(this.id, task.getId()));
    }

    public SubscriberUnsubscribeTask unsubscribe(Subscriber subscriber) {
        return task -> repository.unsubscribe(subscriber);
    }

    public static User user() {
        return new User();
    }



}
