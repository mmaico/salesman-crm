package br.com.kproj.salesman.delivery.application.tasks;


import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface TaskApplication extends ModelLegacyService<TaskEntity> {

    TaskEntity register(TaskEntity taskEntity);

    TaskEntity registerSubtask(TaskEntity parent, TaskEntity taskEntityChild);

    void generateTaskByNewSalesOrder(SalesOrderEntity salesOrderEntity) throws Exception;

    List<TaskEntity> findBySaleOrder(SalesOrderEntity salesOrderEntity);

    Boolean isSomeonesSon(TaskEntity taskEntity);

    void changeStatus(TaskEntity taskEntity, UserEntity userChange);

    DeliveryResumeExecutionTaskDTO getResume();

    DeliveryResumeExecutionTaskDTO getResume(SalesOrderEntity salesOrderEntity);

    Long countBySalesOrder(SalesOrderEntity salesOrderEntity);

    void signedTask(UserEntity user, TaskEntity taskEntity);

    void unsignedTask(UserEntity user, TaskEntity taskEntity);

    List<TaskEntity> findTaskRootBy(SalesOrderEntity salesOrderEntity);


}
