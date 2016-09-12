package br.com.kproj.salesman.delivery2.tasks.domain.model.checklist;



import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;
import java.util.Optional;

public interface ChecklistRepository extends BaseRepository<Checklist, Long> {

    Optional<Checklist> register(Checklist checklist, Task task);
    Collection<Checklist> findAll(Task task);
    void complete(Checklist checklist);
}
