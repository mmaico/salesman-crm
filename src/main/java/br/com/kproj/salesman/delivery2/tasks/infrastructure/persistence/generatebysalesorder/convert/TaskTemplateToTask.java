package br.com.kproj.salesman.delivery2.tasks.infrastructure.persistence.generatebysalesorder.convert;

import br.com.kproj.salesman.delivery2.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.safeIterable;
import static br.com.kproj.salesman.infrastructure.helpers.DateHelper.addDayToDate;


public class TaskTemplateToTask implements Converter<TaskTemplateEntity, TaskEntity> {

    private SalesOrder salesOrder;

    public TaskTemplateToTask(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }


    @Override
    public TaskEntity convert(TaskTemplateEntity source) {
        Integer quantityDays = source.getQuantityDaysTofinishAfertSignedContract();

        TaskBuilder builder = TaskBuilder.createTaskBuilder()
                .withDeadline(addDayToDate(quantityDays, new Date()))
                .withDescription(source.getDescription())
                .withStatus(TaskStatusEntity.WAITING)
                .withTitle(source.getTitle())
                .withRegion(source.getRegion())
                .withSalesOrder(new SalesOrderEntity(salesOrder.getId()));

        safeIterable(source.getChecklistTemplateEntities()).forEach(checklist -> builder.addCheckList(
                ChecklistTemplateToChecklist.create(builder.build()).convert(checklist)
        ));

        if (source.getTemplatesChilds() != null) {
            source.getTemplatesChilds().stream()
                    .filter(item -> item.getRegion().equals(new OperationRegionEntity(salesOrder.getRegion().getId())))
                        .forEach(child -> builder.addChild(TaskTemplateToTask.create(salesOrder).convert(child)));
        }

        return builder.build();
    }

    public static TaskTemplateToTask create(SalesOrder salesOrder) {
        return new TaskTemplateToTask(salesOrder);
    }
}
