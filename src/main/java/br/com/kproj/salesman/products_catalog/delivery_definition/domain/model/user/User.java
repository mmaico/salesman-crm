package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.user;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskToSaleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.service.ToSaleable;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;


public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private TaskRepository repository;

    public User() {
        autowire(this);
    }

    public ToSaleable createA(Task task) {
        return (saleable ->
             repository.create(TaskToSaleable.createTaskToSaleable(saleable.getId(), task))
        );
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static User user() {
        return new User();
    }

}
