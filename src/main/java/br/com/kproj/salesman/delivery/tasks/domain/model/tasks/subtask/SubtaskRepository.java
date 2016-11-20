package br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SubtaskRepository extends BaseRepository<Subtask, Long> {

    Iterable<Subtask> findAll(RootTask rootTask, Pageable pageable);

    void delete(Subtask task);

    Optional<Subtask> add(Subtask subtask, RootTask task);

}
