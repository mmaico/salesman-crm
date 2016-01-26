package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.delivery.application.ActDeliverySalesApplication;
import br.com.kproj.salesman.delivery.application.tasks.TaskApplication;
import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryDashboardHelper {

    @Autowired
    private TaskApplication application;

    @Autowired
    private ActDeliverySalesApplication actDeliverySalesApplication;

    public DeliveryResumeExecutionTaskDTO getResumeTasks() {
        return application.getResume();
    }

    public DeliveryResumeExecutionTaskDTO getResumeTasks(SalesOrder salesOrder) {
        return application.getResume(salesOrder);
    }

    public List<SalesOrder> findNewSalesOrder() {
        return actDeliverySalesApplication.findNewSalesOrder();
    }

    public List<SalesOrder> findSalesOrderInActDelivery() {
        return actDeliverySalesApplication.findSalesOrderInActDelivery();
    }

    public List<User> findUsersResponsibles(SalesOrder salesOrder) {
        return actDeliverySalesApplication.findUsersResponsibles(salesOrder);
    }

    public Long countBySalesOrder(SalesOrder salesOrder) {
        return this.application.countBySalesOrder(salesOrder);
    }
}
