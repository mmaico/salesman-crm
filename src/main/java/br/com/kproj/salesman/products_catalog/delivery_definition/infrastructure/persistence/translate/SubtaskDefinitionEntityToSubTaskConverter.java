package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.SubtaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.Subtask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.mmaico.clone.BusinessModelClone.from;


@Component
public class SubtaskDefinitionEntityToSubTaskConverter implements Converter<SubtaskDefinitionEntity, Subtask> {

    private TaskDefinitionEntityToTaskConverter converter;

    @Autowired
    public SubtaskDefinitionEntityToSubTaskConverter(TaskDefinitionEntityToTaskConverter converter) {
        this.converter = converter;
    }

    @Override
    public Subtask convert(SubtaskDefinitionEntity entity, Object... args) {
        Subtask subtask = from(entity).convertTo(Subtask.class);
        Task task = converter.convert(entity.getTaskDefinition());
        from(task).merge(subtask);

        subtask.setParent(new RootTask(entity.getParent().getId()));

        return subtask;
    }
}
