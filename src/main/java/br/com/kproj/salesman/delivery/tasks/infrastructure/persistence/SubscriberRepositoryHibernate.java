package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;


import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscribeTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscriberRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.TaskResponsibleRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskResponsibleEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("subscriberRepositoryHibernateDeliveryModule")
public class SubscriberRepositoryHibernate extends BaseRespositoryImpl<Subscriber, TaskResponsibleEntity> implements SubscriberRepository {


    @Autowired
    private TaskResponsibleRepositorySpringData repository;


    @Override
    public BaseRepositoryLegacy<TaskResponsibleEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        repository.delete(subscriber.getId());
    }

    @Override
    public Subscriber subscribe(SubscribeTask subscribeTask) {
        TaskResponsibleEntity responsible = new TaskResponsibleEntity();
        responsible.setTask(new TaskEntity(subscribeTask.getTask().getId()));
        responsible.setUser(new UserEntity(subscribeTask.getUser().getId()));

        return getConverter().convert(repository.save(responsible));
    }

    @Override
    public Page<Subscriber> findAll(Task task, Pageable page) {
        Page<TaskResponsibleEntity> result = repository.findAllByTask(task.getId(), page);

        List<Subscriber> subscribers = result.getContent().stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return new PageImpl<>(subscribers, page, result.getTotalElements());
    }

    @Override
    public Converter<TaskResponsibleEntity, Subscriber> getConverter() {
        return ((taskResponsible, args) -> {
            Subscriber subscriber = new Subscriber(taskResponsible.getId());
            subscriber.setTask(new Task(taskResponsible.getTask().getId()));
            subscriber.setUser(new User(taskResponsible.getUser().getId()));
            return subscriber;
        });
    }
}
