package br.com.kproj.salesman.delivery.application.tasktemplates;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplate;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.task.ChecklistTemplateRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistTemplateApplicationImpl extends BaseModelServiceLegacyImpl<ChecklistTemplate> implements ChecklistTemplateApplication {

    @Autowired
    private ChecklistTemplateRepository repository;


    @Override
    public ChecklistTemplate register(ChecklistTemplate checklistTemplate) {

        return super.save(checklistTemplate);
    }

    @Override
    public List<ChecklistTemplate> findCheckListBy(TaskTemplate taskTemplate) {

        if (taskTemplate == null || taskTemplate.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findCheckListBy(taskTemplate);
    }

    @Override
    public void delete(ChecklistTemplate checklistTemplate) {
        repository.delete(checklistTemplate);
    }

    public BaseRepositoryLegacy<ChecklistTemplate, Long> getRepository() {
        return repository;
    }



}
