package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Represent;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.trex.clone.BusinessModelClone.from;


@Component
public class TaskDefinitionEntityToTaskConverter implements Converter<TaskDefinitionEntity, Task> {



    @Override
    public Task convert(TaskDefinitionEntity taskDefinitionEntity, Object... args) {
        Task task = from(taskDefinitionEntity).convertTo(Task.class);
        task.setRegion(new Region(taskDefinitionEntity.getRegion().getId()));
        task.setSaleable(new Saleable(taskDefinitionEntity.getSaleable().getId()));

        Optional<Represent> represent = taskDefinitionEntity.getType() == null
                ? Optional.of(Represent.NO_REPRESENT)
                : Optional.ofNullable(Represent.valueOf(taskDefinitionEntity.getType().name()));

        task.setRepresent(represent.orElse(Represent.NO_REPRESENT));

        return task;
    }
}
