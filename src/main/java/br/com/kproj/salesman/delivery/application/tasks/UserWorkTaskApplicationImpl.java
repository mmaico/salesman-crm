package br.com.kproj.salesman.delivery.application.tasks;

import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.SalesOrderSummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.TaskExecutingHistoryDTO;
import br.com.kproj.salesman.delivery.infrastructure.repository.SalesOrderDeliveryRepository;
import br.com.kproj.salesman.delivery.infrastructure.repository.TaskChangeHistoryRepository;
import br.com.kproj.salesman.delivery.infrastructure.repository.UserWorkOnTasksRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.helpers.RangeDateDTO;
import br.com.kproj.salesman.infrastructure.repository.Pager;
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

        List<UserEntity> users = repository.findUsersWorksInTasks();

        return users.stream()
                .map(user -> createSummary(user)
                        .addStatistic(DONE, repository.countOnAllTasksBy(user, DONE))
                        .addStatistic(PROBLEM, repository.countOnAllTasksBy(user, PROBLEM))
                        .addStatistic(WAITING, repository.countOnAllTasksBy(user, WAITING))
                        .addStatistic(STATED, repository.countOnAllTasksBy(user, STATED)))
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesOrderSummaryExecutingDTO> getSummarySalesOrderTasksExecuting(SalesOrderEntity salesOrderEntity) {

        List<UserEntity> users = repository.findUsersWorksOnSalesOrder(salesOrderEntity);

        return users.stream().map(user -> SalesOrderSummaryExecutingDTO.createSummary(user)
                        .addStatistic(DONE, repository.countOnTaskBy(user, salesOrderEntity, DONE))
                        .addStatistic(PROBLEM, repository.countOnTaskBy(user, salesOrderEntity, PROBLEM))
                        .addStatistic(WAITING, repository.countOnTaskBy(user, salesOrderEntity, WAITING))
                        .addStatistic(STATED, repository.countOnTaskBy(user, salesOrderEntity, STATED)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskExecutingHistoryDTO> getTaskExecutingHistory(SalesOrderEntity salesOrderEntity) {

        SalesOrderEntity salesOrderEntityLoaded = salesOrderDeliveryRepository.findOne(salesOrderEntity.getId());

        Page<Date> startDateResult = taskChangeHistoryRepository.findDateFromNewestHistory(salesOrderEntity, Pager.build().one());
        Page<Date> endDateResult = taskChangeHistoryRepository.findDateFromOldestHistory(salesOrderEntity, Pager.build().one());


        Date startDate = startDateResult.getContent().size() > 0 ? startDateResult.getContent().get(0) : salesOrderEntityLoaded.getCreationDate();
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
