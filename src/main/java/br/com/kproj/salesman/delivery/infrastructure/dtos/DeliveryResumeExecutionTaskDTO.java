package br.com.kproj.salesman.delivery.infrastructure.dtos;

import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class DeliveryResumeExecutionTaskDTO {
    
    private List<ExecutingResumeItem> items = Lists.newArrayList();
    
    public static class ExecutingResumeItem {
        
        TaskStatus status;
        Long count = 0l;

        public ExecutingResumeItem(TaskStatus status, Long count) {
            this.status = status;
            this.count = count;
        }
        
        public static ExecutingResumeItem create(TaskStatus status, Long count) {
            return new ExecutingResumeItem(status, count);
        }

        public TaskStatus getStatus() {
            return status;
        }

        public Long getCount() {
            return count;
        }

    }

    public List<ExecutingResumeItem> getItems() {
        return items;
    }

    public DeliveryResumeExecutionTaskDTO add(TaskStatus status, Long count) {
        this.items.add(ExecutingResumeItem.create(status, count));
        return this;
    }

    public static DeliveryResumeExecutionTaskDTO create() {
        return new DeliveryResumeExecutionTaskDTO();
    }

    public ExecutingResumeItem getByName(String taskStatus) {
        Optional<ExecutingResumeItem> result = this.items.stream()
                .filter(item -> item.getStatus().name().equalsIgnoreCase(taskStatus)).findFirst();

        return result.get();
    }

    public Boolean hasInfos() {
        long count = this.items.stream().map(item -> item.count).reduce(0l, (value1, value2) -> value1 + value2);

        return count > 0;
    }

    public Long getTotal() {
        return this.items.stream().map(item -> item.count).reduce(0l, (value1, value2) -> value1 + value2);
    }

    public Long getPercentageTotalDone() {
        Long total = getTotal();
        Long totalDone = getByName(TaskStatus.DONE.name()).getCount();

        if (totalDone.equals(0l)) {
            return 0l;
        }
        return new BigDecimal(totalDone)
                    .divide(new BigDecimal(total), 1, BigDecimal.ROUND_HALF_DOWN).setScale(BigDecimal.ROUND_DOWN, 0).multiply(new BigDecimal(100)).longValue();


    }
}
