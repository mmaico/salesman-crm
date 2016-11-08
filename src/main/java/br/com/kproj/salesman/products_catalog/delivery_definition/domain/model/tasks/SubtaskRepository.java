package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;
import java.util.Optional;

public interface SubtaskRepository extends BaseRepository<Subtask, Long> {

    Collection<Subtask> findAll(RootTask rootTask);

    void delete(Subtask task);

    Optional<Subtask> add(Subtask subtask, RootTask task);

}
