package br.com.kproj.salesman.delivery.application.tasks;


import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.List;

public interface TaskApplication extends ModelService<Task> {

    Task register(Task task);

    void generateTaskByNewSalesOrder(SalesOrder salesOrder) throws Exception;

    List<Task> findBySaleOrder(SalesOrder salesOrder);

    Boolean isSomeonesSon(Task task);

    void changeStatus(Task task, User userChange);

    DeliveryResumeExecutionTaskDTO getResume();

    DeliveryResumeExecutionTaskDTO getResume(SalesOrder salesOrder);

    Long countBySalesOrder(SalesOrder salesOrder);

    void signedTask(User user, Task task);

    void unsignedTask(User user, Task task);

}
