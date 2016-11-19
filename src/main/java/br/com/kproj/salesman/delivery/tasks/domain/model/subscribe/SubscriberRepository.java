package br.com.kproj.salesman.delivery.tasks.domain.model.subscribe;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriberRepository extends BaseRepository<Subscriber, Long> {

    void unsubscribe(Subscriber subscriber);

    Subscriber subscribe(SubscribeTask subscribeTask);

    Page<Subscriber> findAll(Task task, Pageable page);


}
