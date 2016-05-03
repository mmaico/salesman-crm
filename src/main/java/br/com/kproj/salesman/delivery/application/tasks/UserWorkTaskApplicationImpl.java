package br.com.kproj.salesman.delivery.application.tasks;

import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.SalesOrderSummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.TaskExecutingHistoryDTO;
import br.com.kproj.salesman.delivery.infrastructure.repository.SalesOrderDeliveryRepository;
import br.com.kproj.salesman.delivery.infrastructure.repository.TaskChangeHistoryRepository;
import br.com.kproj.salesman.delivery.infrastructure.repository.UserWorkOnTasksRepository;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.helpers.RangeDateDTO;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO.createSummary;
import static br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus.*;

@Service
public class UserWorkTaskApplicationImpl implements UserWorkTaskApplication {

    @Autowired
    private UserWorkOnTasksRepository repository;

    @Autowired
    private TaskChangeHistoryRepository taskChangeHistoryRepository;

    @Autowired
    private SalesOrderDeliveryRepository salesOrderDeliveryRepository;



    @Override
    public List<DeliverySummaryExecutingDTO> getSummaryTasksExecuting() {

        List<User> users = repository.findUsersWorksInTasks();

        return users.stream()
                .map(user -> createSummary(user)
                        .addStatistic(DONE, repository.countOnAllTasksBy(user, DONE))
                        .addStatistic(PROBLEM, repository.countOnAllTasksBy(user, PROBLEM))
                        .addStatistic(WAITING, repository.countOnAllTasksBy(user, WAITING))
                        .addStatistic(STATED, repository.countOnAllTasksBy(user, STATED)))
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesOrderSummaryExecutingDTO> getSummarySalesOrderTasksExecuting(SalesOrder salesOrder) {

        List<User> users = repository.findUsersWorksOnSalesOrder(salesOrder);

        return users.stream().map(user -> SalesOrderSummaryExecutingDTO.createSummary(user)
                        .addStatistic(DONE, repository.countOnTaskBy(user, salesOrder, DONE))
                        .addStatistic(PROBLEM, repository.countOnTaskBy(user, salesOrder, PROBLEM))
                        .addStatistic(WAITING, repository.countOnTaskBy(user, salesOrder, WAITING))
                        .addStatistic(STATED, repository.countOnTaskBy(user, salesOrder, STATED)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskExecutingHistoryDTO> getTaskExecutingHistory(SalesOrder salesOrder) {

        SalesOrder salesOrderLoaded = salesOrderDeliveryRepository.findOne(salesOrder.getId());

        Page<Date> startDateResult = taskChangeHistoryRepository.findDateFromNewestHistory(salesOrder, Pager.build().one());
        Page<Date> endDateResult = taskChangeHistoryRepository.findDateFromOldestHistory(salesOrder, Pager.build().one());


        Date startDate = startDateResult.getContent().size() > 0 ? startDateResult.getContent().get(0) : salesOrderLoaded.getCreationDate();
        Date endDate = endDateResult.getContent().size() > 0 ? endDateResult.getContent().get(0) : new Date();

        List<RangeDateDTO> rangeWeeks = DateHelper.getRangeWeeks(startDate, endDate);

        List<TaskExecutingHistoryDTO> listHistory = rangeWeeks.stream().map(rangeDate ->
                TaskExecutingHistoryDTO.createSummary(rangeDate)
                        .addStatistic(DONE, taskChangeHistoryRepository.countHistoryByRangeDatesAndStatus(rangeDate.getStartDate(), rangeDate.getEndDate(), DONE))
                        .addStatistic(STATED, taskChangeHistoryRepository.countHistoryByRangeDatesAndStatus(rangeDate.getStartDate(), rangeDate.getEndDate(), STATED))
                        .addStatistic(PROBLEM, taskChangeHistoryRepository.countHistoryByRangeDatesAndStatus(rangeDate.getStartDate(), rangeDate.getEndDate(), PROBLEM))
                        .addStatistic(WAITING, taskChangeHistoryRepository.countHistoryByRangeDatesAndStatus(rangeDate.getStartDate(), rangeDate.getEndDate(), WAITING))
        ).collect(Collectors.toList());

        return listHistory;
    }
}
