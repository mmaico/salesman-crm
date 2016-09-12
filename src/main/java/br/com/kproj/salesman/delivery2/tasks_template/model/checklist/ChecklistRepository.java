package br.com.kproj.salesman.delivery2.tasks_template.model.checklist;


import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;

public interface ChecklistRepository extends BaseRepository<Checklist, Long> {

    Collection<Checklist> findAll(Task task);

    void delete(Checklist checklist);

}
