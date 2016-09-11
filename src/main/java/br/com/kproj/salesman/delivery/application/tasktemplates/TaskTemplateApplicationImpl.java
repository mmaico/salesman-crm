package br.com.kproj.salesman.delivery.application.tasktemplates;

import br.com.kproj.salesman.delivery.domain.TaskTemplateDomainService;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.task.TaskTemplateRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class TaskTemplateApplicationImpl extends BaseModelServiceLegacyImpl<TaskTemplateEntity> implements TaskTemplateApplication {

    @Autowired
    private TaskTemplateRepository repository;

    @Autowired
    private TaskTemplateDomainService domainService;


    @Override
    public TaskTemplateEntity register(TaskTemplateEntity taskTemplateEntity) {
        TaskTemplateEntity templateSaved = super.save(taskTemplateEntity, domainService);

        if (templateSaved.hasValidParent()) {
            TaskTemplateEntity parent = repository.findOne(templateSaved.getParent().getId());
            parent.addChild(templateSaved);
        }

        return templateSaved;
    }

    @Override
    public List<TaskTemplateEntity> findTaskTemplateBy(SaleableUnitEntity saleable) {
        if (saleable == null || saleable.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findTaskTemplateBy(saleable);
    }

    @Override
    public List<TaskTemplateEntity> findTaskTemplateOnlyRootBy(SaleableUnitEntity saleable) {
        if (saleable == null || saleable.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findTaskTemplateRootBy(saleable);
    }

    @Override
    public void remove(TaskTemplateEntity taskTemplateEntity) {

        if (taskTemplateEntity == null || taskTemplateEntity.isNew()) {
            hasErrors(Sets.newHashSet("tasktemplate.not.id")).throwing(ValidationException.class);
        }
        Optional<TaskTemplateEntity> result = repository.getOne(taskTemplateEntity.getId());

        if (!result.isPresent()) {
            hasErrors(Sets.newHashSet("tasktemplate.notfound")).throwing(ValidationException.class);
        }
        TaskTemplateEntity tasktemplateLoaded = result.get();

        repository.delete(tasktemplateLoaded);
    }

    public Optional<TaskTemplateEntity> getOne(Long id) {

        Optional<TaskTemplateEntity> result = repository.getOne(id);

        if (!result.isPresent()) {
            hasErrors(Sets.newHashSet("tasktemplate.notfound")).throwing(ValidationException.class);
        }
        Optional<TaskTemplateEntity> parent = repository.findParent(result.get());

        result.get().setParent(parent.isPresent() ? parent.get() : null);

        return result;
    }

    public BaseRepositoryLegacy<TaskTemplateEntity, Long> getRepository() {
        return repository;
    }


}
