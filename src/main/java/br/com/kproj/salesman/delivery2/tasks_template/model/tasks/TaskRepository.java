package br.com.kproj.salesman.delivery2.tasks_template.model.tasks;


import br.com.kproj.salesman.delivery2.tasks_template.model.product.Saleable;
import br.com.kproj.salesman.delivery2.tasks_template.model.region.Region;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;

public interface TaskRepository extends BaseRepository<Task, Long> {

    Collection<Task> findAll(Saleable saleable);

    Collection<Task> findAll(Saleable saleable, Region region);

    void delete(Task task);

}
