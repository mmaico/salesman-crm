package br.com.kproj.salesman.delivery2.tasks_template.application;


import br.com.kproj.salesman.delivery2.tasks_template.model.product.Saleable;
import br.com.kproj.salesman.delivery2.tasks_template.model.region.Region;
import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.Task;
import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.TaskToSaleable;

import java.util.Collection;
import java.util.Optional;

public interface TaskFacade {

    Collection<Task> findAll(Saleable saleable);

    Collection<Task> findAll(Saleable saleable, Region region);

    void delete(Task task);

    Optional<Task> register(TaskToSaleable taskToSaleable);
}
