package br.com.kproj.salesman.delivery.application.triggernotification;


import br.com.kproj.salesman.infrastructure.entity.task.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface TaskNotificationApplication extends ModelLegacyService<ScheduleTriggerNotification> {


    ScheduleTriggerNotification register(ScheduleTriggerNotification notification);

}
