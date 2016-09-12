package br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence;

import br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence.springdata.ChecklistRepositorySpringData;
import br.com.kproj.salesman.delivery2.tasks_template.model.checklist.Checklist;
import br.com.kproj.salesman.delivery2.tasks_template.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository("checklistTemplateRepository")
public class ChecklistRepositoryHibernate extends BaseRespositoryImpl<Checklist, ChecklistTemplateEntity> implements ChecklistRepository {

    @Autowired
    private ChecklistRepositorySpringData repository;

    @Override
    public Collection<Checklist> findAll(Task task) {
        List<ChecklistTemplateEntity> checkListBy = repository.findCheckListBy(new TaskTemplateEntity(task.getId()));
        List<Checklist> result = checkListBy.stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void delete(Checklist checklist) {
        repository.delete(new ChecklistTemplateEntity(checklist.getId()));
    }

    @Override
    public BaseRepositoryLegacy<ChecklistTemplateEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ChecklistTemplateEntity, Checklist> getConverter() {
        return (checklistTemplateEntity, args) -> {
            Checklist checklist = new Checklist();
            checklist.setId(checklistTemplateEntity.getId());
            checklist.setName(checklistTemplateEntity.getName());
            return checklist;
        };
    }
}
