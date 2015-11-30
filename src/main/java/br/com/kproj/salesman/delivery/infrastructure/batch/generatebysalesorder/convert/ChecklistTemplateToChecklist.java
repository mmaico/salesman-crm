package br.com.kproj.salesman.delivery.infrastructure.batch.generatebysalesorder.convert;

import br.com.kproj.salesman.infrastructure.entity.builders.ChecklistBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.Checklist;
import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplate;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import org.springframework.core.convert.converter.Converter;


public class ChecklistTemplateToChecklist implements Converter<ChecklistTemplate, Checklist> {

    private Task task;

    public ChecklistTemplateToChecklist(Task task) {
        this.task = task;
    }

    @Override
    public Checklist convert(ChecklistTemplate source) {
        return ChecklistBuilder.createChecklist()
                    .withDone(Boolean.FALSE)
                    .withName(source.getName())
                    .withTask(task).build();

    }

    public static ChecklistTemplateToChecklist create(Task task) {
        return new ChecklistTemplateToChecklist(task);
    }
}
