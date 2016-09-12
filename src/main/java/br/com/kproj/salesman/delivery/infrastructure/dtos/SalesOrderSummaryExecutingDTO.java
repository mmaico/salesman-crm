package br.com.kproj.salesman.delivery.infrastructure.dtos;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;

import java.util.HashMap;
import java.util.Map;

public class SalesOrderSummaryExecutingDTO {

    private UserEntity user;
    Map<TaskStatusEntity, Long> statistics = new HashMap<>();

    public SalesOrderSummaryExecutingDTO(UserEntity user) {
        this.user = user;
    }

    public SalesOrderSummaryExecutingDTO addStatistic(TaskStatusEntity status, Long quantity) {
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

    public static SalesOrderSummaryExecutingDTO createSummary(UserEntity user) {
        return new SalesOrderSummaryExecutingDTO(user);
    }
}
