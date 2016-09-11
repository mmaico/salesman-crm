package br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder.convert;

import br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.safeIterable;
import static br.com.kproj.salesman.infrastructure.helpers.DateHelper.addDayToDate;


public class TaskTemplateToTask implements Converter<TaskTemplateEntity, TaskEntity> {

    private SalesOrderEntity salesOrderEntity;

    public TaskTemplateToTask(SalesOrderEntity salesOrderEntity) {
        this.salesOrderEntity = salesOrderEntity;
    }


    @Override
    public TaskEntity convert(TaskTemplateEntity source) {
//        Integer quantityDays = source.getQuantityDaysTofinishAfertSignedContract();
//
//        TaskBuilder builder = TaskBuilder.createTaskBuilder()
//                .withDeadline(addDayToDate(quantityDays, new Date()))
//                .withDescription(source.getDescription())
//                .withStatus(TaskStatus.WAITING)
//                .withTitle(source.getTitle())
//                .withRegion(source.getRegion())
//                .withSalesOrder(salesOrderEntity);
//
//        safeIterable(source.getChecklistTemplateEntities()).forEach(checklist -> builder.addCheckList(
//                ChecklistTemplateToChecklist.create(builder.build()).convert(checklist)
//        ));
//
//        safeIterable(source.getTasksCostsTemplates()).forEach(cost -> builder.addTaskCost(
//                TaskCostTemplateToTaskCost.create(builder.build()).convert(cost)
//            ));
//
//        if (source.getTemplatesChilds() != null) {
//            source.getTemplatesChilds().stream()
//                    .filter(item -> item.getRegion().equals(salesOrderEntity.getOperationRegionEntity()))
//                        .forEach(child -> builder.addChild(TaskTemplateToTask.create(salesOrderEntity).convert(child)));
//        }

        return null;
    }

    public static TaskTemplateToTask create(SalesOrderEntity salesOrderEntity) {
        return new TaskTemplateToTask(salesOrderEntity);
    }
}
