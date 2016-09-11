package br.com.kproj.salesman.delivery.application.tasktemplates;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.task.ChecklistTemplateRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistTemplateApplicationImpl extends BaseModelServiceLegacyImpl<ChecklistTemplateEntity> implements ChecklistTemplateApplication {

    @Autowired
    private ChecklistTemplateRepository repository;


    @Override
    public ChecklistTemplateEntity register(ChecklistTemplateEntity checklistTemplateEntity) {

        return super.save(checklistTemplateEntity);
    }

    @Override
    public List<ChecklistTemplateEntity> findCheckListBy(TaskTemplateEntity taskTemplateEntity) {

        if (taskTemplateEntity == null || taskTemplateEntity.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findCheckListBy(taskTemplateEntity);
    }

    @Override
    public void delete(ChecklistTemplateEntity checklistTemplateEntity) {
        repository.delete(checklistTemplateEntity);
    }

    public BaseRepositoryLegacy<ChecklistTemplateEntity, Long> getRepository() {
        return repository;
    }



}
