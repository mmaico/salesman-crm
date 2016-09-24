package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.ChecklistRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class ChecklistRepositoryHibernate extends BaseRespositoryImpl<Checklist, ChecklistEntity> implements ChecklistRepository {

    @Autowired
    private ChecklistRepositorySpringData repository;

    @Override
    public Optional<Checklist> register(Checklist checklist, Task task) {
        ChecklistEntity checklistEntity = new ChecklistEntity();
        checklistEntity.setName(checklist.getName());
        checklistEntity.setIsDone(Boolean.FALSE);
        checklistEntity.setTask(new TaskEntity(task.getId()));

        Checklist checklistSaved = getConverter().convert(repository.save(checklistEntity));

        return Optional.ofNullable(checklistSaved);
    }

    @Override
    public Collection<Checklist> findAll(Task task) {
        List<ChecklistEntity> result = repository.findCheckListBy(new TaskEntity(task.getId()));
        List<Checklist> checklists = result.stream().map(item -> getConverter()
                .convert(item)).collect(Collectors.toList());

        return checklists;
    }

    @Override
    public void complete(Checklist checklist) {
        ChecklistEntity checklistEntity = repository.findOne(checklist.getId());
        checklistEntity.setIsDone(Boolean.TRUE);
    }

    @Override
    public BaseRepositoryLegacy<ChecklistEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ChecklistEntity, Checklist> getConverter() {
        return (checklistEntity, args) -> {
            Checklist checklist = new Checklist();
            checklist.setId(checklistEntity.getId());
            checklist.setDone(checklistEntity.getIsDone());
            checklist.setName(checklistEntity.getName());

            return checklist;
        };
    }


}
