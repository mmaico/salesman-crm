package br.com.kproj.salesman.delivery.application.tasks;


import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface TaskApplication extends ModelLegacyService<Task> {

    Task register(Task task);

    Task registerSubtask(Task parent, Task taskChild);

    void generateTaskByNewSalesOrder(SalesOrder salesOrder) throws Exception;

    List<Task> findBySaleOrder(SalesOrder salesOrder);

    Boolean isSomeonesSon(Task task);

    void changeStatus(Task task, UserEntity userChange);

    DeliveryResumeExecutionTaskDTO getResume();

    DeliveryResumeExecutionTaskDTO getResume(SalesOrder salesOrder);

    Long countBySalesOrder(SalesOrder salesOrder);

    void signedTask(UserEntity user, Task task);

    void unsignedTask(UserEntity user, Task task);

    List<Task> findTaskRootBy(SalesOrder salesOrder);


}
