package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.delivery.application.ActDeliverySalesApplication;
import br.com.kproj.salesman.delivery.application.tasks.TaskApplication;
import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskHelper {

    private static Map<TaskStatus, String> mapStatus = new HashMap<>();

    static {
        mapStatus.put(TaskStatus.WAITING, "icon entypo-right-open-big");
        mapStatus.put(TaskStatus.STATED, "icon entypo-clock");
        mapStatus.put(TaskStatus.DONE, "icon entypo-check");
        mapStatus.put(TaskStatus.PROBLEM, "icon entypo-cancel");
    }

    @Autowired
    private TaskApplication application;


    public List<Task> getTasks(SalesOrder salesOrder) {
        return application.findBySaleOrder(salesOrder);
    }

    public String getIconStatus(TaskStatus status) {

        return mapStatus.get(status);
    }
}
