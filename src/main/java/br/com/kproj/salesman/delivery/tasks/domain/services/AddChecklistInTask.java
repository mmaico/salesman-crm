package br.com.kproj.salesman.delivery.tasks.domain.services;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;

import java.util.Optional;

@FunctionalInterface
public interface AddChecklistInTask {

    Optional<Checklist> to(Task task);
}
