package br.com.kproj.salesman.delivery2.tasks.application;


import br.com.kproj.salesman.delivery2.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Subtask;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Collection;
import java.util.Optional;

public interface TaskFacade extends ModelFacade<Task> {

    Optional<Task> register(Task task);

    Optional<Subtask> register(Subtask subtask);

    Collection<Task> findAll(SalesOrder salesOrder);

    void generateByNewSalesOrder(SalesOrder salesOrder);

}
