package br.com.kproj.salesman.delivery.infrastructure.dtos;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;

import java.util.HashMap;
import java.util.Map;

public class DeliverySummaryExecutingDTO {

    private UserEntity user;
    Map<TaskStatusEntity, Long> statistics = new HashMap<>();

    public DeliverySummaryExecutingDTO(UserEntity user) {
        this.user = user;
    }

    public DeliverySummaryExecutingDTO addStatistic(TaskStatusEntity status, Long quantity) {
        statistics.put(status, quantity);
        return this;
    }

    public Long getCount(TaskStatusEntity status) {
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
