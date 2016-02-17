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

    public Long getCount(TaskStatus status) {
        Long result = statistics.get(status);

        return result == null ? 0l : result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static DeliverySummaryExecutingDTO createSummary(User user) {
        return new DeliverySummaryExecutingDTO(user);
    }
}
