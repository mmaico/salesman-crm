package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.generatebysalesorder.convert;

import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import org.springframework.core.convert.converter.Converter;


public class TaskTemplateToTask implements Converter<TaskDefinitionEntity, TaskEntity> {

    private SalesOrder salesOrder;

    public TaskTemplateToTask(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }


    @Override
    public TaskEntity convert(TaskDefinitionEntity source) {
//        Integer quantityDays = source.getQuantityDaysTofinishAfertSignedContract();
//
//        TaskBuilder builder = TaskBuilder.createTaskBuilder()
//                .withDeadline(addDayToDate(quantityDays, new Date()))
//                .withDescription(source.getDescription())
//                .withStatus(TaskStatusEntity.WAITING)
//                .withTitle(source.getTitle())
//                .withRegion(source.getRegion())
//                .withSalesOrder(new SalesOrderEntity(salesOrder.getId()));
//
//        safeIterable(source.getChecklistTemplateEntities()).forEach(checklist -> builder.addCheckList(
//                ChecklistTemplateToChecklist.create(builder.build()).convert(checklist)
//        ));
//
//        if (source.getTemplatesChilds() != null) {
//            source.getTemplatesChilds().stream()
//                    .filter(item -> item.getRegion().equals(new OperationRegionEntity(salesOrder.getRegion().getId())))
//                        .forEach(child -> builder.addChild(TaskTemplateToTask.create(salesOrder).convert(child)));
//        }

//        return builder.build();
        return null;
    }

    public static TaskTemplateToTask create(SalesOrder salesOrder) {
        return new TaskTemplateToTask(salesOrder);
    }
}
