package br.com.kproj.salesman.delivery.application.triggernotification;


import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.notification.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.List;

public interface TaskNotificationApplication extends ModelService<ScheduleTriggerNotification> {


    ScheduleTriggerNotification register(ScheduleTriggerNotification notification);

}
