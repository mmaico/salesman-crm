package br.com.kproj.salesman.delivery.tasks.domain.model.checklist;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChecklistRepository extends BaseRepository<Checklist, Long> {

    Optional<Checklist> register(Checklist checklist, Task task);
    Iterable<Checklist> findAll(Task task, Pageable pageable);
    Checklist update(Checklist checklist);
}
