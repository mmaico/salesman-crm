package br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder.convert;

import br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.safeIterable;
import static br.com.kproj.salesman.infrastructure.helpers.DateHelper.addDayToDate;


public class TaskTemplateToTask implements Converter<TaskTemplate, Task> {

    private SalesOrder salesOrder;

    public TaskTemplateToTask(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }


    @Override
    public Task convert(TaskTemplate source) {
        Integer quantityDays = source.getQuantityDaysTofinishAfertSignedContract();

        TaskBuilder builder = TaskBuilder.createTaskBuilder()
                .withDeadline(addDayToDate(quantityDays, new Date()))
                .withDescription(source.getDescription())
                .withStatus(TaskStatus.WATTING)
                .withTimeline()
                .withTitle(source.getTitle())
                .withSalesOrder(salesOrder);


        safeIterable(source.getChecklistTemplates()).forEach(checklist -> builder.addCheckList(
                ChecklistTemplateToChecklist.create(builder.build()).convert(checklist)
        ));

        safeIterable(source.getTasksCostsTemplates()).forEach(cost -> builder.addTaskCost(
                TaskCostTemplateToTaskCost.create(builder.build()).convert(cost)
            ));

        safeIterable(source.getTemplatesChilds()).forEach(child -> builder.addChild(
                TaskTemplateToTask.create(salesOrder).convert(child)
            ));

        return builder.build();
    }

    public static TaskTemplateToTask create(SalesOrder salesOrder) {
        return new TaskTemplateToTask(salesOrder);
    }
}
