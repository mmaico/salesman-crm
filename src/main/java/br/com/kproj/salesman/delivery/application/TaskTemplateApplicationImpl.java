package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.domain.TaskTemplateDomainService;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskTemplateRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTemplateApplicationImpl extends BaseModelServiceImpl<TaskTemplate> implements TaskTemplateApplication {

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
    public List<TaskTemplate> findTaskTemplateBy(SaleableUnit saleable) {
        if (saleable == null || saleable.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findTaskTemplateBy(saleable);
    }

    @Override
    public List<TaskTemplate> findTaskTemplateOnlyRootBy(SaleableUnit saleable) {
        if (saleable == null || saleable.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findTaskTemplateRootBy(saleable);
    }

    @Override
    public BaseRepository<TaskTemplate, Long> getRepository() {
        return repository;
    }


}
