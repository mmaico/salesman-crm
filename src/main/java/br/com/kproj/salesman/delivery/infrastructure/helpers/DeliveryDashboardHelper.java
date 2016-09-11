package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.delivery.application.WorkspaceApplication;
import br.com.kproj.salesman.delivery.application.tasks.TaskApplication;
import br.com.kproj.salesman.delivery.application.tasks.UserWorkTaskApplication;
import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.SalesOrderSummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.TaskExecutingHistoryDTO;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryDashboardHelper {

    @Autowired
    private TaskApplication application;

    @Autowired
    private WorkspaceApplication workspaceApplication;

    @Autowired
    private UserWorkTaskApplication userWorkTaskApplication;

    @Autowired
    private SecurityHelper security;

    public DeliveryResumeExecutionTaskDTO getResumeTasks() {
        return application.getResume();
    }

    public DeliveryResumeExecutionTaskDTO getResumeTasks(SalesOrderEntity salesOrderEntity) {
        return application.getResume(salesOrderEntity);
    }

    public List<SalesOrderEntity> findNewSalesOrder() {
        return workspaceApplication.findNewSalesOrder();
    }

    public List<SalesOrderEntity> findSalesOrderInActDelivery() {
        return workspaceApplication.findSalesOrderNotInWorkspace();
    }

    public List<UserEntity> findUsersResponsibles(SalesOrderEntity salesOrderEntity) {
        return workspaceApplication.findUsersResponsibles(salesOrderEntity);
    }

    public Boolean isInMyWorkspace(SalesOrderEntity salesOrderEntity) {
        return workspaceApplication.isInMyWorkspace(salesOrderEntity, null);
    }

    public Long countBySalesOrder(SalesOrderEntity salesOrderEntity) {
        return this.application.countBySalesOrder(salesOrderEntity);
    }

    public List<DeliverySummaryExecutingDTO> getSummaryTasksExecuting() {
        return userWorkTaskApplication.getSummaryTasksExecuting();
    }

    public List<SalesOrderSummaryExecutingDTO> getSummarySalesOrderTasksExecuting(SalesOrderEntity salesOrderEntity) {
        return userWorkTaskApplication.getSummarySalesOrderTasksExecuting(salesOrderEntity);
    }

    public List<TaskExecutingHistoryDTO> getTaskExecutingHistory(SalesOrderEntity salesOrderEntity) {
        return userWorkTaskApplication.getTaskExecutingHistory(salesOrderEntity);
    }
}
