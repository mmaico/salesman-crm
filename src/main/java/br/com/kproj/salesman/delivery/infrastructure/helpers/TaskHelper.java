package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.delivery.application.WorkspaceApplication;
import br.com.kproj.salesman.delivery.application.tasks.TaskApplication;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.task.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

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

    @Autowired
    private WorkspaceApplication workspaceApplication;

    @Autowired
    private SecurityHelper security;


    public List<Task> getTasks(SalesOrder salesOrder) {
        return application.findBySaleOrder(salesOrder);
    }

    public String getIconStatus(TaskStatus status) {

        return mapStatus.get(status);
    }

    public Boolean isSomeonesSon(Task task) {
        return application.isSomeonesSon(task);
    }

    public List<Task> findTaskRootBy(SalesOrder salesOrder) {
        return application.findTaskRootBy(salesOrder);
    }

    public List<SalesOrder> getSalesOrderInMyWorkspace() {
        User user = security.getPrincipal().getUser();
        return workspaceApplication.findBy(user);
    }

    public ScheduleTriggerNotification getValidTaskNotification(Task task) {

        if (!isEmptySafe(task.getTriggerNotifications())) {
            return task.getTriggerNotifications().get(0);
        }

        return null;
    }
}
