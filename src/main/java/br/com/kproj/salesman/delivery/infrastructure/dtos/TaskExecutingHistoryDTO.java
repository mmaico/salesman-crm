package br.com.kproj.salesman.delivery.infrastructure.dtos;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.helpers.RangeDateDTO;

import java.util.HashMap;
import java.util.Map;

public class TaskExecutingHistoryDTO {

    private RangeDateDTO rangeDate;

    Map<TaskStatus, Long> statistics = new HashMap<>();

    public TaskExecutingHistoryDTO(RangeDateDTO rangeDateDTO) {
        this.rangeDate = rangeDateDTO;
    }

    public TaskExecutingHistoryDTO addStatistic(TaskStatus status, Long quantity) {
        statistics.put(status, quantity);
        return this;
    }

    public Long getCount(TaskStatus status) {
        Long result = statistics.get(status);

        return result == null ? 0l : result;
    }

    public RangeDateDTO getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(RangeDateDTO rangeDate) {
        this.rangeDate = rangeDate;
    }

    public static TaskExecutingHistoryDTO createSummary(RangeDateDTO rangeDateDTO) {
        return new TaskExecutingHistoryDTO(rangeDateDTO);
    }
}
