package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.generatebysalesorder.convert;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.ChecklistDefinitionEntity;
import org.springframework.core.convert.converter.Converter;


public class ChecklistTemplateToChecklist implements Converter<ChecklistDefinitionEntity, ChecklistEntity> {

    private TaskEntity taskEntity;

    public ChecklistTemplateToChecklist(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    @Override
    public ChecklistEntity convert(ChecklistDefinitionEntity source) {
//        return ChecklistBuilder.createChecklist()
//                    .withDone(Boolean.FALSE)
//                    .withName(source.getName())
//                    .withTask(taskEntity).build();
        return null;

    }

    public static ChecklistTemplateToChecklist create(TaskEntity taskEntity) {
        return new ChecklistTemplateToChecklist(taskEntity);
    }
}
