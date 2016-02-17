package br.com.kproj.salesman.delivery.application.tasks;


import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.SalesOrderSummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.TaskExecutingHistoryDTO;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;

import java.util.List;

public interface UserWorkTaskApplication  {


    List<DeliverySummaryExecutingDTO> getSummaryTasksExecuting();

    List<SalesOrderSummaryExecutingDTO> getSummarySalesOrderTasksExecuting(SalesOrder salesOrder);

    List<TaskExecutingHistoryDTO> getTaskExecutingHistory(SalesOrder salesOrder);


}
