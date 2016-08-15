package br.com.kproj.salesman.delivery.application.tasktemplates;

import br.com.kproj.salesman.delivery.domain.TaskTemplateDomainService;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
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
public class TaskTemplateApplicationImpl extends BaseModelServiceLegacyImpl<TaskTemplate> implements TaskTemplateApplication {

    @Autowired
    private TaskTemplateRepository repository;

    @Autowired
    private TaskTemplateDomainService domainService;


    @Override
    public TaskTemplate register(TaskTemplate taskTemplate) {
        TaskTemplate templateSaved = super.save(taskTemplate, domainService);

        if (templateSaved.hasValidParent()) {
            TaskTemplate parent = repository.findOne(templateSaved.getParent().getId());
            parent.addChild(templateSaved);
        }

        return templateSaved;
    }

    @Override
    public List<TaskTemplate> findTaskTemplateBy(SaleableUnitEntity saleable) {
        if (saleable == null || saleable.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findTaskTemplateBy(saleable);
    }

    @Override
    public List<TaskTemplate> findTaskTemplateOnlyRootBy(SaleableUnitEntity saleable) {
        if (saleable == null || saleable.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findTaskTemplateRootBy(saleable);
    }

    @Override
    public void remove(TaskTemplate taskTemplate) {

        if (taskTemplate == null || taskTemplate.isNew()) {
            hasErrors(Sets.newHashSet("tasktemplate.not.id")).throwing(ValidationException.class);
        }
        Optional<TaskTemplate> result = repository.getOne(taskTemplate.getId());

        if (!result.isPresent()) {
            hasErrors(Sets.newHashSet("tasktemplate.notfound")).throwing(ValidationException.class);
        }
        TaskTemplate tasktemplateLoaded = result.get();

        repository.delete(tasktemplateLoaded);
    }

    public Optional<TaskTemplate> getOne(Long id) {

        Optional<TaskTemplate> result = repository.getOne(id);

        if (!result.isPresent()) {
            hasErrors(Sets.newHashSet("tasktemplate.notfound")).throwing(ValidationException.class);
        }
        Optional<TaskTemplate> parent = repository.findParent(result.get());

        result.get().setParent(parent.isPresent() ? parent.get() : null);

        return result;
    }

    public BaseRepositoryLegacy<TaskTemplate, Long> getRepository() {
        return repository;
    }


}
