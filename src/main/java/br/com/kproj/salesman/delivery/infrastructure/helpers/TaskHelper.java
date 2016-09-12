package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.delivery.application.tasks.TaskApplication;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.task.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Component
public class TaskHelper {

    private static Map<TaskStatusEntity, String> mapStatus = new HashMap<>();

    static {
        mapStatus.put(TaskStatusEntity.WAITING, "icon entypo-right-open-big");
        mapStatus.put(TaskStatusEntity.STATED, "icon entypo-clock");
        mapStatus.put(TaskStatusEntity.DONE, "icon entypo-check");
        mapStatus.put(TaskStatusEntity.PROBLEM, "icon entypo-cancel");
    }

    @Autowired
    private TaskApplication application;




    public List<TaskEntity> getTasks(SalesOrderEntity salesOrderEntity) {
        return application.findBySaleOrder(salesOrderEntity);
    }

    public TaskEntity load(TaskEntity taskEntity) {
        Optional<TaskEntity> result = application.getOne(taskEntity.getId());

        return result.isPresent() ? result.get() : null;
    }

    public String getIconStatus(TaskStatusEntity status) {

        return mapStatus.get(status);
    }

    public Boolean isSomeonesSon(TaskEntity taskEntity) {
        return application.isSomeonesSon(taskEntity);
    }

    public List<TaskEntity> findTaskRootBy(SalesOrderEntity salesOrderEntity) {
        return application.findTaskRootBy(salesOrderEntity);
    }


    public ScheduleTriggerNotification getValidTaskNotification(TaskEntity taskEntity) {

        if (!isEmptySafe(taskEntity.getTriggerNotifications())) {
            return taskEntity.getTriggerNotifications().get(0);
        }

        return null;
    }
}
