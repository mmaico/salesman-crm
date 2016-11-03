package br.com.kproj.salesman.products_catalog.delivery_definition.model.product;

import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.TaskRepository;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class Saleable extends ModelIdentifiable {

    private Long id;

    @Autowired
    private TaskRepository taskRepository;

    public Saleable() {
        autowire(this);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<Task> addTask(Task task) {
        return taskRepository.add(task, this);
    }
}
