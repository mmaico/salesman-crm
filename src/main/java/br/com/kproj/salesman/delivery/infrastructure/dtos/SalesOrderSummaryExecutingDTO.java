package br.com.kproj.salesman.delivery.infrastructure.dtos;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;

import java.util.HashMap;
import java.util.Map;

public class SalesOrderSummaryExecutingDTO {

    private User user;
    Map<TaskStatus, Long> statistics = new HashMap<>();

    public SalesOrderSummaryExecutingDTO(User user) {
        this.user = user;
    }

    public SalesOrderSummaryExecutingDTO addStatistic(TaskStatus status, Long quantity) {
        statistics.put(status, quantity);
        return this;
    }

    public static SalesOrderSummaryExecutingDTO createSummary(User user) {
        return new SalesOrderSummaryExecutingDTO(user);
    }
}
