package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist;


import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;

public interface ChecklistRepository extends BaseRepository<Checklist, Long> {

    Collection<Checklist> findAll(Task task);

    void delete(Checklist checklist);

}
