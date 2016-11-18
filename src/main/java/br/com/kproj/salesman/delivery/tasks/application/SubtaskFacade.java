package br.com.kproj.salesman.delivery.tasks.application;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface SubtaskFacade extends ModelFacade<Subtask> {

    Optional<Subtask> register(Subtask task);


}
