package br.com.kproj.salesman.delivery.tasks.application;


import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskFacade extends ModelFacade<Task> {

    Optional<Task> register(Task task);

    Optional<Task> update(Task task);


    Iterable<Task> findAll(Delivery delivery, Pageable pageable);

    void generateByNewSalesOrder(SalesOrder salesOrder);


}
