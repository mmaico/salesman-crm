package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.ChecklistDefinitionEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.Checklist;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistToTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.ChecklistRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("checklistDefinitionRepositoryTaskDefinitionModule")
public class ChecklistRepositoryHibernate extends BaseRespositoryImpl<Checklist, ChecklistDefinitionEntity> implements ChecklistRepository {

    private ChecklistRepositorySpringData repository;

    @Autowired
    public ChecklistRepositoryHibernate (ChecklistRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Checklist> findAll(Task task) {
        List<ChecklistDefinitionEntity> checkListBy = repository.findCheckListBy(new TaskDefinitionEntity(task.getId()));
        List<Checklist> result = checkListBy.stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void delete(Checklist checklist) {
        repository.delete(checklist.getId());
    }

    @Override
    public Optional<Checklist> add(ChecklistToTask checklistToTask) {

        Checklist checklist = checklistToTask.getChecklist();

        ChecklistDefinitionEntity entity = new ChecklistDefinitionEntity();
        entity.setName(checklist.getName());
        entity.setTaskDefinition(new TaskDefinitionEntity(checklistToTask.getTaskId()));

        return Optional.of(getConverter().convert(repository.save(entity)));
    }

    @Override
    public BaseRepositoryLegacy<ChecklistDefinitionEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ChecklistDefinitionEntity, Checklist> getConverter() {
        return (checklistDefinition, args) -> {

            Checklist checklist = new Checklist();
            checklist.setId(checklistDefinition.getId());
            checklist.setName(checklistDefinition.getName());
            checklist.setTask(new Task(checklistDefinition.getTaskDefinition().getId()));

            return checklist;
        };
    }
}
