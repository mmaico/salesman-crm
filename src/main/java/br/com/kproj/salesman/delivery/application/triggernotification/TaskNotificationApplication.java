package br.com.kproj.salesman.delivery.application.triggernotification;


import br.com.kproj.salesman.infrastructure.entity.task.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface TaskNotificationApplication extends ModelService<ScheduleTriggerNotification> {


    ScheduleTriggerNotification register(ScheduleTriggerNotification notification);

}
