package br.com.kproj.salesman.delivery.tasks.application;


import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Collection;
import java.util.Optional;

public interface TaskFacade extends ModelFacade<Task> {

    Optional<Task> register(Task task);

    Collection<Task> findAll(SalesOrder salesOrder);

    void generateByNewSalesOrder(SalesOrder salesOrder);

}
