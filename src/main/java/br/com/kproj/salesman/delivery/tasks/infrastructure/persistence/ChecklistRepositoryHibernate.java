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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;


@Repository
public class ChecklistRepositoryHibernate extends BaseRespositoryImpl<Checklist, ChecklistEntity> implements ChecklistRepository {


    private ChecklistRepositorySpringData repository;

    @Autowired
    public ChecklistRepositoryHibernate(ChecklistRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Checklist> register(Checklist checklist, Task task) {
        ChecklistEntity checklistEntity = new ChecklistEntity();
        checklistEntity.setName(checklist.getName());
        checklistEntity.setDone(Boolean.FALSE);
        checklistEntity.setTask(new TaskEntity(task.getId()));

        Checklist checklistSaved = getConverter().convert(repository.save(checklistEntity));

        return Optional.ofNullable(checklistSaved);
    }

    @Override
    public Iterable<Checklist> findAll(Task task, Pageable pageable) {
        Page<ChecklistEntity> checklistEntities = repository.findCheckListBy(new TaskEntity(task.getId()), pageable);

        List<Checklist> checklists = checklistEntities.getContent().stream().map(checklistEntity ->
                getConverter().convert(checklistEntity)).collect(Collectors.toList());

        return new PageImpl<>(checklists, pageable, checklistEntities.getTotalElements());
    }

    @Override
    public Checklist update(Checklist checklist) {
        ChecklistEntity checklistEntity = repository.findOne(checklist.getId());
        from(checklist).merge(checklistEntity);
        repository.save(checklistEntity);
        return getConverter().convert(checklistEntity);
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
            checklist.setDone(checklistEntity.getDone());
            checklist.setName(checklistEntity.getName());
            checklist.setTask(new Task(checklistEntity.getTask().getId()));

            return checklist;
        };
    }

}
