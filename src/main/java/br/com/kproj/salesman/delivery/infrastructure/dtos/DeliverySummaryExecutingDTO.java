package br.com.kproj.salesman.delivery.infrastructure.dtos;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;

import java.util.HashMap;
import java.util.Map;

public class DeliverySummaryExecutingDTO {

    private UserEntity user;
    Map<TaskStatus, Long> statistics = new HashMap<>();

    public DeliverySummaryExecutingDTO(UserEntity user) {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public static DeliverySummaryExecutingDTO createSummary(UserEntity user) {
        return new DeliverySummaryExecutingDTO(user);
    }
}
