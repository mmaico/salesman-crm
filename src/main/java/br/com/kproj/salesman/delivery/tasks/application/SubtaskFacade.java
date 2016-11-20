package br.com.kproj.salesman.delivery.tasks.application;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskToRootTask;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SubtaskFacade extends ModelFacade<Subtask> {

    Optional<Subtask> register(SubtaskToRootTask subtaskToRootTask);

    Iterable<Subtask> findAll(RootTask rootTask, Pageable pageable);


}
