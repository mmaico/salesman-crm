package br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;
import java.util.Optional;

public interface SubtaskRepository extends BaseRepository<Subtask, Long> {

    Collection<Subtask> findAll(RootTask rootTask);

    void delete(Subtask task);

    Optional<Subtask> add(Subtask subtask, RootTask task);

}
