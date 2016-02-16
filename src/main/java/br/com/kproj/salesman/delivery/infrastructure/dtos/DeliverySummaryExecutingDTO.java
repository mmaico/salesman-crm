package br.com.kproj.salesman.delivery.infrastructure.dtos;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;

import java.util.HashMap;
import java.util.Map;

public class DeliverySummaryExecutingDTO {

    private User user;
    Map<TaskStatus, Long> statistics = new HashMap<>();

    public DeliverySummaryExecutingDTO(User user) {
        this.user = user;
    }

    public DeliverySummaryExecutingDTO addStatistic(TaskStatus status, Long quantity) {
        statistics.put(status, quantity);
        return this;
    }

    public static DeliverySummaryExecutingDTO createSummary(User user) {
        return new DeliverySummaryExecutingDTO(user);
    }
}
