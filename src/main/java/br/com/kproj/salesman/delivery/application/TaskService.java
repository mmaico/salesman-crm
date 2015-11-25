package br.com.kproj.salesman.delivery.application;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.List;

public interface TaskService extends ModelService<Task> {

    Task register(Task task);

    List<Task> findBySaleOrder(SalesOrder salesOrder);

    Boolean isSomeonesSon(Task task);

}
