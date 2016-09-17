package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.generatebysalesorder.convert;

import br.com.kproj.salesman.infrastructure.entity.builders.ChecklistBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import org.springframework.core.convert.converter.Converter;


public class ChecklistTemplateToChecklist implements Converter<ChecklistTemplateEntity, ChecklistEntity> {

    private TaskEntity taskEntity;

    public ChecklistTemplateToChecklist(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    @Override
    public ChecklistEntity convert(ChecklistTemplateEntity source) {
        return ChecklistBuilder.createChecklist()
                    .withDone(Boolean.FALSE)
                    .withName(source.getName())
                    .withTask(taskEntity).build();

    }

    public static ChecklistTemplateToChecklist create(TaskEntity taskEntity) {
        return new ChecklistTemplateToChecklist(taskEntity);
    }
}
