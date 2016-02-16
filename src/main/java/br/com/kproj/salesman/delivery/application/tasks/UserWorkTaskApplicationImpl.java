package br.com.kproj.salesman.delivery.application.tasks;

import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.dtos.SalesOrderSummaryExecutingDTO;
import br.com.kproj.salesman.delivery.infrastructure.repository.UserWorkOnTasksRepository;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.delivery.infrastructure.dtos.DeliverySummaryExecutingDTO.createSummary;
import static br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus.*;

@Service
public class UserWorkTaskApplicationImpl implements UserWorkTaskApplication {

    @Autowired
    private UserWorkOnTasksRepository repository;

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
}
